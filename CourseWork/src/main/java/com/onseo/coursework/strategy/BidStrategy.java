/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onseo.coursework.strategy;

import com.onseo.coursework.domain.Lot;

public interface BidStrategy {

    long makeBid(Lot lot, long ballance, long regularBidAmount);

}
