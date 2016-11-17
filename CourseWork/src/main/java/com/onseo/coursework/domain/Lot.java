package com.onseo.coursework.domain;

public class Lot {

    private String id;
    private long timeForBid;

    public Lot() {
    }

    public Lot(String id, long timeForBid) {
        this.id = id;
        this.timeForBid = timeForBid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimeForBid() {
        return timeForBid;
    }

    public void setTimeForBid(long timeForBid) {
        this.timeForBid = timeForBid;
    }

    @Override
    public String toString() {
        return "Lot{" + "id=" + id + ", timeForBid=" + timeForBid + '}';
    }
}
