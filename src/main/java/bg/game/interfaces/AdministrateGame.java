package bg.game.interfaces;

import javax.ejb.Local;

import bg.game.entities.Game;

@Local
public interface AdministrateGame {
	public Game createGame(String name, String password);
}
