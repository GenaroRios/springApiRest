package com.example.springApiRest;

import com.example.springApiRest.entities.Book;
import com.example.springApiRest.entities.PersonEntity;
import com.example.springApiRest.repositories.BookRepository;
import com.example.springApiRest.repositories.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class SpringApiRestApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringApiRestApplication.class, args);

		BookRepository bRepository = context.getBean(BookRepository.class);
		PersonRepository personRepository = context.getBean(PersonRepository.class);

		PersonEntity autor = new PersonEntity(38, new ArrayList<Book>(), "James", "Clear");

		Book libroHA = new Book("Habitos Atomicos", autor, 300, 30.000, LocalDate.of(2002, 12, 17), true);
		Book libro = new Book("Habitos SubAtomicos", autor, 400, 60.000, LocalDate.of(2012, 12, 17), true);

		autor.addBooks(libroHA);
		autor.addBooks(libro);
		personRepository.save(autor);

		bRepository.save (libroHA);
		bRepository.save (libro);


	}

}
