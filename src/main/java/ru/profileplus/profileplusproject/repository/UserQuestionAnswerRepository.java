package ru.profileplus.profileplusproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.profileplus.profileplusproject.model.UserQuestionAnswer;

public interface UserQuestionAnswerRepository extends JpaRepository<UserQuestionAnswer, Long> {
    boolean existsByUserIdAndQuestionId(String userEmail, Long questionId);
}