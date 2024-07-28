package com.example.inertiaspringpoc;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class Controller {
    private final CounterRepository repository;

    public Controller(CounterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public void root(HttpServletResponse res) throws IOException {
        res.sendRedirect("/counter/1");
    }

    @GetMapping("counter/{id}")
    public void renderCounter(@PathVariable("id") Integer id) throws IOException {
        var counter = repository.findById(id).orElseThrow();
        Inertia.render("Counter", counter);
    }

    @PostMapping("counter/{id}")
    public void increaseCounter(@PathVariable("id") Integer id) throws IOException {
        var counter = repository.findById(id).orElseThrow();
        counter.setValue(counter.getValue() + 1);
        repository.save(counter);
        Inertia.render("Counter", counter);
    }

    @DeleteMapping("counter/{id}")
    public void decreaseCounter(@PathVariable("id") Integer id) throws IOException {
        var counter = repository.findById(id).orElseThrow();
        var newValue = counter.getValue() > 0 ? counter.getValue() - 1 : 0;
        counter.setValue(newValue);
        repository.save(counter);
        Inertia.render("Counter", counter);
    }


}
