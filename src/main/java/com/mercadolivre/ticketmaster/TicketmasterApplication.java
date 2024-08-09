package com.mercadolivre.ticketmaster;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class TicketmasterApplication {

	public static void main(String[] args) {
		run(TicketmasterApplication.class, args);
	}

}
