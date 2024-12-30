package ru.profileplus.profileplusproject.service;

import org.springframework.stereotype.Service;
import ru.profileplus.profileplusproject.model.Question;
import ru.profileplus.profileplusproject.model.UserQuestionAnswer;
import ru.profileplus.profileplusproject.repository.QuestionRepository;
import ru.profileplus.profileplusproject.repository.UserQuestionAnswerRepository;

import java.time.LocalDateTime;

@Service
public class UserQuestionAnswerService {

    private final UserQuestionAnswerRepository repository;
    private final QuestionRepository questionRepository;

    public UserQuestionAnswerService(UserQuestionAnswerRepository repository, QuestionRepository questionRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
    }

    public boolean hasUserAnsweredQuestion(String userEmail, Long questionId) {
        return repository.existsByUserIdAndQuestionId(userEmail, questionId);
    }

    public void recordAnswer(String userEmail, Long questionId) {
        if (!hasUserAnsweredQuestion(userEmail, questionId)) {
            UserQuestionAnswer answer = new UserQuestionAnswer();
            answer.setUserEmail(userEmail);
            Question question = questionRepository.findById(questionId).get();
            answer.setQuestion(question);
            answer.setAnsweredAt(LocalDateTime.now());
            repository.save(answer);
        }
    }
}
