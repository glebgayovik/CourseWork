package com.onseo.coursework.domain;

public class Bid {

    private String botId;
    private long amount;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bid{" + "botId=" + botId + ", amount=" + amount + '}';
    }

}
