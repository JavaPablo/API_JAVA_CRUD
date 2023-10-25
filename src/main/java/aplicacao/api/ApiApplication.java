package aplicacao.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {




	public static void main(String[] args) {
		System.out.println("----------------------------------"+System.getenv("DATABASE_URL"));
		SpringApplication.run(ApiApplication.class, args);
	}

}
