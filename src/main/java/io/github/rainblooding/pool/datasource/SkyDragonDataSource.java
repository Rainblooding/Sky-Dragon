package io.github.rainblooding.pool.datasource;



import io.github.rainblooding.pool.base.IPoolConnection;
import io.github.rainblooding.pool.connection.SkyDragonConnection;
import io.github.rainblooding.pool.exception.JdbcPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 数据库连接池实现
 *
 * @author rainblooding
 * @since 1.0.0
 */
public class SkyDragonDataSource extends AbstractPoolDataSource {


    private List<IPoolConnection> pool = new ArrayList<>();

    ScheduledExecutorService idleExecutor = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void init() {
        this.initJdbcPool();
        this.initTestOnIdle();
    }

    @Override
    public void destroy() {
        System.out.println("连接池关闭");

        idleExecutor.shutdownNow();

        for (IPoolConnection poolConnection : pool) {
            try {
                if (poolConnection.isClosed()) {
                   poolConnection.getConnection().close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 初始化数据库连接池
     *
     * @since 1.0.0
     */
    public void initJdbcPool() {
        pool = new ArrayList<>(minSize);

        for(int i = 0; i < minSize; i++) {
            IPoolConnection poolConnection = createPoolConnection();

            pool.add(poolConnection);
        }
    }

    /**
     * 初始化空闲检查
     *
     * @since 1.0.0
     */
    public void initTestOnIdle() {
        if(super.validQuery != null && !"".equals(super.validQuery)) {
            idleExecutor.scheduleAtFixedRate(() -> testOnIdleCheck(),
                    super.testOnIdleIntervalSeconds,
                    super.testOnIdleIntervalSeconds,
                    TimeUnit.SECONDS);
        }
    }

    @Override
    public void returnConnection(IPoolConnection poolConnection) {
        if (this.testOnReturn) {
            this.checkValid(poolConnection);
        }
        // 设置为不繁忙
        poolConnection.setFree(true);
    }

    /**
     * 创建数据库连接
     *
     * 此方法负责根据继承自父类的数据库URL、用户名和密码创建一个数据库连接
     * 它封装了连接的创建过程，使得连接的使用者无需关心连接的细节
     *
     * @return Connection 数据库连接对象
     * @since 1.0.0
     */
    private Connection createConnection() {
        try {
            return DriverManager.getConnection(super.getUrl(), super.getUser(), super.getPassword());
        } catch (SQLException e) {
            System.err.println("创建连接失败");
            throw new JdbcPoolException(e);
        }
    }

    /**
     * 创建池化的数据库连接对象
     *
     * @return IPoolConnection 池化的数据库连接对象
     * @since 1.0.0
     */
    private IPoolConnection createPoolConnection() {
        IPoolConnection poolConnection = new SkyDragonConnection();

        poolConnection.setFree(true);
        poolConnection.setConnection(createConnection());
        poolConnection.setDataSource(this);

        return poolConnection;
    }

    /**
     * 校验连接是否有效
     *
     * @param poolConnection 连接对象
     * @since 1.0.0
     */
    private void checkValid(final IPoolConnection poolConnection) {
        if (super.getValidQuery() != null && !"".equals(super.getValidQuery())) {
            Connection connection = poolConnection.getConnection();
            try {
                if (!connection.isValid(super.validTimeOutSeconds)) {
                    connection = this.createConnection();
                    poolConnection.setConnection(connection);
                }
            } catch (SQLException exception) {
                System.err.println("验证失败");
                throw new JdbcPoolException(exception);
            }

        }
    }

    /**
     * 空闲检查
     *
     * @since 1.0.0
     */
    private void testOnIdleCheck() {
        for (IPoolConnection poolConnection : this.pool) {
            if (poolConnection.isFree()) {
                this.checkValid(poolConnection);
            }
        }
    }

    /**
     * 获取空闲连接
     *
     * @return IPoolConnection 空闲连接对象
     * @since 1.0.0
     */
    private IPoolConnection getFreeConnection() {
        for (IPoolConnection poolConnection : this.pool) {
            if (poolConnection.isFree()) {
                poolConnection.setFree(false);
                if(super.testOnBorrow) {
                    this.checkValid(poolConnection);
                }
                return poolConnection;
            }
        }
        return null;
    }

    /**
     * 获取数据库连接
     *
     * @since 1.0.0
     * @return 数据库连接
     */
    public Connection getConnection() {
        IPoolConnection freeConnection = this.getFreeConnection();
        if (freeConnection != null) return freeConnection;

        if (pool.size() >= super.maxSize) {
            if (super.maxSize <= 0) {
                System.err.println("获取连接失败");
                throw new JdbcPoolException("Can't get connection from pool!");
            }

            //2.2 循环等待
            final long endWait = System.currentTimeMillis() + super.maxWait;

            while(System.currentTimeMillis() < endWait) {
                freeConnection = this.getFreeConnection();
                if(freeConnection != null) return freeConnection;

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //2.3 等待超时
            System.out.println("获取连接失败");
            throw new JdbcPoolException("Can't get connection from pool, wait time out for mills: " + super.maxSize);
        }


        //3. 扩容（暂时只扩容一个）
        System.out.println("start to resize jdbc pool,step: 1");
        IPoolConnection poolConnection = this.createPoolConnection();
        poolConnection.setFree(false);
        this.pool.add(poolConnection);
        System.out.println("end to resize jdbc pool");
        return poolConnection;
    }

}
