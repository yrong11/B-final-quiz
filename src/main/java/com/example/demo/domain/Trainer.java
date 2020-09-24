package com.example.demo.domain;

import com.example.demo.exception.ErrorMsg;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = ErrorMsg.TRAINER_NAME_NOT_EMPTY)
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private TrainGroup trainGroup;

}
