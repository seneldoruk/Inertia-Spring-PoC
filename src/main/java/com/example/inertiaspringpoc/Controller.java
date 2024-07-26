package com.example.inertiaspringpoc;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class Controller {
    CounterRepository repository;

    public Controller(CounterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public void root(HttpServletResponse res) throws IOException {
        res.sendRedirect("/1");
    }

    @GetMapping("/{id}")
    public void renderCounter(@PathVariable("id") Integer id) throws IOException {
        var counter = repository.findById(id).get();
        Inertia.render("Counter", counter);
    }

    @PostMapping("/{id}")
    public void increaseCounter(@PathVariable("id") Integer id) throws IOException {
        var counter = repository.findById(id).get();
        counter.setValue(counter.getValue() + 1);
        repository.save(counter);
        Inertia.render("Counter", counter);
    }

    @DeleteMapping("/{id}")
    public void decreaseCounter(@PathParam("id") Integer id) throws IOException {
        var counter = repository.findById(id).get();
        var newValue = counter.getValue() > 0 ? counter.getValue() - 1 : 0;
        counter.setValue(newValue);
        repository.save(counter);
        Inertia.render("Counter", counter);
    }


}
