package com.onseo.coursework.domain;

import com.onseo.coursework.strategy.BidStrategy;
import lombok.Data;

@Data
public class Bot {

    private String id;
    private BidStrategy bidStrategy;
}
