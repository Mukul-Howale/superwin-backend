package com.superwin.gameservice.config;

import com.superwin.gameservice.client.ProfileClient;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@AllArgsConstructor
public class WebClientConfig {

    private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    @Bean
    public WebClient profileWebClient(){
        return WebClient.builder()
                .baseUrl("http://profile-service")
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }

    @Bean
    public ProfileClient profileClient(){
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(profileWebClient()))
                .build();

        return httpServiceProxyFactory.createClient(ProfileClient.class);
    }
}
