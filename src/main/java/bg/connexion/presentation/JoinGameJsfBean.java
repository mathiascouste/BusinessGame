package bg.connexion.presentation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Cookie;

import tools.CookieHelper;

import bg.connexion.interfaces.Connexion;

@ViewScoped
@ManagedBean
public class JoinGameJsfBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ident = "";
	private String password = "";

	@EJB
	Connexion connexion;

	public String logout() {
		new CookieHelper().getCookie("game_ident").setValue("");
		new CookieHelper().getCookie("game_password").setValue("");
		return "logout";
	}

	public String connect() {
		if (connexion.connectToGame(new Long(ident), password) != null) {
			new CookieHelper().setCookie("game_ident", ident);
			new CookieHelper().setCookie("game_password", password);
			return "success";
		} else {
			System.out.println("FAIL : cannot connect to this game");
			return "fail";
		}
	}

	public String isConnected() {
		Cookie cookie = new CookieHelper().getCookie("game_ident");
		String gameIdent = "";
		if (cookie != null) {
			gameIdent = cookie.getValue();
		}
		cookie = new CookieHelper().getCookie("game_password");
		String game_password = "";
		if (cookie != null) {
			game_password = cookie.getValue();
		}
		if (connexion.connectToGame(new Long(gameIdent), game_password) != null) {
			return "success";
		} else {
			System.out.println("FAIL : this game is not connected");
			return "fail";
		}
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
