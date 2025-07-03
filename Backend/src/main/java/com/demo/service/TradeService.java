package com.demo.service;

import com.demo.model.Trade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TradeService {
    Trade save(Trade trade);
    Page<Trade> findAll(Pageable pageable);
    Trade findById(String id);
    void delete(String id);
}
