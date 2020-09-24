package com.example.demo.repository;

import com.example.demo.domain.Trainee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends CrudRepository<Trainee, Long> {
}
