package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<Pizza>> index() {
        List<Pizza> result = pizzaService.findAllPizzas();

        if (result.isEmpty()) {
            return new ResponseEntity<List<Pizza>>(result, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<Pizza>>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable("id") Integer id) {
        Optional<Pizza> result = pizzaService.findPizzaById(id);

        if (result.isPresent()) {
            return new ResponseEntity<Pizza>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Pizza>(result.get(), HttpStatus.NOT_FOUND);
        }
    }

}
