package com.example.springApiRest;

import com.example.springApiRest.entities.Book;
import com.example.springApiRest.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class SpringApiRestApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringApiRestApplication.class, args);

		BookRepository bRepository = context.getBean(BookRepository.class);

		Book libroHA = new Book("Habitos Atomicos", "James Clear", 300, 30.000, LocalDate.of(2002, 12, 17), true);
		Book libro = new Book("Habitos SubAtomicos", "James Clear", 400, 60.000, LocalDate.of(2012, 12, 17), true);

		bRepository.save (libroHA);
		bRepository.save (libro);
	}

}
