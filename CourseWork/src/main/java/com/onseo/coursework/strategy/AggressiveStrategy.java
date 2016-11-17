package com.onseo.coursework.strategy;

import com.onseo.coursework.domain.Lot;

public class AggressiveStrategy implements BidStrategy {

    @Override
    public long makeBid(Lot lot, long currentStage, long regularBidAmount) {
        long bid = regularBidAmount;
        long timeForBid = lot.getTimeForBid();
        if (timeForBid * (2 / 3) < currentStage) {
            bid *= 2;
        }
        return bid;
    }

}
