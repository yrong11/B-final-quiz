package com.example.demo.domain;


import com.example.demo.exception.ErrorMsg;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = ErrorMsg.TRAINEE_ZOOM_ID_NOT_EMPTY)
    private String zoomId;
}
