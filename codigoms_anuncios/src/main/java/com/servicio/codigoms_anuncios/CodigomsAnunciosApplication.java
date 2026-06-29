package com.servicio.codigoms_anuncios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients

@SpringBootApplication
public class CodigomsAnunciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodigomsAnunciosApplication.class, args);
	}

}
