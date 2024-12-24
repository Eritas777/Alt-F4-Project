package ru.profileplus.profileplusproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.profileplus.profileplusproject.model.Poll;
import ru.profileplus.profileplusproject.service.PollService;

import java.util.List;

@RestController
@RequestMapping("api/platform/polls")
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping("/add")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        return ResponseEntity.ok(pollService.createPoll(poll));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        return pollService.getPollById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public void deletePoll(@RequestBody Long id) {
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
}
