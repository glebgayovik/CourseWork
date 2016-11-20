package com.onseo.coursework.domain;

public class Bid {

    private String botId;
    private Long amount;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bid{" + "botId=" + botId + ", amount=" + amount + '}';
    }

}
