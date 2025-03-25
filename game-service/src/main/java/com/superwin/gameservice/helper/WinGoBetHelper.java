package com.superwin.gameservice.helper;

import com.superwin.gameservice.dto.WinGoBetRequestDTO;
import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.Result;
import com.superwin.gameservice.enums.Size;
import com.superwin.gameservice.exception.InvalidBetException;
import com.superwin.gameservice.model.WinGoBet;

import java.util.Arrays;
import java.util.UUID;

public class WinGoBetHelper {

    public static boolean checkIfSizeIsValid(Size size){
        Arrays.stream(Size.values()).forEach(s -> {
            if(!s.equals(size)) throw new InvalidBetException("Invalid size");
        });
        return true;
    }

    public static boolean checkIfColorIsValid(Color color){
        Arrays.stream(Color.values()).forEach(c -> {
            if(!c.equals(color)) throw new InvalidBetException("Invalid color");
        });
        return true;
    }

    public static boolean checkIfNumberIsValid(Integer number){
        if(number < 0 || number > 9)
            throw new InvalidBetException("Invalid number");
        return true;
    }

    // This is the first time a bet is created and registered to the db
    public static WinGoBet buildWinGoBet(WinGoBetRequestDTO winGoBetRequestDTO, Color color, Size size, Integer number){
        return WinGoBet.builder()
                .id(UUID.randomUUID())
                .profileId(winGoBetRequestDTO.profileId())
                .sessionId(winGoBetRequestDTO.sessionId())
                .time(winGoBetRequestDTO.time())
                .betAmount(winGoBetRequestDTO.betAmount())
                .color(color)
                .size(size)
                .number(number)
                .result(Result.PENDING)
                .build();
    }
}
