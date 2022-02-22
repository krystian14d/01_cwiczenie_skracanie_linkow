package pl.javastart.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.logging.Logger;

@SpringBootApplication
public class LinguApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LinguApp.class, args);
        LinguController linguController = context.getBean(LinguController.class);
        linguController.mainLoop();
    }

    @Bean
    Scanner scanner(){
        return new Scanner(System.in);
    }
}
