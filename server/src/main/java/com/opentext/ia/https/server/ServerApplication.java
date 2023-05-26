package com.opentext.ia.https.server;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServerApplication {

	@RestController
	public static class IndexController {

		@GetMapping("/")
		public String index() {
			return "Hello World @ " + LocalTime.from(Instant.now().atZone(ZoneId.systemDefault()));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
