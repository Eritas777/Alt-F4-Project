package ru.profileplus.profileplusproject.service;

import org.springframework.stereotype.Service;
import ru.profileplus.profileplusproject.model.Option;
import ru.profileplus.profileplusproject.model.Poll;
import ru.profileplus.profileplusproject.repository.PollRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);
    }

    public void deletePoll(Long id) {
        pollRepository.delete(pollRepository.findById(id).get());
    }

    public List<Poll> showAllPolls() {
        return pollRepository.findAll();
    }

    public Poll updatePoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getPollsByUserEmail(String userEmail) { return pollRepository.findByCreatorEmail(userEmail); }
}
