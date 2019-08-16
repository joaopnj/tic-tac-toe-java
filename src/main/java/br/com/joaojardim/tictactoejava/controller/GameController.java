package br.com.joaojardim.tictactoejava.controller;

import br.com.joaojardim.tictactoejava.model.Game;
import br.com.joaojardim.tictactoejava.payload.GamePayLoad;
import br.com.joaojardim.tictactoejava.payload.MovimentPayload;
import br.com.joaojardim.tictactoejava.payload.PositionPayload;
import br.com.joaojardim.tictactoejava.payload.RequestMovimentPayLoad;
import br.com.joaojardim.tictactoejava.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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
        Game game = this.gameRepository.save(new Game(' ', choosePlayer(), new Character[3][3]));
        gamePayLoad.setId(game.getId());
        gamePayLoad.setFirstPlayer(game.getTurn());
        return ResponseEntity.ok(gamePayLoad);
    }

    @PostMapping(value= "/{id}/moviment")
    public ResponseEntity<?> moviment(@PathVariable String id, @RequestBody RequestMovimentPayLoad requestMovimentPayLoad) {
        MovimentPayload movimentPayload = new MovimentPayload();
        Optional<Game> game = this.gameRepository.findById(id);
        if (!game.isPresent()) {
            movimentPayload.setMsg("Partida não encontrada");
            return ResponseEntity.badRequest().body(movimentPayload);
        }

        if (null != requestMovimentPayLoad.getPlayer() && game.get().getTurn() != requestMovimentPayLoad.getPlayer()) {
            movimentPayload.setMsg("Não é turno do jogador");
            return ResponseEntity.badRequest().body(movimentPayload);
        }

//        if (this.verifyPosition(game.get().getRequestMovimentPayLoadList(), requestMovimentPayLoad.getPosition())) {
//            movimentPayload.setMsg("Posição já escolhida");
//            return ResponseEntity.badRequest().body(movimentPayload);
//        }

        game.get().getTable()[requestMovimentPayLoad.getPosition().getX()][requestMovimentPayLoad.getPosition().getY()] = requestMovimentPayLoad.getPlayer();
        Game newGame = gameRepository.save(game.get());
        movimentPayload.setMsg("Movimento efetuado sucesso!");

        Character winner = this.verifyWinner(newGame);

        if (null != winner || ' ' != winner) {
            movimentPayload.setMsg("Partida finalizada");
            movimentPayload.setWinner(winner);
            return ResponseEntity.ok(movimentPayload);
        }

        return ResponseEntity.ok(movimentPayload);
    }

    private char choosePlayer() {
        final String[] proper_noun = {"X", "O"};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        return proper_noun[index].charAt(0);
    }

    private Character verifyWinner(Game game) {
        
        //Checa X verticalmente
        if(game.getTable()[0][0] == 'X' && game.getTable()[1][0] == 'X' && game.getTable() [2][0] == 'X'){
            System.out.println("'X' VENCEU");
            return 'X';
        }
        else if(game.getTable()[0][1] == 'X' && game.getTable()[1][1] == 'X' && game.getTable()[2][1] == 'X'){
            System.out.println("'X' VENCEU");
            return 'X';
        }
        else if(game.getTable()[0][2] == 'X' && game.getTable()[1][2] == 'X' && game.getTable()[2][2] == 'X'){
            System.out.println("'X' VENCEU");
            return 'X';
        }



        //Checa X horizontalmente
        else if(game.getTable()[0][0] == 'X' && game.getTable()[0][1] == 'X' && game.getTable()[0][2] == 'X'){
            System.out.println("'X' VENCEU");
            return 'X';
        }
        else if(game.getTable()[1][0] == 'X' && game.getTable()[1][1] == 'X' && game.getTable()[1][2] == 'X'){
            System.out.println("'X' VENCEU");
            return 'X';
        }
        else if(game.getTable()[2][0] == 'X' && game.getTable()[2][1] == 'X' && game.getTable()[2][2] == 'X'){
            System.out.println("'X' VENCEU");
            return 'X';
        }
        //Checa X diagonalmente
        else if(game.getTable()[0][0] == 'X' && game.getTable()[1][1] == 'X' && game.getTable()[2][2] == 'X'){
            System.out.println("'X' VENCEU");
            return 'X';
        }
        else if(game.getTable()[0][2] == 'X' && game.getTable()[1][1] == 'X' && game.getTable()[2][0] == 'X'){
            System.out.println("'X' VENCEU");
            return 'X';
        }
        //Checa O verticalmente
        if(game.getTable()[0][0] == 'O' && game.getTable()[1][0] == 'O' && game.getTable() [2][0] == 'O'){
            System.out.println("'O' VENCEU");
            return 'O';
        }
        else if(game.getTable()[0][1] == 'O' && game.getTable()[1][1] == 'O' && game.getTable()[2][1] == 'O'){
            System.out.println("'O' VENCEU");
            return 'O';
        }
        else if(game.getTable()[0][2] == 'O' && game.getTable()[1][2] == 'O' && game.getTable()[2][2] == 'O'){
            System.out.println("'O' VENCEU");
            return 'O';
        }

        //Checa O horizontalmente
        if(game.getTable()[0][0] == 'O' && game.getTable()[0][1] == 'O' && game.getTable()[0][2] == 'O'){
            System.out.println("'O' VENCEU");
            return 'O';
        }
        else if(game.getTable()[1][0] == 'O' && game.getTable()[1][1] == 'O' && game.getTable()[1][2] == 'O'){
            System.out.println("'O' VENCEU");
            return 'O';
        }
        else if(game.getTable()[2][0] == 'O' && game.getTable()[2][1] == 'O' && game.getTable()[2][2] == 'O'){
            System.out.println("'O' VENCEU");
            return 'O';
        }

        //Checa O diagonalmente
        if(game.getTable()[0][0] == 'O' && game.getTable()[1][1] == 'O' && game.getTable()[2][2] == 'O'){
            System.out.println("'O' VENCEU");
            return 'O';
        }
        else if(game.getTable()[0][2] == 'O' && game.getTable()[1][1] == 'O' && game.getTable()[2][0] == 'O'){
            System.out.println("'O' VENCEU");
            return 'O';
        }

        return ' ';
    }


}
