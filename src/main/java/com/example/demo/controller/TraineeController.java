package com.example.demo.controller;


import com.example.demo.domain.Trainee;
import com.example.demo.service.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/trainees")
public class TraineeController {
    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping("")
    public ResponseEntity<Trainee> addTrainee(@Valid @RequestBody Trainee trainee){
        Trainee respTrainee = traineeService.addTrainee(trainee);
        return ResponseEntity.status(HttpStatus.CREATED).body(respTrainee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTrainee(@PathVariable int id) {
        traineeService.deleteTrainee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
