package com.example.demo.service;

import com.example.demo.domain.TrainGroup;
import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorMsg;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;

    public GroupService(GroupRepository groupRepository, TrainerRepository trainerRepository, TraineeRepository traineeRepository) {
        this.groupRepository = groupRepository;
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
    }

    @Transactional
    public List<TrainGroup> grouping() {
        checkTrainerSizeGreaterThan2();
        List<TrainGroup> groups = generateGroupAndGroupTrainer();
        List<Trainee> trainees = traineeRepository.findAll();
        Collections.shuffle(trainees);
        int groupId = 0;
        while (!trainees.isEmpty()){
            Trainee trainee = trainees.remove(trainees.size() - 1);
            groups.get(groupId).getTrainees().add(trainee);
            trainee.setTrainGroup(groups.get(groupId));
            groupId = ++groupId % groups.size();
        }
        groupRepository.saveAll(groups);
        TrainGroup trainGroup = groupRepository.save(groups.get(0));
        groupRepository.deleteByIdLessThan(trainGroup.getId());
        return groupRepository.findAll();
    }

    //TODO GTB：这个@Transactional是不会生效的，感兴趣可以找找答案，可能后面在项目上也会遇到类似的问题
    //TODO GTB：这个方法可以按模块抽取方法重构
    @Transactional
    public List<TrainGroup> generateGroupAndGroupTrainer() {
        List<Trainer> allTrainers = trainerRepository.findAll();
        Collections.shuffle(allTrainers);
        List<TrainGroup> groups = new ArrayList<>();
        //TODO GTB：Magic number
        for (int i = 0; i < allTrainers.size()/2; i++) {
            TrainGroup trainGroup = TrainGroup.builder().trainers(new ArrayList<>()).trainees(new ArrayList<>()).name(i + 1 + " 组").build();
            allTrainers.get(2 * i).setTrainGroup(trainGroup);
            allTrainers.get(2 * i + 1).setTrainGroup(trainGroup);
            trainGroup.getTrainers().add(allTrainers.get(2 * i));
            trainGroup.getTrainers().add(allTrainers.get(2 * i + 1));
            groups.add(trainGroup);
        }
        if (allTrainers.size() % 2 != 0){
            allTrainers.get(allTrainers.size() - 1).setTrainGroup(null);
            trainerRepository.save(allTrainers.get(allTrainers.size() - 1));
        }
        return groups;
    }

    public void checkTrainerSizeGreaterThan2(){
        if (trainerRepository.count() < 2) {
            throw new BusinessException(ErrorMsg.TRAINER_SIZE_LESS_THAN_TWO);
        }
    }

    public List<TrainGroup> getGroups() {
        return groupRepository.findAll();
    }

    public void updateGroupName(long id, String name) {
        if (!groupRepository.existsById(id)){
            throw new BusinessException(String.format(ErrorMsg.GROUP_NOT_EXIST, id));
        }
        if (groupRepository.existsByName(name)){
            throw new BusinessException(ErrorMsg.GROUP_NAME_REPEAT);
        }
        TrainGroup trainGroup = groupRepository.findById(id).get();
        trainGroup.setName(name);
        groupRepository.save(trainGroup);
    }
}
