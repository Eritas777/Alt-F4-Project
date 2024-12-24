package ru.profileplus.profileplusproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.profileplus.profileplusproject.model.Option;
import ru.profileplus.profileplusproject.model.Question;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findAllByQuestion(Question question);
}
