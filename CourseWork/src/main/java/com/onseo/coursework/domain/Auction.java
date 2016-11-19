package com.onseo.coursework.domain;

import com.onseo.coursework.strategy.AggressiveStrategy;
import com.onseo.coursework.strategy.EasyStrategy;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Auction {

    private final Lot lot;
    private final long botsCount;
    private final Map<Long, Bid> bidHistory = new ConcurrentHashMap<>();
    private final List<Bot> bots = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private ScheduledExecutorService watcherService;
    private Bid biggestBid;

    private Bid retrieveBid() {
        lock.lock();
        try {
            return this.biggestBid;
        } catch (Exception e) {
            System.out.println("Error during lock");
        } finally {
            lock.unlock();
        }
        return null;
    }

    private void setBid(Bid bid) {
        lock.lock();
        try {
            this.biggestBid = bid;
        } catch (Exception e) {
            System.out.println("Error during lock");
        } finally {
            lock.unlock();
        }
    }

    public Auction(String lotName, long time, long botsCount) {
        this.lot = new Lot(lotName, time);
        this.botsCount = botsCount;
        this.biggestBid = new Bid() {
            {
                setBotId("initializer");
                setAmount(10);
            }
        };
    }

    public void start() {
        Thread watcherThread = registryWatchers();
        try {
            watcherThread.start();
            fillBots();
            System.out.println("Auction was created with parameters:");
            System.out.println("Lot : " + lot.toString());
            System.out.println("Bots count : " + botsCount);
            Thread.sleep(this.lot.getTimeForBid());
        } catch (InterruptedException ex) {
            System.out.println("Error durin processing thread " + ex.getMessage());
        } finally {
            stopAuction();
            System.out.println("Winner bid is : " + biggestBid.toString());
        }
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
            auctionBot.setBehaviour(createBotBehaviour(auctionBot));
            bots.add(auctionBot);
        }
        System.out.println("Bots was created :");
        bots.forEach(b -> {
            System.out.println(b.toString());
        });
    }

    private ScheduledExecutorService createBotBehaviour(Bot bot) {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);
        service.scheduleAtFixedRate(() -> {
            Bid currentBid = retrieveBid();
            Bid newBid = new Bid();
            newBid.setAmount(biggestBid.getAmount() + bot.getBidStrategy().makeBid(lot, 0, 10));
            newBid.setBotId(bot.getId());
            bidHistory.put(System.currentTimeMillis(), newBid);
            setBid(newBid);

        }, 1, 5, TimeUnit.MILLISECONDS);
        return service;
    }

    private Thread registryWatchers() {
        return new Thread(() -> {
            watcherService = new ScheduledThreadPoolExecutor(1);
            watcherService.scheduleAtFixedRate(() -> {
                System.out.println("Start make auction dump at : " + System.currentTimeMillis());
                bidHistory.forEach((t, bid) -> {
                    System.out.println(t + " : " + bid);
                });
            }, 5, 1000, TimeUnit.MILLISECONDS);
        });
    }

    private void stopAuction() {
        try {
            bots.forEach(b -> {
                b.getBehaviour().shutdownNow();
            });
        } catch (Exception e) {
            e.getMessage();
        } finally {
            System.out.println("auction stoped at " + System.currentTimeMillis());
            watcherService.shutdownNow();
        }
    }
}
