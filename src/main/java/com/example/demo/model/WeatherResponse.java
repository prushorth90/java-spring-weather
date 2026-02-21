package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// Ignores any JSON fields from the API that we don't explicitly map below
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private String name;
    
    @JsonProperty("main")
    private Main main;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }

    public static class Main {
        private Double temp;
        private Double humidity;

        // Getters and Setters
        public Double getTemp() { return temp; }
        public void setTemp(Double temp) { this.temp = temp; }
        public Double getHumidity() { return humidity; }
        public void setHumidity(Double humidity) { this.humidity = humidity; }
    }
}