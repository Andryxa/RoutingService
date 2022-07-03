package com.example.routingservice.transport;

import com.example.routingservice.dto.ForecastRequest;
import com.example.routingservice.dto.ForecastResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForecastSender {
    private static final String FORECAST_RK = "forecast_service.forecast";
    private static final String FORECAST_EXC = "forecastServiceExchange";

    private final RabbitTemplate rabbitTemplate;

    public ForecastResponse sendAndReceiveForecast(final ForecastRequest request) {
        log.info("Sending request to exchange - {} and routing key - {}", FORECAST_EXC, FORECAST_RK);
        return rabbitTemplate.convertSendAndReceiveAsType(FORECAST_EXC,FORECAST_RK, request, ParameterizedTypeReference.forType(ForecastResponse.class));
    }
}
