package com.example.inertiaspringpoc;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CounterRepository extends CrudRepository<Counter, Integer> {

}
