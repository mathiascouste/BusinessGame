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
		System.out.println("SIMULATE A");
		this.game = this.getGame();
		System.out.println("SIMULATE B");
		if (this.game == null) {
			System.out.println("SIMULATE C");
			return "fail";
		}
		System.out.println("SIMULATE D");
		simulator.simulate(this.game);
		return "success";
	}

	private Game getGame() {
		System.out.println("GET GAME   A");
		Cookie cookie = new CookieHelper().getCookie("game_ident");
		System.out.println("GET GAME   B");
		String gameID = "";
		String gamePassword = "";
		System.out.println("GET GAME   C");
		if (cookie != null) {
			System.out.println("GET GAME   D");
			gameID = cookie.getValue();
		}
		System.out.println("GET GAME   E");
		cookie = new CookieHelper().getCookie("game_password");
		if (cookie != null) {
			System.out.println("GET GAME   F");
			gamePassword = cookie.getValue();
		}
		System.out.println("GET GAME   G");
		Long gameIDL = connexion.connectToGame(new Long(gameID), gamePassword);
		System.out.println("GET GAME   H");
		this.game = gameAdministration.findGameByID(gameIDL);

		System.out.println("GET GAME   I");
		return this.game;
	}

}
