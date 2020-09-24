package com.example.demo.controller;

import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin
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
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainee(@PathVariable long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("")
    public ResponseEntity<List<Trainer>> getTrainees(@PathParam("grouped") String grouped) {
        List<Trainer> trainers = trainerService.getTrainers(grouped);
        return ResponseEntity.ok(trainers);
    }
}
