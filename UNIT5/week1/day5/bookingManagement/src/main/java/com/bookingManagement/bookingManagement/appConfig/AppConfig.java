package com.bookingManagement.bookingManagement.appConfig;

import com.bookingManagement.bookingManagement.Bean.Catalogue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean("Catalogue")
    public Catalogue catalogue() {
        return new Catalogue();
    }


}
