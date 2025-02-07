package feral.translate.logic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LogicApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogicApplication.class, args);
	}

}
