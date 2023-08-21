package com.integration.orderservice.configuration;

import com.integration.orderservice.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfiguration {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


    @Bean
    @LoadBalanced
    public WebClient productWebClient() {
        return WebClient.builder()
                .baseUrl("http://product-service/api/v1/product/")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public ProductClient productClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(productWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(ProductClient.class);
    }
}

