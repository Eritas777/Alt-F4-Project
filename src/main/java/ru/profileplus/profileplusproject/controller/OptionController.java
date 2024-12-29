package ru.profileplus.profileplusproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.profileplus.profileplusproject.model.Option;
import ru.profileplus.profileplusproject.model.Question;
import ru.profileplus.profileplusproject.repository.OptionRepository;
import ru.profileplus.profileplusproject.service.OptionService;

import java.util.List;

@RestController
@RequestMapping("api/platform/polls/questions/options")
public class OptionController {
    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping("/add-single/{questionId}")
    public ResponseEntity<Option> addOption(@PathVariable Long questionId, @RequestBody Option option) {
        return ResponseEntity.ok(optionService.addOptionToQuestion(questionId, option));
    }

    @PostMapping("/add-multiple/{questionId}")
    public void addOptions(@PathVariable Long questionId, @RequestBody List<Option> options) {
        optionService.addOptionsToQuestion(questionId, options);
    }

    @GetMapping("/options-list/{questionId}")
    public ResponseEntity<List<Option>> getOptions(@PathVariable Long questionId) {
        return ResponseEntity.ok(optionService.getOptionsByQuestionId(questionId));
    }

    @PostMapping("/vote/{optionId}")
    public void vote(@PathVariable Long optionId) {
        optionService.vote(optionId);
    }


}
