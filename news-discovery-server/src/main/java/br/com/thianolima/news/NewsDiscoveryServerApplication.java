package br.com.thianolima.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NewsDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsDiscoveryServerApplication.class, args);
    }

}
