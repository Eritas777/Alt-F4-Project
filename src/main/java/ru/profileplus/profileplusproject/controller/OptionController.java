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

    @PostMapping("/add-single")
    public ResponseEntity<Option> addOption(@RequestBody Long questionId, Option option) {
        return ResponseEntity.ok(optionService.addOptionToQuestion(questionId, option));
    }

    @PostMapping("/add-multiple")
    public void addOptions(Long questionId, List<Option> options) {
        optionService.addOptionsToQuestion(questionId, options);
    }

    @GetMapping("/options-list")
    public ResponseEntity<List<Option>> getOptions(@RequestBody Long questionId) {
        return ResponseEntity.ok(optionService.getOptionsByQuestionId(questionId));
    }

    @PostMapping("/vote/{optionId}")
    public void vote(@PathVariable Long optionId) {
        optionService.vote(optionId);
    }


}
