package com.example.demo.controller;

import com.example.demo.domain.TrainGroup;
import com.example.demo.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/auto-grouping")
    public ResponseEntity<List<TrainGroup>> grouping() {
        List<TrainGroup> trainGroups = groupService.grouping();
        return ResponseEntity.ok(trainGroups);
    }

    @GetMapping("")
    public ResponseEntity<List<TrainGroup>> getGroups() {
        List<TrainGroup> trainGroups = groupService.getGroups();
        return ResponseEntity.ok(trainGroups);
    }
}
