package com.trendex.trendex.global.client.webclient.dto.upbit;

import lombok.Getter;

import java.util.List;

@Getter
public class UpbitOrderBook {

    private String market;

    private long timestamp;

    private double totalAskSize;

    private double totalBidSize;

    private List<UpbitOrderBookUnit> orderBookUnits;

    private int level;

}
