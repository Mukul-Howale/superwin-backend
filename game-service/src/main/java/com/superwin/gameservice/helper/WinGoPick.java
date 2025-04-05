package com.superwin.gameservice.helper;

import com.superwin.gameservice.enums.Color;
import com.superwin.gameservice.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class WinGoPick {

    public static Size getSize(Integer number){
        if(number == 0 || number == 1 || number == 2 || number == 3 || number == 4)
            return Size.SMALL;
        return Size.BIG;
    }

    public static Color getColor(Integer number){
        if(number == 0 || number == 2 || number == 4 || number == 6 || number == 8)
            return Color.RED;
        return Color.GREEN;
    }

    public static List<Integer> getPickCombo(String categoryCombo){
        switch (categoryCombo){
            case "smallRed" -> {
                return new ArrayList<>(List.of(0,2,4));
            }
            case "smallGreen" -> {
                return new ArrayList<>(List.of(1,3));
            }
            case "bigRed" -> {
                return new ArrayList<>(List.of(6,8));
            }
            case "bigGreen" -> {
                return new ArrayList<>(List.of(5,7,9));
            }
        }
        return new ArrayList<>();
    }
}
