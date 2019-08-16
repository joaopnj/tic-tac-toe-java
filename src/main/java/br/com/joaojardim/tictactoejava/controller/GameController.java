package br.com.joaojardim.tictactoejava.controller;

import br.com.joaojardim.tictactoejava.model.Game;
import br.com.joaojardim.tictactoejava.payload.GamePayLoad;
import br.com.joaojardim.tictactoejava.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @PostMapping()
    public ResponseEntity<?> initGame() {
        GamePayLoad gamePayLoad = new GamePayLoad();
        Game game = this.gameRepository.save(new Game("", choosePlayer(), new String[3][3]));
        gamePayLoad.setId(game.getId());
        gamePayLoad.setFirstPlayer(game.getTurn());
        return ResponseEntity.ok(gamePayLoad);
    }

    private String choosePlayer() {
        final String[] proper_noun = {"X", "O"};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        return proper_noun[index];
    }


}
