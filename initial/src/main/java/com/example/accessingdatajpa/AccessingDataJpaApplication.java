package com.example.accessingdatajpa;

import com.example.accessingdatajpa.model.Customer;
import com.example.accessingdatajpa.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
@Slf4j
public class AccessingDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			repository.save(customer("Jack", "Bauer"));
			repository.save(customer("Chloe", "O'Brian"));
			repository.save(customer("Kim", "Bauer"));
			repository.save(customer("David", "Palmer"));
			repository.save(customer("Michelle", "Dessler"));

			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			Optional<Customer> customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.orElseThrow().toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));

			log.info("Customer found with id(1L):");
			log.info("--------------------------------------------");
			Customer customer1 = repository.findById(1L).orElseThrow();
			log.info(customer1.toString());
			Customer black = customer1.lastName("Black");
			log.info("Set lastname('Black'): {}", customer1);
			log.info("{} == {}? {}", customer1, black, customer1 == black);

			log.info("Customer re-fetched with id(1L):");
			log.info("--------------------------------------------");
			Customer customer1Again = repository.findById(1L).orElseThrow();
			log.info("{} == {}? {}", customer1, customer1Again, customer1 == customer1Again);

			log.info("Saving lastname");
			log.info("--------------------------------------------");
			repository.save(customer1);
			log.info("{} == {}", customer1, customer1Again);


		};
	}

	private static Customer customer(String firstName, String lastName) {
		return Customer.builder().firstName(firstName).lastName(lastName).build();
	}
}
