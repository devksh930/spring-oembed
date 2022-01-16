package me.devksh930.oembed.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
@Slf4j
public class CacheConfig {
    public static final String PROVIDER_ENDPOINT_LIST = "allEndPoint";

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(PROVIDER_ENDPOINT_LIST);
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {PROVIDER_ENDPOINT_LIST})
    @Scheduled(fixedDelay = 3 * 60 * 1000, initialDelay = 3000)
    public void cacheEvict() {
        log.debug("캐쉬 삭제");
    }
}
