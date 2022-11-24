package com.example.phonems;

import com.example.phonems.frontEnd.FrontEnd;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhonemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonemsApplication.class, args);
		/*FrontEnd frontEnd = new FrontEnd();
		frontEnd.display();*/
	}

}
