package io.credable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;

@SpringBootApplication
@EnablePrometheusEndpoint
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
