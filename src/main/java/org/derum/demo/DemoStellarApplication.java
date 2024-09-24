package org.derum.demo;

import lombok.NoArgsConstructor;
import org.derum.demo.services.StellarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.stellar.sdk.KeyPair;

import java.io.IOException;

@SpringBootApplication
public class DemoStellarApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoStellarApplication.class, args);
    }
}