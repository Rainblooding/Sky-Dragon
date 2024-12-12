package io.github.rainblooding.pool.base;


/**
 * 数据源配置接口
 *
 * @author chen
 * @since 1.0.0
 */
public interface IPoolDataSource {

    /**
     * 归还连接
     * @param poolConnection 连接池信息
     * @since 1.0.0
     */
    void returnConnection(IPoolConnection poolConnection);

    //================================ 基本配置 =====================================
    /**
     * 设置驱动类
     *
     * @param driverClass 驱动类
     * @since 1.0.0
     */
    void setDriverClass(String driverClass);

    /**
     * 获取驱动类
     *
     * @return 驱动类
     */
    String getDriverClass();

    /**
     * jdbc url
     *
     * @param url
     * @since 1.0.0
     */
    void setUrl(String url);

    /**
     * 获取jdbc url
     *
     * @return jdbc url
     * @since 1.0.0
     */
    String getUrl();

    /**
     * 设置用户信息
     *
     * @param user 用户
     * @since 1.0.0
     */
    void setUser(String user);

    /**
     * 获取用户
     *
     * @return 用户
     * @since 1.0.0
     */
    String getUser();

    /**
     * 设置密码
     *
     * @param password 密码
     * @since 1.0.0
     */
    void setPassword(String password);

    /**
     * 获取密码
     *
     * @return 密码
     * @since 1.0.0
     */
    String getPassword();

    //================================ 高级配置 =====================================

    /**
     * 设置连接池最小尺寸
     *
     * @param minSize 最小尺寸
     * @since 1.0.0
     */
    void setMinSize(int minSize);

    /**
     * 获取连接池最小尺寸
     *
     * @return 最小尺寸
     * @since 1.0.0
     */
    int getMinSize();

    /**
     * 设置连接池最大尺寸
     *
     * @param maxSize 最大尺寸
     * @since 1.0.0
     */
    void setMaxSize(int maxSize);

    /**
     * 获取连接池最大尺寸
     *
     * @return 最大尺寸
     * @since 1.0.0
     */
    int getMaxSize();

    /**
     * 设置最大等待时间
     *
     * @param maxWait 最大等待时间
     * @since 1.0.0
     */
    void setMaxWait(long maxWait);

    /**
     * 获取最大等待时间
     *
     * @return 最大等待时间
     * @since 1.0.0
     */
    long getMaxWait();


    /**
     * 设置连接是否有效的查询语句
     *
     * @param validQuery 查询语句
     * @since 1.0.0
     */
    void setValidQuery(String validQuery);

    /**
     * 获取连接是否有效的查询语句
     *
     * @return 查询语句
     * @since 1.0.0
     */
    String getValidQuery();

    /**
     * 设置连接是否有效的超时时间
     *
     * @param validateTimeout 超时时间
     * @since 1.0.0
     */
    void setValidateTimeout(int validateTimeout);

    /**
     * 获取连接是否有效的超时时间
     *
     * @return 超时时间
     * @since 1.0.0
     */
    int getValidateTimeout();

    /**
     * 获取时验证
     *
     * @param testOnBorrow 是否验证
     * @since 1.0.0
     */
    void setTestOnBorrow(boolean testOnBorrow);

    /**
     * 获取时验证
     *
     * @return 是否验证
     * @since 1.0.0
     */
    boolean isTestOnBorrow();

    /**
     * 归还时时验证
     *
     * @param testOnReturn 是否验证
     * @since 1.0.0
     */
    void setTestOnReturn(boolean testOnReturn);

    /**
     * 归还时时验证
     *
     * @return 是否验证
     * @since 1.0.0
     */
    boolean isTestOnReturn();

    /**
     * 空闲时验证
     *
     * @param testOnIdle 是否验证
     * @since 1.0.0
     */
    void setTestOnIdle(boolean testOnIdle);

    /**
     * 空闲时验证
     *
     * @return 是否验证
     * @since 1.0.0
     */
    boolean isTestOnIdle();

    /**
     * 空闲时验证时间间隔
     *
     * @param idleCheckInterval 时间间隔
     * @since 1.0.0
     */
    void setIdleCheckInterval(long idleCheckInterval);

    /**
     * 空闲时验证时间间隔
     *
     * @return 时间间隔
     * @since 1.0.0
     */
    long getIdleCheckInterval();
}
