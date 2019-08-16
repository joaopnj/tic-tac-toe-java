package br.com.joaojardim.tictactoejava.model;


import br.com.joaojardim.tictactoejava.payload.PositionPayload;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "game")
@Getter
public class Game {

    @Id
    private String id;
    private String winner;
    private String turn;
    private List<PositionPayload> positionPayloadList;

    public Game(String winner, String turn, List<PositionPayload> positionPayloadList) {
        this.winner = winner;
        this.turn = turn;
        this.positionPayloadList = positionPayloadList;
    }
}