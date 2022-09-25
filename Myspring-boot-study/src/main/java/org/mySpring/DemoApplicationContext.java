package org.mySpring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("org.mySpring.mapper")
public class DemoApplicationContext {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplicationContext.class, args);
	}
}
