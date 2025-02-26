package com.superwin.gameservice.controller;

import com.superwin.gameservice.dto.WinGoBetRequestDTO;
import com.superwin.gameservice.dto.WinGoSessionResponseDTO;
import com.superwin.gameservice.service.WinGoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/win-go")
@AllArgsConstructor
public class WinGoController {

    public WinGoService winGoService;

    @PostMapping("/bet")
    public ResponseEntity<String> bet(@RequestBody WinGoBetRequestDTO winGoBetRequestDTO){
        Boolean isBetAccepted = winGoService.bet(winGoBetRequestDTO);
        if(isBetAccepted) return new ResponseEntity<>("WinGoBet accepted", HttpStatus.ACCEPTED);
        return new ResponseEntity<>("WinGoBet rejected", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/session")
    public ResponseEntity<WinGoSessionResponseDTO> sessionDetails(){
        WinGoSessionResponseDTO winGoSessionResponseDTO = winGoService.sessionDetails();

    }
}
