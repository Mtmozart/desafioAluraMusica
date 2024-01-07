package com.screematchmusic.screematchmusic;

import com.screematchmusic.screematchmusic.principal.Principal;
import com.screematchmusic.screematchmusic.repository.ArtistaRepository;
import com.screematchmusic.screematchmusic.service.AppServicesMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreematchmusicApplication implements CommandLineRunner{
	@Autowired
	private ArtistaRepository repository;

	public static void main(String[] args) {

		SpringApplication.run(ScreematchmusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AppServicesMain app = new AppServicesMain(repository);
		Principal principal = new Principal(app);
		principal.exibirMenu();
	}


}
