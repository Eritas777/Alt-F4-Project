package ru.profileplus.profileplusproject.service;

import org.springframework.stereotype.Service;
import ru.profileplus.profileplusproject.model.Poll;
import ru.profileplus.profileplusproject.model.UserPollVisit;
import ru.profileplus.profileplusproject.repository.PollRepository;
import ru.profileplus.profileplusproject.repository.UserPollVisitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserPollVisitService {
    private final UserPollVisitRepository repository;
    private final PollRepository pollRepository;

    public UserPollVisitService(UserPollVisitRepository repository, PollRepository pollRepository) {
        this.repository = repository;
        this.pollRepository = pollRepository;
    }

    public void recordVisit(String userEmail, Long pollId) {
        if (!repository.existsByUserEmailAndPollId(userEmail, pollId)) {
            UserPollVisit visit = new UserPollVisit();
            visit.setUserEmail(userEmail);
            Poll poll = pollRepository.findById(pollId).get();
            visit.setPoll(poll);
            visit.setVisitedAt(LocalDateTime.now());
            repository.save(visit);
        }
    }

    public List<Poll> getVisitedPollsByUserEmail(Long userEmail) {
        return repository.findVisitedPollsByUserEmail(userEmail);
    }
}
