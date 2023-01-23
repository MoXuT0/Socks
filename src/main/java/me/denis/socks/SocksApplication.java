package me.denis.socks;

import lombok.extern.jackson.Jacksonized;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Jacksonized
public class SocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocksApplication.class, args);
    }

}
