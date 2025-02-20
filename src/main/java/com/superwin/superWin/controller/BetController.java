package com.superwin.superWin.controller;

import com.superwin.superWin.dto.WinGoBetRequestDTO;
import com.superwin.superWin.game.lottery.winGo.service.WinGoBetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bet")
@AllArgsConstructor
public class BetController {

    public WinGoBetService winGoBetService;

    // changing it betWinGo
    @PostMapping("/")
    public ResponseEntity<String> bet(@RequestBody WinGoBetRequestDTO winGoBetRequestDTO){
        Boolean isBetAccepted = winGoBetService.bet(winGoBetRequestDTO);
        if(isBetAccepted) return new ResponseEntity<>("WinGoBet accepted", HttpStatus.ACCEPTED);
        return new ResponseEntity<>("WinGoBet rejected", HttpStatus.NOT_ACCEPTABLE);
    }
}
