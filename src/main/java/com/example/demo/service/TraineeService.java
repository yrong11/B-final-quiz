package com.example.demo.service;

import com.example.demo.domain.Trainee;
import com.example.demo.exception.ErrorMsg;
import com.example.demo.exception.NotExistException;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new NotExistException(String.format(ErrorMsg.TRAINEE_NOT_EXIST, id));
        }
    }

    public List<Trainee> getTrainees(String grouped) {
        if (grouped == null) {
            return traineeRepository.findAll();
        }
        if (grouped.equals("false")){
            return traineeRepository.findAllByTrainGroupIsNull();
        }
        return traineeRepository.findAllByTrainGroupIsNotNull();
    }
}
