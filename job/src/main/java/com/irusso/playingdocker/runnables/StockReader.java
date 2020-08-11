package com.irusso.playingdocker.runnables;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusso.playingdocker.constant.Tickers;
import com.irusso.playingdocker.model.Quote;
import com.irusso.playingdocker.service.FinnhubService;
import org.apache.http.HttpException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class StockReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final FinnhubService finnhubService;

    public StockReader(Properties config) {
        finnhubService = new FinnhubService(config);
    }

    public Map<String, String> checkoutStocks() {
        Map<String, Quote> quotes = new HashMap<>();

        Tickers.DESIRED.forEach(ticker -> {
            try {
                quotes.put(ticker, finnhubService.getQuote(ticker));
            } catch (HttpException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Got quotes - \n" + prettyPrint(quotes));
        return quotes.entrySet().stream().collect(Collectors.toMap(
            Map.Entry::getKey, StockReader::write));
    }

    private static String write(Map.Entry<String, Quote> entry) {
        try {
            return objectMapper.writeValueAsString(entry.getValue());
        } catch (JsonProcessingException e) {
            System.err.println("Error encountered writing object to JSON" + entry.getValue());
            return "{}";
        }
    }

    private static String prettyPrint(Map<String, Quote> quotes) {
        StringBuilder sb = new StringBuilder();
        quotes.forEach((ticker, quote) ->
            sb.append("\n").append(ticker).append("\n")
                .append("Open = $").append(quote.getOpen())
                .append(", Low = $").append(quote.getLow())
                .append(", High = $").append(quote.getHigh())
                .append(", Current = $").append(quote.getCurrent())
                .append("\n")
        );
        return sb.toString();
    }
}
