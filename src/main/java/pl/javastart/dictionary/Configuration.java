package pl.javastart.dictionary;

import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }
}
