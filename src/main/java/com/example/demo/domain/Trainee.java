package com.example.demo.domain;


import com.example.demo.exception.ErrorMsg;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = ErrorMsg.TRAINEE_NAME_NOT_EMPTY)
    private String name;
    @NotEmpty(message = ErrorMsg.TRAINEE_OFFICE_NOT_EMPTY)
    private String office;
    @NotEmpty(message = ErrorMsg.TRAINEE_EMAIL_NOT_EMPTY)
    @Email(message = ErrorMsg.TRAINEE_EMAIL_FORMAT_NOT_VALID)
    private String email;
    @NotNull(message = ErrorMsg.TRAINEE_GITHUB_NOT_EMPTY)
    private String github;
    @NotEmpty(message = ErrorMsg.TRAINEE_ZOOM_ID_NOT_EMPTY)
    private String zoomId;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private TrainGroup trainGroup;

}
