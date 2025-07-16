package com.demo.service.impl;

import com.demo.cache.TradeCache;
import com.demo.kafka.KafkaProducer;
import com.demo.model.Trade;
import com.demo.repo.TradeRepository;
import com.demo.service.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class TradeServiceImpl implements TradeService {
    @Value("${kafka.topic.trade}")
    private String tradeTopicName;

    @Autowired
    private TradeRepository repo;
    @Autowired
    private TradeCache tradeCache;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Trade save(Trade trade) {
        return repo.save(trade);
    }

    @Override
    public Trade publishToSave(Trade trade) {
        try {
            String json = objectMapper.writeValueAsString(trade);
            kafkaProducer.send(tradeTopicName, json);
        } catch (Exception e) {
            log.error("Unable to convert data into string, Exception: {}", e.getMessage());
        }
        return trade;
    }

    @Override
    public Page<Trade> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Trade findById(String id) {
        Trade cached = tradeCache.get(id);
        if (cached != null) return cached;

        Trade trade = repo.findById(id).orElseThrow(() ->
                new NoSuchElementException("Trade not found with id " + id)
        );
        tradeCache.save(trade);
        return trade;
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }
}
