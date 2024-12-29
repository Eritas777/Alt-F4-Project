package ru.profileplus.profileplusproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.profileplus.profileplusproject.model.Question;
import ru.profileplus.profileplusproject.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("api/platform/polls/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add/{pollId}")
    public ResponseEntity<Question> addQuestion(@PathVariable Long pollId, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestionToPoll(pollId, question));
    }

    @GetMapping("/question-list/{pollId}")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Long pollId) {
        return ResponseEntity.ok(questionService.getQuestionsByPollId(pollId));
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(questionService.getQuestion(questionId));
    }

    @DeleteMapping("/delete/{questionId}")
    public void deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestionFromPoll(questionId);
    }
}
