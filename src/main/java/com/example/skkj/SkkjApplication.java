package com.example.skkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.skkj.mapper")
public class SkkjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkkjApplication.class, args);
	}
}
