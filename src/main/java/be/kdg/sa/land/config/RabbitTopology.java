package be.kdg.sa.land.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopology {

    public static final String DIRECT_EXCHANGE = "land-exchange";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }
}
