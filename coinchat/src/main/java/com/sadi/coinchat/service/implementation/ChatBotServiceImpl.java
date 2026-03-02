package com.sadi.coinchat.service.implementation;

import com.sadi.coinchat.dto.CoinDTO;
import com.sadi.coinchat.io.ApiResponse;
import com.sadi.coinchat.service.ChatBotService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ChatBotServiceImpl implements ChatBotService {

    private double doubleConverter(Object value) {

        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        }else if (value instanceof Long) {
            return ((Long) value).doubleValue();
        }else if (value instanceof Double) {
            return (Double)value;
        }
        else throw new  IllegalArgumentException("unsupported data "+value.getClass().getName());



//        private double doubleConverter(Object value) {
//            if (value == null) return 0.0;
//
//            if (value instanceof Number) {
//                return ((Number) value).doubleValue();
//            }
//
//            throw new IllegalArgumentException("Unsupported type: " + value.getClass().getName());
//        }
    }

    public CoinDTO makeApiRequest(String currencyName) throws Exception {

        String url = "https://api.coingecko.com/api/v3/coins/" + currencyName;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url,Map.class);
        Map <String,Object> responseBody = responseEntity.getBody();

        if(responseEntity != null) {
            Map <String,Object> image=(Map<String,Object>)responseBody.get("image");
            Map <String,Object> marketData=(Map<String,Object>)responseBody.get("market_data");

            CoinDTO coinDTO = new CoinDTO();
            coinDTO.setId((String)responseBody.get("id"));
            coinDTO.setCoinName((String)responseBody.get("name"));
            coinDTO.setSymbol((String)responseBody.get("symbol"));
            coinDTO.setImage((String)responseBody.get("large"));

         //   marketData
            coinDTO.setCurrentPrice(doubleConverter(((Map<String,Object>)marketData.get("current_price")).get("usd")));
            coinDTO.setMarketCap(doubleConverter(((Map<String,Object>)marketData.get("market_cap")).get("usd")));
            coinDTO.setMarketCapRank(doubleConverter(((Map<String,Object>)marketData.get("market_cap_rank"))));
            coinDTO.setTotalVolume(doubleConverter(((Map<String,Object>)marketData.get("total_volume")).get("usd")));
            coinDTO.setHigh24h(doubleConverter(((Map<String,Object>)marketData.get("high_24")).get("usd")));
            coinDTO.setLow24h(doubleConverter(((Map<String,Object>)marketData.get("low_24")).get("usd")));
            coinDTO.setPriceChange24h(doubleConverter(((Map<String,Object>)marketData.get("price_change_24"))));
            coinDTO.setPriceChangePercentage24h(doubleConverter(((Map<String,Object>)marketData.get("price_change_percentage_24"))));
            coinDTO.setMarketCapChange24h(doubleConverter(((Map<String,Object>)marketData.get("market_cap_change_24"))));
            coinDTO.setMarketCapChangePercentage24h(doubleConverter(((Map<String,Object>)marketData.get("market_cap_change_percentage_24"))));
            coinDTO.setCirculatingSupply(doubleConverter(((Map<String,Object>)marketData.get("circulating_supply"))));
            coinDTO.setTotalSupply(doubleConverter(((Map<String,Object>)marketData.get("total_supply"))));

            return coinDTO;
        }

        throw new Exception("Coin NOT Found");




    }

    @Override
    public String sendChat(String prompt) {
        return "";
    }

    @Override
    public ApiResponse getCoinDetails(String prompt) {
        return null;
    }
}
