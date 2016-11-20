package com.onseo.coursework;

import com.onseo.coursework.domain.Auction;

public class AuctionMain {

    public static void main(String[] args) {
        final int ttl = 10000;
        int regularBid = 10;
        int botsCount = 10;
        final Long botBalance = 1000000L;
        Auction auction = new Auction("lot_1", ttl, botsCount, botBalance, regularBid);
        auction.start();
    }
}
