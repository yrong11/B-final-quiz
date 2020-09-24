package com.example.demo.controller;

import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("")
    public ResponseEntity<Trainer> addTrainee(@Valid @RequestBody Trainer trainer){
        Trainer respTrainer = trainerService.addTrainer(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(respTrainer);
    }}
