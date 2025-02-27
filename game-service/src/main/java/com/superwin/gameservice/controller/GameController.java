package com.superwin.gameservice.controller;

import com.superwin.gameservice.dto.GameCreationRequestDTO;
import com.superwin.gameservice.enums.GameStatus;
import com.superwin.gameservice.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private GameService gameService;

    @PostMapping("/add-game")
    public ResponseEntity<String> addGame(@RequestBody GameCreationRequestDTO gameCreationRequestDTO){
        Boolean isGameAdded = gameService.addGame(gameCreationRequestDTO);
        if(!isGameAdded) return new ResponseEntity<>("Game not added", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>("Game added successfully", HttpStatus.ACCEPTED);
    }

//    @PatchMapping("/change-status/{status}")
//    public ResponseEntity<String> changeStatus(@PathVariable GameStatus status){
//
//    }

}
