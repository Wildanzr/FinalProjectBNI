package com.wildannn.poststat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PostStatApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostStatApplication.class, args);
	}

}
