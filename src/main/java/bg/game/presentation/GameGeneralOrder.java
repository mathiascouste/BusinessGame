package bg.game.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Cookie;

import tools.CookieHelper;
import bg.company.entities.Company;
import bg.company.interfaces.CompanyManager;
import bg.connexion.interfaces.Connexion;
import bg.game.entities.Game;
import bg.game.interfaces.AdministrateGame;
import bg.order.entities.Order;
import bg.order.interfaces.OrderManager;

@ViewScoped
@ManagedBean
public class GameGeneralOrder implements Serializable {
	private static final long serialVersionUID = -4981963404610900131L;
	private int employee = 0;
	private int salary = 0;
	private int research = 0;
	private List<Company> companies;
	private Game game;

	@EJB
	Connexion connexion;

	@EJB
	AdministrateGame gameAdministration;

	@EJB
	CompanyManager companyManager;

	@EJB
	OrderManager orderManager;

	public GameGeneralOrder() {
		this.companies = new ArrayList<Company>();
	}

	public String apply() {
		if (this.getGame() == null) {
			return "fail";
		}
		if (salary < this.game.getFixedData().getMinimalSalary()) {
			return "fail";
		}

		Order order = orderManager.createOrder();
		order.setSalary(salary);
		order.setEmployee(employee);
		order.setResearch(research);

		for (Company c : this.game.getCompanies()) {
			c.setValidatedOrder(order);
			companyManager.saveCompany(c);
		}
		orderManager.saveOrder(order);

		return "success";
	}

	private Game getGame() {
		if (this.game != null) {
			return this.game;
		}
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

	public int getEmployee() {
		return employee;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getResearch() {
		return research;
	}

	public void setResearch(int research) {
		this.research = research;
	}
}
