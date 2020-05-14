package com.batchcsvpostgresql;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringBatchCsvPsqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchCsvPsqlApplication.class, args);
    }

}
