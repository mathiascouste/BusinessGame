package bg.game.implem;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.game.entities.FixedData;
import bg.game.entities.Game;
import bg.game.interfaces.AdministrateGame;

@Stateless(name = "AdministrateGame")
public class AdministrateGameBean implements AdministrateGame, Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "database")
	EntityManager entityManager;

	public Game createGame(String name, String password) {
		Game game = entityManager.find(Game.class, name);
		if (game != null) {
			if (game.getPassword().equals(password)) {
				return game;
			} else {
				System.out.println("C'est nul !");
				return null;
			}
		} else {
			game = new Game();
			game.setName(name);
			game.setPassword(password);
			entityManager.persist(game);
		}
		return game;
	}

	@Override
	public FixedData createFixedData() {
		FixedData data = new FixedData();
		entityManager.persist(data);
		return data;
	}
}
