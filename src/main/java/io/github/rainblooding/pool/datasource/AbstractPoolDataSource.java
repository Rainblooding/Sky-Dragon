package io.github.rainblooding.pool.datasource;


import io.github.rainblooding.pool.base.ILifeCycle;
import io.github.rainblooding.pool.base.IPoolDataSource;
import io.github.rainblooding.pool.base.PooledConst;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * 抽象类
 *
 * @author rainblooding
 * @since 1.0.0
 */
public abstract class AbstractPoolDataSource implements IPoolDataSource, DataSource, ILifeCycle {


    /**
     * 数据库驱动类
     */
    protected String driverClass;

    /**
     * 数据库连接地址
     */
    protected String url;

    /**
     * 数据库用户名
     */
    protected String user;

    /**
     * 数据库密码
     */
    protected String password;

    /**
     * 最小尺寸
     * @since 1.0.0
     */
    protected int minSize = PooledConst.DEFAULT_MIN_SIZE;

    /**
     * 最大尺寸
     * @since 1.0.0
     */
    protected int maxSize = PooledConst.DEFAULT_MAX_SIZE;

    /**
     * 最大的等待时间
     * @since 1.0.0
     */
    protected long maxWait = PooledConst.DEFAULT_MAX_WAIT_MILLS;

    /**
     * 验证查询
     * @since 1.0.0
     */
    protected String validQuery = PooledConst.DEFAULT_VALID_QUERY;

    /**
     * 验证的超时时间
     * @since 1.0.0
     */
    protected int validTimeOutSeconds = PooledConst.DEFAULT_VALID_TIME_OUT_SECONDS;

    /**
     * 获取时验证
     * @since 1.0.0
     */
    protected boolean testOnBorrow = PooledConst.DEFAULT_TEST_ON_BORROW;

    /**
     * 归还时验证
     * @since 1.0.0
     */
    protected boolean testOnReturn = PooledConst.DEFAULT_TEST_ON_RETURN;

    /**
     * 闲暇时验证
     * @since 1.0.0
     */
    protected boolean testOnIdle = PooledConst.DEFAULT_TEST_ON_IDLE;

    /**
     * 闲暇时验证的时间间隔
     * @since 1.0.0
     */
    protected long testOnIdleIntervalSeconds = PooledConst.DEFAULT_TEST_ON_IDLE_INTERVAL_SECONDS;


    @Override
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    @Override
    public String getDriverClass() {
        return driverClass;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    @Override
    public int getMinSize() {
        return minSize;
    }

    @Override
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    @Override
    public long getMaxWait() {
        return maxWait;
    }

    @Override
    public void setValidQuery(String validQuery) {
        this.validQuery = validQuery;
    }

    @Override
    public String getValidQuery() {
        return validQuery;
    }

    @Override
    public void setValidateTimeout(int validateTimeout) {
        this.validTimeOutSeconds = validateTimeout;
    }

    @Override
    public int getValidateTimeout() {
        return validTimeOutSeconds;
    }

    @Override
    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    @Override
    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    @Override
    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    @Override
    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    @Override
    public void setTestOnIdle(boolean testOnIdle) {
        this.testOnIdle = testOnIdle;
    }

    @Override
    public boolean isTestOnIdle() {
        return testOnIdle;
    }

    @Override
    public void setIdleCheckInterval(long idleCheckInterval) {
        this.testOnIdleIntervalSeconds = idleCheckInterval;
    }

    @Override
    public long getIdleCheckInterval() {
        return testOnIdleIntervalSeconds;
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    //================================= data source ======================================
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
