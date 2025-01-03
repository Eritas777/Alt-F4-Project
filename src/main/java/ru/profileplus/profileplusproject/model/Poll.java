package ru.profileplus.profileplusproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String creatorEmail;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<Question> questions;
}
