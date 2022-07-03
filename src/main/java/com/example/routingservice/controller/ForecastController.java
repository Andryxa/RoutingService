package com.example.routingservice.controller;

import com.example.routingservice.dto.ForecastRequest;
import com.example.routingservice.dto.ForecastResponse;
import com.example.routingservice.transport.ForecastSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ForecastController {

    private final ForecastSender forecastSender;

    @GetMapping("/forecast")
    public ForecastResponse getForecast(@RequestParam Long userId) {
        ForecastResponse forecastResponse = forecastSender.sendAndReceiveForecast(new ForecastRequest(userId));
        log.info("Received response {}", forecastResponse);
        return forecastResponse;
    }
}
