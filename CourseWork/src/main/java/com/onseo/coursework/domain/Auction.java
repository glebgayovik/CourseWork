package com.onseo.coursework.domain;

import com.onseo.coursework.strategy.AggressiveStrategy;
import com.onseo.coursework.strategy.EasyStrategy;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Auction {

    private final Lot lot;
    private final Map<String, Bid> bidHistory = new ConcurrentHashMap<>();
    private List<Bot> bots = new LinkedList<>();

    public Auction(String lotName, long time, long botsCount) {
        this.lot = new Lot(lotName, time);
        fillBots(botsCount);
    }

    private void fillBots(long botsCount) {
        Random rand = new Random(3);
        for (int i = 1; i <= botsCount; i++) {
            Bot bot = new Bot();
            if (rand.nextInt() > 2) {
                bot.setBidStrategy(new AggressiveStrategy());
                bot.setId("aggressive_" + i);
            } else {
                bot.setBidStrategy(new EasyStrategy());
                bot.setId("easy_" + i);
            }
            bots.add(bot);
        }
    }

    public void start() {
        
    }
}
