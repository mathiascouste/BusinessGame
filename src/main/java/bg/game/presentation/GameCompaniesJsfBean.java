package bg.game.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import tools.CookieHelper;

import bg.company.entities.Company;
import bg.company.interfaces.CompanyManager;
import bg.connexion.interfaces.Connexion;
import bg.game.entities.Game;
import bg.game.interfaces.AdministrateGame;

@SessionScoped
@ManagedBean
public class GameCompaniesJsfBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int bonus;
	private int malus;
	private List<Company> companies;
	private Game game;

	@EJB
	Connexion connexion;

	@EJB
	AdministrateGame gameAdministration;

	@EJB
	CompanyManager companyManager;

	public GameCompaniesJsfBean() {
		this.companies = new ArrayList<Company>();
	}

	public String giveBonus() {
		if(this.getGame() == null) {
			System.out.println("FAIL : Not connected to any game");
			return "fail";
		}
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> params = facesContext.getExternalContext()
				.getRequestParameterMap();
		String strID = params.get("companyID");
		Long id = new Long(strID);

		for (Company c : this.getCompanies()) {
			if (c.getIdent().equals(id)) {
				c.setAmende(malus);
				c.setSubvention(bonus);
				companyManager.saveCompany(c);
				gameAdministration.saveGame(this.game);
				malus = 0;
				bonus = 0;
				return "success";
			}
		}

		System.out.println("FAIL : No company of this ident");
		return "fail";
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getMalus() {
		return malus;
	}

	public void setMalus(int malus) {
		this.malus = malus;
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

	public List<Company> getCompanies() {
		Game g = this.getGame();
		if (g != null) {
			this.companies = g.getCompanies();
		}
		return this.companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public Connexion getConnexion() {
		return connexion;
	}

	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
