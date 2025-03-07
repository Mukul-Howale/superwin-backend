package com.superwin.gameservice.controller;

import com.superwin.gameservice.dto.WinGoBetRequestDTO;
import com.superwin.gameservice.dto.WinGoSessionResponseDTO;
import com.superwin.gameservice.enums.Time;
import com.superwin.gameservice.service.WinGoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/session/{time}")
    public ResponseEntity<WinGoSessionResponseDTO> getSessions(@PathVariable Time time){
        WinGoSessionResponseDTO winGoSessionResponseDTO = winGoService.getSessions(time);
        return new ResponseEntity<>(winGoSessionResponseDTO, HttpStatus.OK);
    }
}
