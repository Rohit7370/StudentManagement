package com.student;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "StudentManagement Open Api Documentation",
				version="1.0.0",
				description = "Documentation related to Student and his address and result...!!",
				contact = @Contact(name = "Support Team", email = "rohit.k@brickredsys.in")
		),
		servers=@Server(
				url="http://localhost:8080",
				description = "Student open api url...!!"
		)

)
@Tag(name = "Student", description = "Student-related operations")
public class StudentManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
