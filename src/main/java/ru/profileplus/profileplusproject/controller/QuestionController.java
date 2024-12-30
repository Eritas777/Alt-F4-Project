package ru.profileplus.profileplusproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.profileplus.profileplusproject.model.Question;
import ru.profileplus.profileplusproject.service.QuestionService;
import ru.profileplus.profileplusproject.service.UserQuestionAnswerService;

import java.util.List;

@RestController
@RequestMapping("api/platform/polls/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final UserQuestionAnswerService questionAnswerService;

    public QuestionController(QuestionService questionService, UserQuestionAnswerService questionAnswerService) {
        this.questionService = questionService;
        this.questionAnswerService = questionAnswerService;
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

    @PostMapping("/{questionId}/answer")
    public ResponseEntity<String> recordAnswer(@PathVariable Long questionId, Authentication authentication) {
        if (questionAnswerService.hasUserAnsweredQuestion(authentication.getName(), questionId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Question already answered.");
        }
        questionAnswerService.recordAnswer(authentication.getName(), questionId);
        return ResponseEntity.ok("Answer recorded.");
    }
}
