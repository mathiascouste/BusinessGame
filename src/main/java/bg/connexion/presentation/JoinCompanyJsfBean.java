package bg.connexion.presentation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tools.CookieHelper;

import bg.connexion.interfaces.Connexion;

@ManagedBean
public class JoinCompanyJsfBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String gameIdent = "";
	private String companyName = "";
	private String companyPassword = "";

	@EJB
	Connexion connexion;

	public String connect() {
		Long companyID = connexion.connectToCompany(new Long(gameIdent),
				companyName, companyPassword);
		if (companyID != null) {
			new CookieHelper().setCookie("game_ident", gameIdent);
			new CookieHelper().setCookie("company_ident", companyID.toString());
			new CookieHelper().setCookie("company_password", companyPassword);
			return "success";
		}
		return "fail";
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
