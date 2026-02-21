package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc; // <-- New Import
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.WeatherResponse;
import com.example.demo.service.WeatherService;

// This annotation fires up just the web layer, not the whole server
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherService weatherService;

    @Test
    public void testGetWeatherEndpoint() throws Exception {
        // Setup dummy response
        WeatherResponse mockResponse = new WeatherResponse();
        mockResponse.setName("Seattle");
        
        // Mock the service
        when(weatherService.getWeather("Seattle")).thenReturn(mockResponse);

        // Perform the simulated HTTP request and check the results
        mockMvc.perform(get("/api/weather").param("city", "Seattle"))
                .andExpect(status().isOk()) // Expect a 200 HTTP status
                .andExpect(jsonPath("$.name").value("Seattle")); // Expect JSON output to contain "Seattle"
    }
}