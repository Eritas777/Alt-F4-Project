package ru.profileplus.profileplusproject.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.profileplus.profileplusproject.model.Poll;
import ru.profileplus.profileplusproject.model.Question;
import ru.profileplus.profileplusproject.repository.PollRepository;
import ru.profileplus.profileplusproject.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final PollRepository pollRepository;

    public QuestionService(QuestionRepository questionRepository, PollRepository pollRepository) {
        this.questionRepository = questionRepository;
        this.pollRepository = pollRepository;
    }

    // Логика добавления вопроса к определённому опросу
    public Question addQuestionToPoll(Long pollId, Question question) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));
        question.setPoll(poll);
        return questionRepository.save(question);
    }

    // Получение всех вопросов для опроса
    public List<Question> getQuestionsByPollId(Long pollId) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));
        return questionRepository.findAllByPoll(poll);
    }

    public Question getQuestion(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    public void deleteQuestionFromPoll(Long questionId) {
        questionRepository.deleteById(questionId);
    }


}
