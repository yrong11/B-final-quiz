package com.example.demo.repository;

import com.example.demo.domain.TrainGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<TrainGroup, Integer> {
    @Override
    List<TrainGroup> findAll();
    void deleteByIdLessThan(long id);
}
