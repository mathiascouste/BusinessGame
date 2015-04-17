package bg.game.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import tools.CookieHelper;
import bg.company.entities.Company;
import bg.company.entities.Machine;
import bg.company.interfaces.CompanyManager;
import bg.connexion.interfaces.Connexion;
import bg.game.entities.Game;
import bg.game.interfaces.AdministrateGame;
import bg.order.entities.Order;
import bg.order.interfaces.OrderManager;

@SessionScoped
@ManagedBean
public class GameGeneralOrder implements Serializable {
	private static final long serialVersionUID = -4981963404610900131L;
	private int employee = 0;
	private int salary = 0;
	private int research = 0;
	private int machineQuantity = 0;
	private List<Company> companies;
	private Map<Machine, Integer> buyMachines = new HashMap<Machine, Integer>();
	private List<Machine> machines;
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

	public String buyMachine() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> params = facesContext.getExternalContext()
				.getRequestParameterMap();
		String strID = params.get("machineID");
		Long id = new Long(strID);

		Machine thisMachine = null;
		for (Machine m : this.getMachines()) {
			if (m.getIdent().equals(id)) {
				thisMachine = m;
				break;
			}
		}
		if (thisMachine == null) {
			return "fail";
		}
		this.buyMachines.put(thisMachine, new Integer(machineQuantity));
		this.machineQuantity = 0;

		return "success";
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
		order.setBuyMachines(buyMachines);

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

	public Map<Machine, Integer> getBuyMachines() {
		return buyMachines;
	}

	public void setBuyMachines(Map<Machine, Integer> buyMachines) {
		this.buyMachines = buyMachines;
	}

	public int getMachineQuantity(Machine m) {
		Integer q = buyMachines.get(m);
		if (q != null) {
			return q.intValue();
		} else {
			return 0;
		}
	}

	public List<Machine> getMachines() {
		if (this.machines == null || this.machines.size() == 0) {
			System.out.println("Ce game a "
					+ this.getGame().getMachines().size() + " machines ...");
			this.machines = this.getGame().getMachines();
		}
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}

	public int getMachineQuantity() {
		return machineQuantity;
	}

	public void setMachineQuantity(int machineQuantity) {
		this.machineQuantity = machineQuantity;
	}
}
