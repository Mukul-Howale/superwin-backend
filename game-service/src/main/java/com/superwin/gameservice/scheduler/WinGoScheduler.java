package com.superwin.gameservice.scheduler;

import com.superwin.gameservice.model.WinGoSession;
import com.superwin.gameservice.repository.WinGoSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@AllArgsConstructor
public class WinGoScheduler {

    private WinGoSessionRepository winGoSessionRepository;

    private static final Long INITIAL_TOTAL_AMOUNT = 0L;
    private static final Long INITIAL_MINORITY_AMOUNT = 0L;
    private static final Long INITIAL_MAJORITY_AMOUNT = 0L;
    private static final Integer INITIAL_NUMBER = -1;

    // Cron expression syntax which runs every 30 seconds
    // Runs at 0s, 30s, 60s, ...
    @Scheduled(cron = "*/30 * * * * *")
    public void createSession() {
        try {
            WinGoSession winGoSession = WinGoSession.builder()
                    .id(UUID.randomUUID())
                    .totalAmount(INITIAL_TOTAL_AMOUNT)
                    .minorityAmount(INITIAL_MINORITY_AMOUNT)
                    .majorityAmount(INITIAL_MAJORITY_AMOUNT)
                    .number(INITIAL_NUMBER)
                    .color(null)
                    .size(null)
                    .build();
            winGoSessionRepository.save(winGoSession);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Runs at 25s, 55s, 85s, ...

    @Scheduled(cron = "25/30 * * * * *")
    public void lastRun(){
        try{
            // using game id get all the bets data
            // segregate data account to the parameters (e.g. big, green, etc.)

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void calculationAlgo(){

    }

    private void majoritySelectionAlgo(){

    }

}
