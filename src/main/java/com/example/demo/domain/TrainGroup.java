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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainGroup")
    private List<Trainee> trainees;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainGroup")
    private List<Trainer> trainers;


}
