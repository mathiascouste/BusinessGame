package bg.connexion.implem;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.company.entities.Company;
import bg.connexion.interfaces.Connexion;
import bg.game.entities.Game;

@Stateless(name = "Connexion")
public class ConnexionBean implements Connexion, Serializable {
	private static final long serialVersionUID = -6068234697472630237L;
	@PersistenceContext(unitName = "database")
	EntityManager entityManager;

	@Override
	public Long connectToGame(Long ident, String password) {
		Game game = entityManager.find(Game.class, ident);
		if (game == null) {
			return null;
		}
		if (!game.getPassword().equals(password)) {
			return null;
		}
		return game.getIdent();
	}

	@Override
	public Long connectToCompany(Long gameIdent, String companyName,
			String password) {
		Game game = entityManager.find(Game.class, gameIdent);
		if (game == null) {
			return null;
		}
		List<Company> companies = game.getCompanies();
		for (Company c : companies) {
			if (c.getName().equals(companyName)
					&& c.getPassword().equals(password)) {
				return c.getIdent();
			}
		}
		return null;
	}

}
