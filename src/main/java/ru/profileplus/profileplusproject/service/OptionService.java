package ru.profileplus.profileplusproject.service;

import org.springframework.stereotype.Service;
import ru.profileplus.profileplusproject.model.Option;
import ru.profileplus.profileplusproject.model.Question;
import ru.profileplus.profileplusproject.repository.OptionRepository;
import ru.profileplus.profileplusproject.repository.QuestionRepository;

import java.util.List;

@Service
public class OptionService {
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    public OptionService(OptionRepository optionRepository, QuestionRepository questionRepository) {
        this.optionRepository = optionRepository;
        this.questionRepository = questionRepository;
    }

    // Логика добавления варианта ответа к вопросу
    public Option addOptionToQuestion(Long questionId, Option option) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        option.setQuestion(question);
        return optionRepository.save(option);
    }

    // Логика голосования за вариант
    public void vote(Long optionId) {
        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new RuntimeException("Option not found"));
        option.setVotes(option.getVotes() + 1);
        optionRepository.save(option);
    }

    // Получение вариантов для определённого вопроса
    public List<Option> getOptionsByQuestionId(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return optionRepository.findAllByQuestion(question);
    }

    public void addOptionsToQuestion(Long questionId, List<Option> options) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        for (Option option : options) {
            option.setQuestion(question);
        }

        optionRepository.saveAll(options);
    }
}
