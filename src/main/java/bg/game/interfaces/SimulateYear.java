package bg.game.interfaces;

import javax.ejb.Local;

import bg.game.entities.Game;

@Local
public interface SimulateYear {
	public void simulate(Game game);
}
