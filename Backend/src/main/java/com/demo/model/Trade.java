package com.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "trades")
public class Trade {
    @Id
    private String id;

    private String symbol;
    private Long timestamp;
    private double price;
    private int volume;
}
