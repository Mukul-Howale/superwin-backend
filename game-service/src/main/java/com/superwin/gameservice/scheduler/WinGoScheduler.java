package com.superwin.gameservice.scheduler;

import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.GameSessionStatus;
import com.superwin.gameservice.enums.Size;
import com.superwin.gameservice.exception.GeneralException;
import com.superwin.gameservice.model.WinGoSession;
import com.superwin.gameservice.repository.WinGoSessionRepository;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    @SchedulerLock(name = "create_session", lockAtMostFor = "30s", lockAtLeastFor = "5s")
    public void createSession() {
        try {
            WinGoSession winGoSession = WinGoSession.builder()
                    .id(UUID.randomUUID())
                    .totalAmount(INITIAL_TOTAL_AMOUNT)
                    .minorityAmount(INITIAL_MINORITY_AMOUNT)
                    .majorityAmount(INITIAL_MAJORITY_AMOUNT)
                    .number(INITIAL_NUMBER)
                    .color(Color.INITIAL_COLOR)
                    .size(Size.INITIAL_SIZE)
                    .sessionStatus(GameSessionStatus.ACTIVE)
                    .build();
            winGoSessionRepository.save(winGoSession);
        } catch (Exception e) {
            throw new GeneralException("Unhandled Exception: void createSession(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 25th second for 5 seconds of 30 seconds cycle
    // Runs at 25s, 55s, 85s, ...
    @Scheduled(cron = "25/30 * * * * *")
    @SchedulerLock(name = "last_run", lockAtMostFor = "5s", lockAtLeastFor = "1s")
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
