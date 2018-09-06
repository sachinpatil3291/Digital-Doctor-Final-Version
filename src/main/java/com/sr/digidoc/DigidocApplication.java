package com.sr.digidoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.sr.digidoc.util.FileProperties;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableConfigurationProperties(FileProperties.class)
public class DigidocApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigidocApplication.class, args);
	}
}
