package br.com.aceleradevsp.squad2.mapfood.motosimulator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;

@Configuration
public class SchedulerConfiguration {

    @Bean
    Timer timer() {
        return new Timer("Timer");
    }
}
