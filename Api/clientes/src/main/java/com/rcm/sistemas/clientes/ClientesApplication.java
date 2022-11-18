package com.rcm.sistemas.clientes;

import com.rcm.sistemas.clientes.domain.model.entity.Cliente;
import com.rcm.sistemas.clientes.domain.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class ClientesApplication {

	@Autowired
	ClienteRepository repository;

	@Bean
	public CommandLineRunner rus(){
		return args -> {
			Cliente cliente = Cliente.builder().cpf("67380700715").nome("Alvaro Cardoso Miranda").build();
			repository.save(cliente);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}

}
