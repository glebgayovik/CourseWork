package com.onseo.coursework.strategy;

import com.onseo.coursework.domain.Lot;

public class EasyStrategy implements BidStrategy {

    @Override
    public long makeBid(Lot lot, long ballance, long regularBidAmount) {
        return regularBidAmount;
    }

}
