package ru.profileplus.profileplusproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.profileplus.profileplusproject.model.UserSelectedOption;

import java.util.List;

public interface UserSelectedOptionRepository extends JpaRepository<UserSelectedOption, Long> {
    List<UserSelectedOption> findByUserEmailAndOptionId(String userEmail, Long optionId);
}
