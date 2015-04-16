package bg.game.interfaces;

import javax.ejb.Local;

import bg.game.entities.FixedData;
import bg.game.entities.FloatingData;
import bg.game.entities.Game;

@Local
public interface AdministrateGame {
	public Game createGame(String name, String password);

	public FixedData createFixedData();

	public FloatingData createFloatingData();

	public void saveFixedData(FixedData data);

	public void saveFloatingData(FloatingData data);

	public void saveGame(Game game);

	public Game findGameByID(Long id);
}
