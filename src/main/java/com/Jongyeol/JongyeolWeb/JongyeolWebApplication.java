package com.Jongyeol.JongyeolWeb;

import kr.jongyeol.jaServer.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JongyeolWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JongyeolWebApplication.class, args);
		Server.main(args);
	}

}
