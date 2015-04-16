package bg.game.implem;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.game.entities.FixedData;
import bg.game.entities.FloatingData;
import bg.game.entities.Game;
import bg.game.interfaces.AdministrateGame;

@Stateless(name = "AdministrateGame")
public class AdministrateGameBean implements AdministrateGame, Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "database")
	EntityManager entityManager;

	public Game createGame(String name, String password) {
		Game game = null; // entityManager.find(Game.class, name);
		/*
		 * if (game != null) { if (game.getPassword().equals(password)) { return
		 * game; } else { System.out.println("C'est nul !"); return null; } }
		 * else {
		 */
		game = new Game();
		game.setName(name);
		game.setPassword(password);
		entityManager.persist(game);
		// }
		return game;
	}

	@Override
	public FixedData createFixedData() {
		FixedData data = new FixedData();
		entityManager.persist(data);
		return data;
	}

	@Override
	public FloatingData createFloatingData() {
		FloatingData data = new FloatingData();
		entityManager.persist(data);
		return data;
	}

	@Override
	public void saveFixedData(FixedData data) {
		entityManager.merge(data);
	}

	@Override
	public void saveFloatingData(FloatingData data) {
		entityManager.merge(data);
	}

	@Override
	public void saveGame(Game game) {
		entityManager.merge(game);
	}

	@Override
	public Game findGameByID(Long id) {
		return entityManager.find(Game.class, id);
	}
}
