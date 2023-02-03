package com.example.accessingdatajpa;

import com.example.accessingdatajpa.model.Customer;
import com.example.accessingdatajpa.model.Portfolio;
import com.example.accessingdatajpa.repository.CustomerRepository;
import com.example.accessingdatajpa.repository.PortfolioRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class AccessingDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository customers, PortfolioRepository portfolios) {
		return (args) -> {
			customers.save(customer("Jack", "Bauer"));
			customers.save(customer("Chloe", "O'Brian"));
			customers.save(customer("Kim", "Bauer"));
			customers.save(customer("David", "Palmer"));
			customers.save(customer("Michelle", "Dessler"));

			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : customers.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			Optional<Customer> customer = customers.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.orElseThrow().toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			customers.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));

			log.info("Customer found with id(1L):");
			log.info("--------------------------------------------");
			Customer customer1 = customers.findById(1L).orElseThrow();
			log.info(customer1.toString());
			Customer black = customer1.lastName("Black");
			log.info("Set lastname('Black'): {}", customer1);
			log.info("{} == {}? {}", customer1, black, customer1 == black);

			log.info("Customer re-fetched with id(1L):");
			log.info("--------------------------------------------");
			Customer customer1Again = customers.findById(1L).orElseThrow();
			log.info("{} == {}? {}", customer1, customer1Again, customer1 == customer1Again);

			log.info("Saving lastname");
			log.info("--------------------------------------------");
			customers.save(customer1);
			log.info("{} == {}", customer1, customer1Again);
			log.info("");
			log.info("Create a portfolio named '24'");
			log.info("--------------------------------------------");
			Portfolio portfolio24 = portfolios.save(portfolio("24"));
			log.info("{}", portfolio24);
			log.info("");
			log.info("Add customers to portfolio");
			log.info("--------------------------------------------");
			for (Customer c : customers.findAll()) {
				c.portfolio(portfolio24);
				customers.save(c);
				log.info(c.toString());
			}
			log.info("Is the portfolio updated?");
			log.info("--------------------------------------------");
			log.info(portfolio24.toString());
			log.info("");
			log.info("Fetch the portfolio");
			log.info("--------------------------------------------");
			Portfolio portfolio24_2 = portfolios.findByName("24").stream().findFirst().orElseThrow();
			log.info(portfolio24_2.toString());
			log.info("");
			log.info("Portfolio's customers");
			for (Customer c : portfolio24_2.customers()) {
				log.info(c.toString());
			}
		};
	}

	private static Customer customer(String firstName, String lastName) {
		return Customer.builder().firstName(firstName).lastName(lastName).build();
	}

	private static Portfolio portfolio(String name) {
		return Portfolio.builder().name(name).build();
	}
}
