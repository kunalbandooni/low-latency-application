package com.demo.cache;

import com.demo.model.Trade;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TradeCache {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void save(Trade trade) {
        try {
            String key = "trade:" + trade.getId();
            String value = objectMapper.writeValueAsString(trade);
            redisTemplate.opsForValue().set(key, value, 60, TimeUnit.MINUTES);  // 60 minutes
        } catch (Exception e) {
            log.error("Some exception occurred while saving: {}", e.getMessage());
        }
    }

    public Trade get(String id) {
        try {
            String key = "trade:" + id;
            String value = redisTemplate.opsForValue().get(key);
            return value != null ? objectMapper.readValue(value, Trade.class) : null;
        } catch (Exception e) {
            log.error("Some exception occurred while get id {} : {}", id, e.getMessage());
            return null;
        }
    }
}
