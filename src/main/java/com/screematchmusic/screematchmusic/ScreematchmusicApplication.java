package com.screematchmusic.screematchmusic;

import com.screematchmusic.screematchmusic.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreematchmusicApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreematchmusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibirMenu();
	}


}
