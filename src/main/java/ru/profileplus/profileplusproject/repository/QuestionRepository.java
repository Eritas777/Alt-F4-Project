package ru.profileplus.profileplusproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.profileplus.profileplusproject.model.Poll;
import ru.profileplus.profileplusproject.model.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByPoll(Poll poll);
}
