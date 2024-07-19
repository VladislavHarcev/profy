package com.profy.profuru;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "calendar.client")
@Getter
@Setter
public class CalendarProperties {
    private String url;
    private String path;

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
