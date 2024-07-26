package com.example.inertiaspringpoc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InertiaSpringPoCApplication {

    public static void main(String[] args) {
        SpringApplication.run(InertiaSpringPoCApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(CounterRepository repository) throws Exception {
        return (String[] args) -> {
            repository.save(new Counter(1, 7));
            repository.save(new Counter(2, 12));
        };
    }

}
