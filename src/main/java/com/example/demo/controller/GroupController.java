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

    //TODO GTB：如果没有定制化的返回需求，可以省略ResponseEntity
    @PostMapping("/auto-grouping")
    public ResponseEntity<List<TrainGroup>> grouping() {
        List<TrainGroup> trainGroups = groupService.grouping();
        return ResponseEntity.ok(trainGroups);
    }
    //TODO GTB: 如果path为空，可以不写""
    @GetMapping("")
    public ResponseEntity<List<TrainGroup>> getGroups() {
        List<TrainGroup> trainGroups = groupService.getGroups();
        return ResponseEntity.ok(trainGroups);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateGroupName(@PathVariable long id, @RequestBody TrainGroup group){
        groupService.updateGroupName(id, group.getName());
        return ResponseEntity.ok().build();
    }
}
