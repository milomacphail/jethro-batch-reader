package com.batchcsvpostgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringBatchCsvPsqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchCsvPsqlApplication.class, args);
    }

}
