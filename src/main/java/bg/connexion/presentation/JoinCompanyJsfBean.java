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
public class JoinCompanyJsfBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String gameIdent = "";
	private String companyName = "";
	private String companyPassword = "";

	@EJB
	Connexion connexion;

	public String logout() {
		new CookieHelper().getCookie("game_ident").setValue("");
		new CookieHelper().getCookie("game_password").setValue("");
		return "logout";
	}

	public String connect() {
		Long companyID = connexion.connectToCompany(new Long(gameIdent),
				companyName, companyPassword);
		if (companyID != null) {
			new CookieHelper().setCookie("game_ident", gameIdent);
			new CookieHelper().setCookie("company_ident", companyID.toString());
			new CookieHelper().setCookie("company_password", companyPassword);
			return "success";
		}
		System.out.println("FAIL : cannot connect to this company");
		return "fail";
	}

	public String isConnected() {

		String gameID = "";
		String companyID = "";
		String companyPassword = "";
		Cookie cookie = new CookieHelper().getCookie("game_ident");
		if (cookie != null) {
			gameID = cookie.getValue();
		}
		cookie = new CookieHelper().getCookie("company_ident");
		if (cookie != null) {
			companyID = cookie.getValue();
		}
		cookie = new CookieHelper().getCookie("company_password");
		if (cookie != null) {
			companyPassword = cookie.getValue();
		}
		if (connexion.connectToCompany(new Long(gameID), companyID,
				companyPassword) != null) {
			return "success";
		} else {
			System.out.println("FAIL : connected to no company");
			return "fail";
		}
	}

	public String getGameIdent() {
		return gameIdent;
	}

	public void setGameIdent(String gameIdent) {
		this.gameIdent = gameIdent;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPassword() {
		return companyPassword;
	}

	public void setCompanyPassword(String companyPassword) {
		this.companyPassword = companyPassword;
	}
}
