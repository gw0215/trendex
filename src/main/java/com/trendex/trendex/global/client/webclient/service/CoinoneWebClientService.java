package com.trendex.trendex.global.client.webclient.service;

import com.trendex.trendex.global.client.webclient.dto.coinone.CoinoneOrderBook;
import com.trendex.trendex.global.client.webclient.dto.coinone.CoinoneTicker;
import com.trendex.trendex.global.client.webclient.dto.coinone.CoinoneCurrency;
import com.trendex.trendex.global.client.webclient.dto.coinone.CoinoneTransactionHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CoinoneWebClientService {

    private final WebClient coinoneWebClient;

    public CoinoneCurrency getAllCurrencies() {

        return coinoneWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/currencies")
                        .build())
                .header("accept", "application/json")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .bodyToMono(CoinoneCurrency.class)
                .block();

    }

    public CoinoneCurrency getCurrencies(String currency) {

        return coinoneWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/currencies/{currency}")
                        .build(currency))
                .header("accept", "application/json")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .bodyToMono(CoinoneCurrency.class)
                .block();

    }

    public CoinoneTicker getAllTicker(String quoteCurrency, boolean additionalData) {

        return coinoneWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/ticker_new/{quote_currency}")
                        .queryParam("additional_data", additionalData)
                        .build(quoteCurrency))
                .header("accept", "application/json")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .bodyToMono(CoinoneTicker.class)
                .block();

    }

    public CoinoneTicker getTicker(String quoteCurrency, String targetCurrency, boolean additionalData) {

        return coinoneWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/ticker_new/{quote_currency}/{target_currency}")
                        .queryParam("additional_data", additionalData)
                        .build(quoteCurrency, targetCurrency))
                .header("accept", "application/json")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .bodyToMono(CoinoneTicker.class)
                .block();

    }

    public CoinoneTransactionHistory getTransactionHistory(String quoteCurrency, String targetCurrency, int size) {

        return coinoneWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/trades/{quote_currency}/{target_currency}")
                        .queryParam("size", size)
                        .build(quoteCurrency, targetCurrency))
                .header("accept", "application/json")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .bodyToMono(CoinoneTransactionHistory.class)
                .block();

    }

    public CoinoneOrderBook getOrderBook(String quoteCurrency, String targetCurrency, int size) {

        return coinoneWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/orderbook/{quote_currency}/{target_currency}")
                        .queryParam("size", size)
                        .build(quoteCurrency, targetCurrency))
                .header("accept", "application/json")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .bodyToMono(CoinoneOrderBook.class)
                .block();

    }

}
