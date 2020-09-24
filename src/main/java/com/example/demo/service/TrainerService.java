package com.example.demo.service;


import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.exception.ErrorMsg;
import com.example.demo.exception.NotExistException;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public void deleteTrainer(long id) {
        checkTraineeExist(id);
        trainerRepository.deleteById(id);

    }

    public List<Trainer> getTrainers(String grouped) {
        if (grouped == null) {
            return trainerRepository.findAll();
        }
        if (grouped.equals("false")){
            return trainerRepository.findAllByTrainGroupIsNull();
        }
        return trainerRepository.findAllByTrainGroupIsNotNull();
    }

    private void checkTraineeExist(long id) {
        if (!trainerRepository.existsById(id)){
            throw new NotExistException(String.format(ErrorMsg.TRAINER_NOT_EXIST, id));
        }
    }


}
