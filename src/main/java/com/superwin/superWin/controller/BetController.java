package com.superwin.superWin.controller;

import com.superwin.superWin.dto.BetRequestDTO;
import com.superwin.superWin.service.BetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bet")
@AllArgsConstructor
public class BetController {

    public BetService betService;

    // changing it betWinGo
    @PostMapping("/")
    public ResponseEntity<String> bet(@RequestBody BetRequestDTO betRequestDTO){
        Boolean isBetAccepted = betService.bet(betRequestDTO);
        if(isBetAccepted) return new ResponseEntity<>("Bet accepted", HttpStatus.ACCEPTED);
        return new ResponseEntity<>("Bet rejected", HttpStatus.NOT_ACCEPTABLE);
    }
}
