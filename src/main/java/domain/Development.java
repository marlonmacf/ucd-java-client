package domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Development {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "development");
        SpringApplication.run(Development.class, args);
    }
}
