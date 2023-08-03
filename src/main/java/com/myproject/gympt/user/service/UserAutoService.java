package com.myproject.gympt.user.service;

import com.myproject.gympt.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAutoService {

    private final UserRepository userRepository;
//      @Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 * * * *")
    public void updateGptCount() {
          userRepository.resetAllGptCounts();
      }

}
