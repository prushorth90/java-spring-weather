package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.WeatherResponse;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    public void testGetWeather_Success() {
        // 1. Setup our dummy data
        WeatherResponse mockResponse = new WeatherResponse();
        mockResponse.setName("London");
        WeatherResponse.Main mainInfo = new WeatherResponse.Main();
        mainInfo.setTemp(15.0);
        mockResponse.setMain(mainInfo);

        // Inject the hidden properties normally loaded from application.properties
        ReflectionTestUtils.setField(weatherService, "apiUrl", "http://fake-url.com/{city}");
        ReflectionTestUtils.setField(weatherService, "apiKey", "fake-key");

        // 2. Tell Mockito what to do when RestTemplate is called
        when(restTemplate.getForObject(anyString(), eq(WeatherResponse.class), anyString(), anyString()))
                .thenReturn(mockResponse);

        // 3. Execute the method
        WeatherResponse result = weatherService.getWeather("London");

        // 4. Assert the results are what we expect
        assertNotNull(result);
        assertEquals("London", result.getName());
        assertEquals(15.0, result.getMain().getTemp());
    }
}