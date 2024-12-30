package ru.profileplus.profileplusproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.profileplus.profileplusproject.model.UserPollVisit;

public interface UserPollVisitRepository extends JpaRepository<UserPollVisit, Long> {
    boolean existsByUserIdAndPollId(String userEmail, Long pollId);
}
