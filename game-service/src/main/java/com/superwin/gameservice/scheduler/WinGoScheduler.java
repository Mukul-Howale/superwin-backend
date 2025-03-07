package com.superwin.gameservice.scheduler;

import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.GameSessionStatus;
import com.superwin.gameservice.enums.Size;
import com.superwin.gameservice.enums.Time;
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
    @SchedulerLock(name = "create_session_30s", lockAtMostFor = "30s", lockAtLeastFor = "5s")
    public void createSession30S() {
        try {
            saveSession(Time._30S);
        } catch (Exception e) {
            throw new GeneralException("Unhandled Exception: void createSession30S(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 1 minute
    // Runs at 1m, 2m, 3m, ...
    @Scheduled(cron = "* */1 * * * *")
    @SchedulerLock(name = "create_session_1m", lockAtMostFor = "1m", lockAtLeastFor = "5s")
    public void createSession1M() {
        try {
            saveSession(Time._1M);
        } catch (Exception e) {
            throw new GeneralException("Unhandled Exception: void createSession1M(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 3 minute
    // Runs at 3m, 6m, 9m, ...
    @Scheduled(cron = "* */3 * * * *")
    @SchedulerLock(name = "create_session_3m", lockAtMostFor = "3m", lockAtLeastFor = "5s")
    public void createSession3M() {
        try {
            saveSession(Time._3M);
        } catch (Exception e) {
            throw new GeneralException("Unhandled Exception: void createSession3M(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 5 minute
    // Runs at 5m, 10m, 15m, ...
    @Scheduled(cron = "* */5 * * * *")
    @SchedulerLock(name = "create_session_5m", lockAtMostFor = "5m", lockAtLeastFor = "5s")
    public void createSession5M() {
        try {
            saveSession(Time._5M);
        } catch (Exception e) {
            throw new GeneralException("Unhandled Exception: void createSession5M(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 25th second for 5 seconds of 30 seconds cycle
    // Runs at 25s, 55s, 85s, ...
    @Scheduled(cron = "25/30 * * * * *")
    @SchedulerLock(name = "last_run", lockAtMostFor = "5s", lockAtLeastFor = "1s")
    public void lastRun30S(){
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

    private void saveSession(Time time){
        WinGoSession winGoSession = WinGoSession.builder()
                .id(UUID.randomUUID())
                .totalAmount(INITIAL_TOTAL_AMOUNT)
                .minorityAmount(INITIAL_MINORITY_AMOUNT)
                .majorityAmount(INITIAL_MAJORITY_AMOUNT)
                .number(INITIAL_NUMBER)
                .color(Color.INITIAL_COLOR)
                .size(Size.INITIAL_SIZE)
                .time(time)
                .sessionStatus(GameSessionStatus.ACTIVE)
                .build();
        winGoSessionRepository.save(winGoSession);
    }

}
