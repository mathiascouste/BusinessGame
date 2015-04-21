package bg.company.presentation;

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
import bg.company.entities.Product;
import bg.company.interfaces.CompanyManager;
import bg.connexion.interfaces.Connexion;
import bg.game.entities.Game;
import bg.game.interfaces.AdministrateGame;
import bg.order.entities.Order;
import bg.order.entities.ProductionOrder;
import bg.order.interfaces.OrderManager;

@SessionScoped
@ManagedBean
public class CompanyOrderJsfBean implements Serializable {
	private static final long serialVersionUID = -4981963404610900131L;
	private int employee = 0;
	private int salary = 0;
	private int research = 0;
	private int machineQuantity = 0;
	private List<Company> companies;
	private Map<Machine, Integer> buyMachines = new HashMap<Machine, Integer>();
	private List<Machine> machines = new ArrayList<Machine>();
	private Game game;
	private Company company;
	private int productQuantity = 0;
	private int productQuality = 0;
	private int productAdvertising = 0;
	private int productSellPrice = 0;
	private List<ProductionOrder> productionOrders = new ArrayList<ProductionOrder>();

	@EJB
	Connexion connexion;

	@EJB
	AdministrateGame gameAdministration;

	@EJB
	CompanyManager companyManager;

	@EJB
	OrderManager orderManager;

	public CompanyOrderJsfBean() {
		this.companies = new ArrayList<Company>();
	}

	public String productProduct() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> params = facesContext.getExternalContext()
				.getRequestParameterMap();
		String strID = params.get("productID");
		Long id = new Long(strID);

		for (ProductionOrder pO : this.productionOrders) {
			if (pO.getProduct().getIdent().equals(id)) {
				pO.setAdvertising(this.productAdvertising);
				pO.setQuality(this.productQuality);
				pO.setQuantity(this.productQuantity);
				pO.setSellPrice(this.productSellPrice);
				this.productAdvertising = this.productQuality = this.productQuantity = this.productSellPrice = 0;
			}
		}
		return "success";
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
			System.out.println("FAIL : this machine does not exist");
			return "fail";
		}
		this.buyMachines.put(thisMachine, new Integer(machineQuantity));
		this.machineQuantity = 0;

		return "success";
	}

	public String validateOrder() {
		if(this.getCompany() == null || this.getCompany().getCurrentOrder() == null) {
			System.out.println("FAIL : this company does not exist or its current order is NULL");
			return "fail";
		}
		this.company.setValidatedOrder(this.company.getCurrentOrder());
		this.company.setCurrentOrder(null);
		
		return "success";
	}
	
	public String apply() {
		if (this.getGame() == null) {
			System.out.println("FAIL : this game does not exist");
			return "fail";
		}
		if (this.getCompany() == null) {
			System.out.println("FAIL : this company does not exist");
			return "fail";
		}
		if (salary < this.game.getFixedData().getMinimalSalary()) {
			System.out.println("FAIL : the minimal salary is not enough high");
			return "fail";
		}

		Order order = orderManager.createOrder();
		order.setSalary(salary);
		order.setEmployee(employee);
		order.setResearch(research);
		order.setBuyMachines(buyMachines);
		order.setProductionOrders(productionOrders);

		this.getCompany().setCurrentOrder(order);
		companyManager.saveCompany(this.company);
		orderManager.saveOrder(order);

		return "success";
	}

	private Game getGame() {
		if (this.game != null) {
			return this.game;
		}
		String gameID = "";
		Cookie cookie = new CookieHelper().getCookie("game_ident");
		if (cookie != null) {
			gameID = cookie.getValue();
		}
		this.game = gameAdministration.findGameByID(new Long(gameID));

		return this.game;
	}

	private Company getCompany() {
		if (this.company != null) {
			return this.company;
		}
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
		Long companyIDL = connexion.connectToCompany(new Long(companyID),
				companyPassword);
		this.game = gameAdministration.findGameByID(new Long(gameID));
		this.company = companyManager.findCompanyByID(companyIDL);

		return this.company;
	}

	public int getEmployee() {
		return employee;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	public int getSalary() {
		if(this.getCompany() != null && this.company.getCurrentOrder() != null) {
			salary = this.company.getCurrentOrder().getSalary();
		}
		if(this.getCompany() != null && this.company.getValidatedOrder() != null) {
			salary = this.company.getValidatedOrder().getSalary();
		}
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

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getProductQuality() {
		return productQuality;
	}

	public void setProductQuality(int productQuality) {
		this.productQuality = productQuality;
	}

	public int getProductAdvertising() {
		return productAdvertising;
	}

	public void setProductAdvertising(int productAdvertising) {
		this.productAdvertising = productAdvertising;
	}

	public int getProductSellPrice() {
		return productSellPrice;
	}

	public void setProductSellPrice(int productSellPrice) {
		this.productSellPrice = productSellPrice;
	}

	public List<ProductionOrder> getProductionOrders() {
		if (this.productionOrders.size() == 0) {
			for (Product p : getGame().getProducts()) {
				ProductionOrder pO = new ProductionOrder();
				pO.setProduct(p);
				this.productionOrders.add(pO);
			}
		}
		return productionOrders;
	}

	public void setProductionOrders(List<ProductionOrder> productionOrders) {
		this.productionOrders = productionOrders;
	}
}
