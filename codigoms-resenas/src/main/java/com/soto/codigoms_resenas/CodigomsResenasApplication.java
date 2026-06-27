package com.soto.codigoms_resenas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CodigomsResenasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodigomsResenasApplication.class, args);
	}

}
