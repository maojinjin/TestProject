package com.saic.ebiz.smcc.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saic.framework.redis.client.impl.RedisClientImpl;
import com.saic.framework.redis.config.PoolConfig;

@Configuration
public class RedisConfig {

    @Value("${global.codis.zkAddress}")
    private String zkAddress;

    @Value("${codis.cluster.enterprise.name}")
    private String codisProjectName;

    /**
     * 
     * 功能描述: <br>
     * 配置redis连接池信息
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Bean
    public PoolConfig poolConfigRedis() {
        PoolConfig poolConfig = new PoolConfig();
        // 最大空闲连接数
        poolConfig.setMaxIdle("60");
        // 最小空闲连接数
        poolConfig.setMinIdle("60");
        // 是否测试连接
        poolConfig.setTestOnBorrow("true");
        // 测试连接后是否返回一个连接
        poolConfig.setTestOnReturn("true");
        // 是否空闲时检测连接
        poolConfig.setTestWhileIdle("true");
        return poolConfig;
    }

    /**
     * 
     * 功能描述: <br>
     * 配置redis客户端
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Bean
    public RedisClientImpl springRedisClient() {
        RedisClientImpl springRedisClient = new RedisClientImpl();
        // 配置redis引用的链接池配置信息
        springRedisClient.setPoolConfig(poolConfigRedis());
        // 使用的zookeeper地址信息
        springRedisClient.setZkAddress(zkAddress);
        // 按照应用分配的codis名称信息
        springRedisClient.setCodisProjectName(codisProjectName);
        return springRedisClient;
    }

}
