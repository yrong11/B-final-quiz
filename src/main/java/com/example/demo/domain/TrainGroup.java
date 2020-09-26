package com.example.demo.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TrainGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    private int flag;
    //TODO GTB：思考一下，group这边需要维护这两个list吗? @OneToMany和@ManyToOne是可以分开使用的，不一定非要成对出现。
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "trainGroup")
    private List<Trainee> trainees;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "trainGroup")
    private List<Trainer> trainers;


}
