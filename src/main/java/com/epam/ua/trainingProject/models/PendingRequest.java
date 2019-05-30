package com.epam.ua.trainingProject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pending_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PendingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String experience;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne
    private User user;
}
