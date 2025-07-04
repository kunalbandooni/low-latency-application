package com.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.demo.cache.TradeCache;
import com.demo.model.Trade;
import com.demo.repo.TradeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TradeConsumer {
    @Autowired
    private TradeRepository repository;
    @Autowired
    private TradeCache tradeCache;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "trades", groupId = "trade-consumer")
    public void consume(String message) {
        try {
            Trade trade = objectMapper.readValue(message, Trade.class);
            repository.save(trade);          // Save to MongoDB
            tradeCache.save(trade);          // Save to Redis
            log.info("Processed trade in kafka consumer: " + trade.getId());
        } catch (Exception e) {
            log.error("Some error occurred in running kafka consumer: {}", e.getMessage());
        }
    }
}
