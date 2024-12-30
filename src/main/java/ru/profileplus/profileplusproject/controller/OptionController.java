package ru.profileplus.profileplusproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.profileplus.profileplusproject.model.Option;
import ru.profileplus.profileplusproject.service.OptionService;
import ru.profileplus.profileplusproject.service.UserSelectedOptionService;

import java.util.List;

@RestController
@RequestMapping("api/platform/polls/questions/options")
public class OptionController {
    private final OptionService optionService;
    private final UserSelectedOptionService selectedOptionService;

    public OptionController(OptionService optionService, UserSelectedOptionService selectedOptionService) {
        this.optionService = optionService;
        this.selectedOptionService = selectedOptionService;
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
    public void vote(@PathVariable Long optionId, Authentication authentication) {
        optionService.vote(optionId);
        selectedOptionService.recordSelectedOption(authentication.getName(), optionId);
    }
}
