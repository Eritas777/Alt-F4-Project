package ru.profileplus.profileplusproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.profileplus.profileplusproject.model.Poll;
import ru.profileplus.profileplusproject.service.PollService;
import ru.profileplus.profileplusproject.service.UserPollVisitService;
import ru.profileplus.profileplusproject.service.UserQuestionAnswerService;
import ru.profileplus.profileplusproject.service.UserSelectedOptionService;

import java.util.List;

@RestController
@RequestMapping("api/platform/polls")
public class PollController {
    private final PollService pollService;
    private final UserPollVisitService pollVisitService;

    public PollController(PollService pollService, UserPollVisitService pollVisitService) {
        this.pollService = pollService;
        this.pollVisitService = pollVisitService;
    }

    @PostMapping("/add")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll, Authentication authentication) {
        poll.setCreatorEmail(authentication.getName());
        return ResponseEntity.ok(pollService.createPoll(poll));
    }

    @GetMapping("/my-polls")
    public ResponseEntity<List<Poll>> getMyPolls(Authentication authentication) {
        String userEmail = authentication.getName(); // Получение email текущего пользователя
        List<Poll> polls = pollService.getPollsByUserEmail(userEmail);
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        return pollService.getPollById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public void deletePoll(@PathVariable Long id) {
        pollService.deletePoll(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Poll> updatePoll(@RequestBody Poll poll) {
        return ResponseEntity.ok(pollService.updatePoll(poll));
    }

    @GetMapping("/poll-list")
    public ResponseEntity<List<Poll>> showAllPolls() {
        return ResponseEntity.ok(pollService.showAllPolls());
    }

    @PostMapping("/{pollId}/visit")
    public ResponseEntity<String> recordVisit(@PathVariable Long pollId, Authentication authentication) {
        pollVisitService.recordVisit(authentication.getName(), pollId);
        return ResponseEntity.ok("Visit recorded.");
    }
}
