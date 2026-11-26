package com.github.wkh131.hotelbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingApplication.class, args);
    }

    //    @Bean
    //    DataSource getDataSource() {
    //        HikariConfig config = new HikariConfig();
    //        config.setJdbcUrl("jdbc:hsqldb:hsql://localhost:");
    //        config.setUsername("sa");
    //        config.setPassword("");
    //        return new HikariDataSource(config);
    //    }
}
