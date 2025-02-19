package com.superwin.superWin.service;

import com.superwin.superWin.commonEnum.GameName;
import com.superwin.superWin.commonEnum.GameSessionStatus;
import com.superwin.superWin.commonEnum.GameType;
import com.superwin.superWin.model.WinGo;
import com.superwin.superWin.repository.WinGoRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class WinGoScheduler {

    private WinGoRepository winGoRepository;

    private static final Long initialTotalAmount = 0L;
    private static final Long initialMinorityAmount = 0L;
    private static final Long initialMajorityAmount = 0L;
    private static final Integer initialNumber = -1;

    @Scheduled(fixedRate = 30000) // Runs every 30 seconds
    private void createSession() {
        try {
            WinGo winGo = WinGo.builder()
                    .id(UUID.randomUUID())
                    .name(GameName.WIN_GO)
                    .type(GameType.LOTTERY)
                    .status(GameSessionStatus.ACTIVE)
                    .totalAmount(initialTotalAmount)
                    .minorityAmount(initialMinorityAmount)
                    .majorityAmount(initialMajorityAmount)
                    .number(initialNumber)
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
