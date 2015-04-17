package bg.game.presentation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Cookie;

import tools.CookieHelper;

import bg.connexion.interfaces.Connexion;
import bg.game.entities.Game;
import bg.game.interfaces.AdministrateGame;
import bg.game.interfaces.SimulateYear;

@ViewScoped
@ManagedBean
public class GameNextTurnJsfBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Game game;

	@EJB
	Connexion connexion;

	@EJB
	AdministrateGame gameAdministration;

	@EJB
	SimulateYear simulator;

	public String simulate() {
		this.game = this.getGame();
		if (this.game == null) {
			return "fail";
		}
		simulator.simulate(this.game);
		return "success";
	}

	private Game getGame() {
		Cookie cookie = new CookieHelper().getCookie("game_ident");
		String gameID = "";
		String gamePassword = "";
		if (cookie != null) {
			gameID = cookie.getValue();
		}
		cookie = new CookieHelper().getCookie("game_password");
		if (cookie != null) {
			gamePassword = cookie.getValue();
		}
		Long gameIDL = connexion.connectToGame(new Long(gameID), gamePassword);
		this.game = gameAdministration.findGameByID(gameIDL);

		return this.game;
	}

}
