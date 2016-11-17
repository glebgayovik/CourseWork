package com.onseo.coursework.domain;

import com.onseo.coursework.strategy.BidStrategy;

public class Bot {

    private String id;
    private BidStrategy bidStrategy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BidStrategy getBidStrategy() {
        return bidStrategy;
    }

    public void setBidStrategy(BidStrategy bidStrategy) {
        this.bidStrategy = bidStrategy;
    }

    @Override
    public String toString() {
        return "Bot{" + "id=" + id + ", bidStrategy=" + bidStrategy + '}';
    }

}
