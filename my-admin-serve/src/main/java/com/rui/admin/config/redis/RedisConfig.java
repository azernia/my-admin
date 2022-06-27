package com.rui.admin.config.redis;

import com.alibaba.fastjson.parser.ParserConfig;
import com.rui.admin.commons.serializer.json.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author rui
 * @since 2022/3/23 10:38
 */
@Configuration
public class RedisConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    public RedisConfig(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /**
     * Redis 存储 token
     * @return {@link TokenStore}
     */
    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 自定义 RedisTemplate
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        // 使用fastJson序列化
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        // value值的序列化采用fastJsonRedisSerializer
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // 全局开启AutoType，不建议使用
        // ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 建议使用这种方式，小范围指定白名单，需要序列化的类
        ParserConfig globalInstance = ParserConfig.getGlobalInstance();
        globalInstance.addAccept("com.rui.admin.config.security.entity");
        globalInstance.addAccept("com.rui.admin.system.model.entity");
        // key的序列化采用StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // TODO Redis 事务
        // 启用 Redis 事务支持
        // redisTemplate.setEnableTransactionSupport(true);
        // 初始化 RedisTemplate
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    // 事务管理器
    // @Bean
    // public PlatformTransactionManager transactionManager(DataSource dataSource) {
    //     return new DataSourceTransactionManager(dataSource);
    // }

}
