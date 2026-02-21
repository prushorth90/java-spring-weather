
package com.example.demo.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.WeatherResponse;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public WeatherResponse getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        
        // RestTemplate automatically injects the 'city' and 'apiKey' variables into the URL placeholders
        return restTemplate.getForObject(apiUrl, WeatherResponse.class, city, apiKey);
    }
}