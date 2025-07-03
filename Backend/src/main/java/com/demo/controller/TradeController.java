package com.demo.controller;

import com.demo.model.Trade;
import com.demo.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/trades")
public class TradeController {
    @Autowired
    private TradeService service;

    @PostMapping
    public Trade save(@RequestBody Trade trade) {
        return service.save(trade);
    }

    @GetMapping
    public Page<Trade> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "25") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Trade getById(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        Trade trade = service.findById(id);
        long endTime = System.currentTimeMillis();
        log.info("Time taken: {}", endTime - startTime);
        return trade;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}