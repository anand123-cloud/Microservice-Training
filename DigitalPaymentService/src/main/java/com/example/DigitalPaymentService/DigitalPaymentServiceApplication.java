package com.example.DigitalPaymentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.example.config.DigitalBankServiceConfiguration;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RibbonClient(name = "banking-client",configuration = DigitalBankServiceConfiguration.class)

public class DigitalPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalPaymentServiceApplication.class, args);
	}

}
