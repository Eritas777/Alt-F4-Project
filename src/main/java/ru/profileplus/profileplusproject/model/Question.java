package ru.profileplus.profileplusproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    @JsonIgnore
    private Poll poll;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options;
}
