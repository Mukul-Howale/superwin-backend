package com.superwin.gameservice.controller;

import com.superwin.gameservice.dto.GameCreaationRequestDTO;
import com.superwin.gameservice.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private GameService gameService;

    @PostMapping("/")
    public ResponseEntity<String> addGame(GameCreaationRequestDTO gameCreaationRequestDTO){
        Boolean isGameAdded = gameService.addGame(gameCreaationRequestDTO);
        if(!isGameAdded) return new ResponseEntity<>("Game not added", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>("Game added successfully", HttpStatus.ACCEPTED);
    }
}
