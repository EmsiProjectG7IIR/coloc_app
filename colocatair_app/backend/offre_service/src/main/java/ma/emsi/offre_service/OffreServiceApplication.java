package ma.emsi.offre_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OffreServiceApplication {
        public static void main(String[] args) {
        SpringApplication.run(OffreServiceApplication.class, args);
    }

}
