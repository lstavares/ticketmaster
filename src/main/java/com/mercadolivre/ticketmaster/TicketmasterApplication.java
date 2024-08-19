package com.mercadolivre.ticketmaster;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

import static org.slf4j.MDC.put;
import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class TicketmasterApplication {

	public static void main(String[] args) {
		put("application", "TicketMaster");;
		run(TicketmasterApplication.class, args);
	}

}
