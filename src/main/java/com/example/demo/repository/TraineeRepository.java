package com.example.demo.repository;

import com.example.demo.domain.Trainee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeRepository extends CrudRepository<Trainee, Long> {
    @Override
    List<Trainee> findAll();
    List<Trainee> findAllByTrainGroupIsNotNull();
    List<Trainee> findAllByTrainGroupIsNull();

}
