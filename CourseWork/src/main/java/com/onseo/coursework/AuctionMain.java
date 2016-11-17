package com.onseo.coursework;

import com.onseo.coursework.domain.Auction;

public class AuctionMain {

    public static void main(String[] args) {
        final int ttl = 10000;
        Auction auction = new Auction("lot_1", ttl, 10);

        auction.start();
        try {
            Thread.sleep(ttl);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrapted");
        } finally {
            auction.stop();
        }
    }
}
