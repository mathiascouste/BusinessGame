package bg.connexion.presentation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import tools.CookieHelper;

import bg.connexion.interfaces.Connexion;

@ManagedBean
public class JoinGameJsfBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ident = "";
	private String password = "";

	@EJB
	Connexion connexion;

	public String connect() {
		if (connexion.connectToGame(new Long(ident), password) != null) {
			new CookieHelper().setCookie("game_ident", ident);
			new CookieHelper().setCookie("game_password", password);
			return "success";
		} else {
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
