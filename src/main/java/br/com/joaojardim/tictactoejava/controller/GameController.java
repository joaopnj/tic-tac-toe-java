package br.com.joaojardim.tictactoejava.controller;

import br.com.joaojardim.tictactoejava.model.Game;
import br.com.joaojardim.tictactoejava.payload.GamePayLoad;
import br.com.joaojardim.tictactoejava.payload.MovimentPayload;
import br.com.joaojardim.tictactoejava.payload.PositionPayload;
import br.com.joaojardim.tictactoejava.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
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
    public ResponseEntity<?> init() {
        GamePayLoad gamePayLoad = new GamePayLoad();
        Game game = this.gameRepository.save(new Game("", choosePlayer(), new ArrayList<>()));
        gamePayLoad.setId(game.getId());
        gamePayLoad.setFirstPlayer(game.getTurn());
        return ResponseEntity.ok(gamePayLoad);
    }

    @PostMapping(value= "/{id}/moviment", params = {"player", "positionPayload"})
    public ResponseEntity<?> moviment(@PathVariable String id, @RequestParam String player, @RequestParam PositionPayload positionPayload) {
        MovimentPayload movimentPayload = new MovimentPayload();
        Optional<Game> game = this.gameRepository.findById(id);
        if(!game.isPresent()) {
            movimentPayload.setMsg("Partida não encontrada");
            return ResponseEntity.badRequest().body(movimentPayload);
        }

        if(null != player && game.get().getTurn() != player) {
            movimentPayload.setMsg("Não é turno do jogador");
            return ResponseEntity.badRequest().body(movimentPayload);
        }
        return ResponseEntity.ok(null);
    }

    private String choosePlayer() {
        final String[] proper_noun = {"X", "O"};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        return proper_noun[index];
    }


}
