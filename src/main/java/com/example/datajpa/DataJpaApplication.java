package com.example.datajpa;

import com.example.datajpa.model.Customer;
import com.example.datajpa.repo.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataJpaApplication {
	private static final Logger log = LoggerFactory.getLogger(DataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository customerRepository) {
		return (args) -> {
			customerRepository.save(new Customer("Anton", "Kuluk"));
			customerRepository.save(new Customer("Polina", "Kulyk"));
			customerRepository.save(new Customer("Oleg", "Kulyk"));

			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : customerRepository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			Customer customer = customerRepository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			log.info("Customer found with findByLastName('Kulyk'):");
			log.info("--------------------------------------------");
			for (Customer customer1 : customerRepository.findByLastName("Kulyk")) {
				log.info(customer1.toString());
			}
		};
	}
}
