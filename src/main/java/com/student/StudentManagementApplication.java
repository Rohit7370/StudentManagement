package com.student;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "StudentManagement Open Api Documentation",
				version="1.0.0",
				description = "Documentation related to Student and his address and result...!!"
		),
		servers=@Server(
				url="http://localhost:8080",
				description = "Student open api url...!!"
		)

)
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}
}
