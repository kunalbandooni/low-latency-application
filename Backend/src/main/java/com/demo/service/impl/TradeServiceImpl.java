package com.demo.service.impl;

import com.demo.cache.TradeCache;
import com.demo.model.Trade;
import com.demo.repo.TradeRepository;
import com.demo.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeRepository repo;
    @Autowired
    private TradeCache tradeCache;

    @Override
    public Trade save(Trade trade) {
        return repo.save(trade);
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
