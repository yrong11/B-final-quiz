package com.example.demo.service;

import com.example.demo.domain.Trainee;
import com.example.demo.exception.ErrorMsg;
import com.example.demo.exception.TraineeNotExistException;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public Trainee addTrainee(Trainee trainee) {
        return traineeRepository.save(trainee);
    }

    public void deleteTrainee(long id) {
        checkTraineeExist(id);
        traineeRepository.deleteById(id);

    }

    private void checkTraineeExist(long id) {
        if (!traineeRepository.existsById(id)){
            throw new TraineeNotExistException(String.format(ErrorMsg.TRAINEE_NOT_EXIST, id));
        }
    }
}
