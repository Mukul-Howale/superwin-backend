package com.superwin.gameservice.scheduler;

import com.superwin.gameservice.enums.Time;
import com.superwin.gameservice.exception.CreateSessionException;
import com.superwin.gameservice.exception.LastRunException;
import com.superwin.gameservice.service.WinGoService;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class WinGoScheduler {

    private final WinGoService winGoService;



    // Cron expression syntax which runs every 30 seconds
    // Runs at 0s, 30s, 60s, ...
    @Scheduled(cron = "*/30 * * * * *")
    @SchedulerLock(name = "create_session_30s", lockAtMostFor = "30s", lockAtLeastFor = "5s")
    public void createSession30S() {
        try {
            winGoService.saveSession(Time._30S);
        } catch (Exception e) {
            throw new CreateSessionException("Unhandled Exception: void createSession30S(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 1 minute
    // Runs at 1m, 2m, 3m, ...
    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "create_session_1m", lockAtMostFor = "1m", lockAtLeastFor = "5s")
    public void createSession1M() {
        try {
            winGoService.saveSession(Time._1M);
        } catch (Exception e) {
            throw new CreateSessionException("Unhandled Exception: void createSession1M(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 3 minute
    // Runs at 3m, 6m, 9m, ...
    @Scheduled(cron = "0 */3 * * * *")
    @SchedulerLock(name = "create_session_3m", lockAtMostFor = "3m", lockAtLeastFor = "5s")
    public void createSession3M() {
        try {
            winGoService.saveSession(Time._3M);
        } catch (Exception e) {
            throw new CreateSessionException("Unhandled Exception: void createSession3M(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 5 minute
    // Runs at 5m, 10m, 15m, ...
    @Scheduled(cron = "0 */5 * * * *")
    @SchedulerLock(name = "create_session_5m", lockAtMostFor = "5m", lockAtLeastFor = "5s")
    public void createSession5M() {
        try {
            winGoService.saveSession(Time._5M);
        } catch (Exception e) {
            throw new CreateSessionException("Unhandled Exception: void createSession5M(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 25th second for 5 seconds of 30 seconds cycle
    // Runs at 25s, 55s, 1.25m, ...
    @Scheduled(cron = "25/30 * * * * *")
    @SchedulerLock(name = "last_run_30s", lockAtMostFor = "5s", lockAtLeastFor = "1s")
    public void lastRun30S(){
        try{
            winGoService.sessionPick(Time._30S);
        }catch (Exception e){
            throw new LastRunException("Unhandled Exception: void lastRun30S(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 55th second for 5 seconds of 1 minutes cycle
    // Runs at 55s, 1.55m, 2.85m, ...
    @Scheduled(cron = "55 */1 * * * *")
    @SchedulerLock(name = "last_run_1m", lockAtMostFor = "5s", lockAtLeastFor = "1s")
    public void lastRun1M(){
        try{
            winGoService.sessionPick(Time._1M);
        }catch (Exception e){
            throw new LastRunException("Unhandled Exception: void lastRun1M(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 175th second for 5 seconds of 3 minutes cycle
    // Runs at 2.55m, 5.55m, 8.55m, ...
    @Scheduled(cron = "55 */3 * * * *")
    @SchedulerLock(name = "last_run_3m", lockAtMostFor = "5s", lockAtLeastFor = "1s")
    public void lastRun3M(){
        try{
            winGoService.sessionPick(Time._3M);
        }catch (Exception e){
            throw new LastRunException("Unhandled Exception: void lastRun3M(), WinGoScheduler \n PrintStack: " + e);
        }
    }

    // Cron expression syntax which runs every 255th second for 5 seconds of 5 minutes cycle
    // Runs at 4.55m, 9.55m, 14.55m, ...
    @Scheduled(cron = "55 */5 * * * *")
    @SchedulerLock(name = "last_run_5m", lockAtMostFor = "5s", lockAtLeastFor = "1s")
    public void lastRun5M(){
        try{
            winGoService.sessionPick(Time._5M);
        }catch (Exception e){
            throw new LastRunException("Unhandled Exception: void lastRun5M(), WinGoScheduler \n PrintStack: " + e);
        }
    }
}
