package io.github.rainblooding.pool.base;


import java.sql.Connection;

/**
 * 池化的数据库连接
 *
 * @author rainblooding
 * @since 1.0.0
 */
public interface IPoolConnection extends Connection {

    /**
     * 是否空闲
     *
     * @return 是否空闲
     * @since 1.0.0
     */
    boolean isFree();

    /**
     * 设置是否空闲
     *
     * @param free 是否空闲
     * @since  1.0.0
     */
    void setFree(boolean free);

    /**
     * 获取真实的数据库连接
     *
     * @return 数据库连接
     * @since 1.0.0
     */
    Connection getConnection();

    /**
     * 设置真实的数据库连接
     *
     * @param connection 数据库连接
     * @since  1.0.0
     */
    void setConnection(Connection connection);

    /**
     * 获取数据源
     *
     * @return 数据源
     * @since 1.0.0
     */
    IPoolDataSource getDataSource();

    /**
     * 设置数据源
     *
     * @param dataSource 数据源
     * @since 1.0.0
     */
    void setDataSource(IPoolDataSource dataSource);
}
