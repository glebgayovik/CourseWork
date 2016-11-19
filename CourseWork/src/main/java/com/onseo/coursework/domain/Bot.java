package com.onseo.coursework.domain;

import com.onseo.coursework.strategy.BidStrategy;
import java.util.concurrent.ScheduledExecutorService;

public class Bot {

    private String id;
    private BidStrategy bidStrategy;
    private ScheduledExecutorService behaviour;

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

    public ScheduledExecutorService getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(ScheduledExecutorService behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public String toString() {
        return "Bot{" + "id=" + id + ", bidStrategy=" + bidStrategy + '}';
    }

}
