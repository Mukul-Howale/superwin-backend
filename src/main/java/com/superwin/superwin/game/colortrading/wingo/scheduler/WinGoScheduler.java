package com.superwin.superwin.game.colortrading.wingo.scheduler;

import com.superwin.superwin.game.colortrading.wingo.model.WinGo;
import com.superwin.superwin.game.colortrading.wingo.repository.WinGoRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class WinGoScheduler {

    private WinGoRepository winGoRepository;

    private static final Long INITIAL_TOTAL_AMOUNT = 0L;
    private static final Long INITIAL_MINORITY_AMOUNT = 0L;
    private static final Long INITIAL_MAJORITY_AMOUNT = 0L;
    private static final Integer INITIAL_NUMBER = -1;

    @Scheduled(fixedRate = 30000) // Runs every 30 seconds
    private void createSession() {
        try {
            WinGo winGo = WinGo.builder()
                    .id(UUID.randomUUID())
                    .totalAmount(INITIAL_TOTAL_AMOUNT)
                    .minorityAmount(INITIAL_MINORITY_AMOUNT)
                    .majorityAmount(INITIAL_MAJORITY_AMOUNT)
                    .number(INITIAL_NUMBER)
                    .color(null)
                    .size(null)
                    .build();
            winGoRepository.save(winGo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 25000)
    private void lastRun(){
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
