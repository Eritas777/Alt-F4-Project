package ru.profileplus.profileplusproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.profileplus.profileplusproject.model.Poll;
import ru.profileplus.profileplusproject.model.UserPollVisit;

import java.util.List;

public interface UserPollVisitRepository extends JpaRepository<UserPollVisit, Long> {
    boolean existsByUserEmailAndPollId(String userEmail, Long pollId);

    @Query("SELECT v.poll FROM UserPollVisit v WHERE v.userEmail = :userEmail")
    List<Poll> findVisitedPollsByUserEmail(@Param("userEmail") Long userEmail);
}
