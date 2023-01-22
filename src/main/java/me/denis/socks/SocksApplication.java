package me.denis.socks;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@JsonSerialize
public class SocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocksApplication.class, args);
    }

}
