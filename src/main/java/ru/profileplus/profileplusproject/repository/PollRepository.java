package ru.profileplus.profileplusproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.profileplus.profileplusproject.model.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
