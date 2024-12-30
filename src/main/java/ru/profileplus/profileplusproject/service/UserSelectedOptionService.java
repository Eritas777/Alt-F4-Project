package ru.profileplus.profileplusproject.service;

import org.springframework.stereotype.Service;
import ru.profileplus.profileplusproject.model.Option;
import ru.profileplus.profileplusproject.model.UserSelectedOption;
import ru.profileplus.profileplusproject.repository.OptionRepository;
import ru.profileplus.profileplusproject.repository.UserSelectedOptionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserSelectedOptionService {

    private final UserSelectedOptionRepository repository;
    private final OptionRepository optionRepository;

    public UserSelectedOptionService(UserSelectedOptionRepository repository, OptionRepository optionRepository) {
        this.repository = repository;
        this.optionRepository = optionRepository;
    }

    public void recordSelectedOption(String userEmail, Long optionId) {
        UserSelectedOption selectedOption = new UserSelectedOption();
        selectedOption.setUserEmail(userEmail);
        Option option = optionRepository.findById(optionId).get();
        selectedOption.setOption(option);
        selectedOption.setSelectedAt(LocalDateTime.now());
        repository.save(selectedOption);
    }

    public List<UserSelectedOption> getSelectedOptions(String userEmail, Long optionId) {
        return repository.findByUserIdAndOptionId(userEmail, optionId);
    }
}
