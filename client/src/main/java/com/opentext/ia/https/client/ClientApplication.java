package com.opentext.ia.https.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {

	@Configuration
	public static class CLR implements CommandLineRunner {

		private final Environment environment;

		private final RestTemplate restTemplate;

		CLR(Environment environment, RestTemplateBuilder restTemplateBuilder, SslBundles sslBundles) {
			this.environment = environment;
			// Use httpclient5 so that we can log request and response
			restTemplateBuilder = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class);
			if (environment.acceptsProfiles(Profiles.of("https"))) {
				restTemplateBuilder = restTemplateBuilder.setSslBundle(sslBundles.getBundle("client"));
			}
			this.restTemplate = restTemplateBuilder.build();
		}

		@Override
		public void run(String... args) throws Exception {
			String scheme = "http";
			if (environment.acceptsProfiles(Profiles.of("https"))) {
				scheme = "https";
			}
			String greetings= restTemplate.getForObject(scheme + "://localhost:8080/", String.class);
			System.out.println(greetings);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

}
