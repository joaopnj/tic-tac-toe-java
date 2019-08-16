package br.com.joaojardim.tictactoejava.repository;

import br.com.joaojardim.tictactoejava.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
}
