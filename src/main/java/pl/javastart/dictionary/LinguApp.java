package pl.javastart.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LinguApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LinguApp.class, args);
        LinguController linguController = context.getBean(LinguController.class);
        linguController.mainLoop();
    }

}
