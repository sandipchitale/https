package com.opentext.ia.https.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {

	@Configuration
	public static class CLR implements CommandLineRunner {

		private final RestTemplate restTemplate;

		CLR(RestTemplateBuilder restTemplateBuilder, SslBundles sslBundles) {
			this.restTemplate = restTemplateBuilder.setSslBundle(sslBundles.getBundle("client")).build();
		}

		@Override
		public void run(String... args) throws Exception {
			String greetings= restTemplate.getForObject("https://localhost:8080/", String.class);
			System.out.println(greetings);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

}
