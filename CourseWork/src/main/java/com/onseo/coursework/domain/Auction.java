package com.onseo.coursework.domain;

import com.onseo.coursework.strategy.AggressiveStrategy;
import com.onseo.coursework.strategy.EasyStrategy;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Auction {

    private final Lot lot;
    private final long botsCount;
    private final Map<Long, Bid> bidHistory = new ConcurrentHashMap<>();
    private List<Bot> bots = new LinkedList<>();
    private ExecutorService executorService;
    private boolean isAuctionRunning = false;

    public Auction(String lotName, long time, long botsCount) {
        this.lot = new Lot(lotName, time);
        this.botsCount = botsCount;
        fillBots();
        System.out.println("Auction was created with parameters:");
        System.out.println("Lot : " + lot.toString());
        System.out.println("Bots count : " + botsCount);
    }

    private void fillBots() {
        Random rand = new Random(3);
        for (int i = 1; i <= botsCount; i++) {
            Bot auctionBot = new Bot();
            if (rand.nextInt() > 2) {
                auctionBot.setBidStrategy(new AggressiveStrategy());
                auctionBot.setId("aggressive_" + i);
            } else {
                auctionBot.setBidStrategy(new EasyStrategy());
                auctionBot.setId("easy_" + i);
            }
            bots.add(auctionBot);
        }
        System.out.println("Bots was created :");
        bots.forEach(b -> {
            System.out.println(b.toString());
        });
    }

    public void start() {
        isAuctionRunning = true;
        System.out.println("Auction was started:");
        executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            for (int i = 10; isAuctionRunning; i += 10) {
                processBots(i);
            }
        });
    }

    public void stop() {
        System.out.println("Auction was finished:");
        isAuctionRunning = false;
        executorService.shutdown();
        bidHistory.forEach((t, bid) -> {
            System.out.println(t + " : " + bid);
        });
        System.out.println("THE WINNER : " + decideWinnerBid().toString());
    }

    private Bid decideWinnerBid() {
        Long latestTs = bidHistory.keySet().stream().sorted((k1, k2) -> {
            return k2.compareTo(k1);
        }).findFirst().orElse(null);
        return latestTs != null ? bidHistory.get(latestTs) : null;
    }

    private void processBots(int currentBid) {
        System.out.println("start process bot with bid : " + currentBid);
        bots.stream().forEach((b) -> {
            long madeBid = b.getBidStrategy().makeBid(lot, 1, currentBid);
            System.out.println("Bot " + b.getId() + "make bid " + madeBid);
            Bid bid = new Bid();
            bid.setAmount(madeBid);
            bid.setBotId(b.getId());
            bidHistory.put(System.currentTimeMillis(), bid);
        });

    }
}
