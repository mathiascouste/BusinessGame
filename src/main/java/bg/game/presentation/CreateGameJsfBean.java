package bg.game.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bg.company.entities.Company;
import bg.company.entities.Machine;
import bg.company.entities.Product;
import bg.company.interfaces.CompanyManager;
import bg.company.interfaces.MachineManager;
import bg.company.interfaces.ProductManager;
import bg.game.entities.Game;
import bg.game.interfaces.AdministrateGame;

@SessionScoped
@ManagedBean
public class CreateGameJsfBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String errorMessage = "";
	private Game game;

	public CreateGameJsfBean() {
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/*****************************/
	/******* Game creation *******/
	/*****************************/
	private String name = "";
	private String password = "";

	@EJB
	AdministrateGame gameAdministrator;

	public String create() {
		if (name.trim().isEmpty()) {
			this.errorMessage = "Le nom de la partie est vide";
			return "fail";
		}
		if (password.trim().isEmpty()) {
			this.errorMessage = "Le mot de passe de la partie est vide";
			return "fail";
		}

		if ((this.game = gameAdministrator.createGame(this.name, this.password)) == null) {
			this.errorMessage = "Le nom de la partie existe déjà ou le mot de passe est mauvais";
			return "fail";
		}

		return "success";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*****************************/
	/******* Team creation *******/
	/*****************************/
	private String teamName;
	private String teamPassword;
	private List<Company> teams = new ArrayList<Company>();

	@EJB
	CompanyManager companyManager;

	public String addTeam() {
		if (teamName.trim().isEmpty()) {
			this.errorMessage = "Le nom de l'équipe est vide";
			return "fail";
		}
		if (teamPassword.trim().isEmpty()) {
			this.errorMessage = "Le mot de passe de l'équipe est vide";
			return "fail";
		}

		Company company = companyManager.createCompany(teamName, teamPassword);
		if (company == null) {
			this.errorMessage = "Le nom de la company existe déjà";
			return "fail";
		}
		this.teams.add(company);

		return "continue";
	}

	public String goToProduct() {
		System.out.println(this.teams.size());
		if (this.teams.size() == 0) {
			this.errorMessage = "Il faut créer un moins une équipe";
			return "fail";
		} else {
			return "success";
		}
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamPassword() {
		return teamPassword;
	}

	public void setTeamPassword(String teamPassword) {
		this.teamPassword = teamPassword;
	}

	public List<Company> getTeams() {
		return teams;
	}

	public void setTeams(List<Company> teams) {
		this.teams = teams;
	}

	/*****************************/
	/****** Product creation *****/
	/*****************************/
	private String productName;
	private int productPrice;
	private int productDev;
	private List<Product> products = new ArrayList<Product>();

	@EJB
	ProductManager productManager;

	public String addProduct() {
		if (productName.trim().isEmpty()) {
			this.errorMessage = "Le nom du produit est vide";
			return "fail";
		}
		if (productPrice < 1) {
			this.errorMessage = "Le prix du produit est trop bas";
			return "fail";
		}
		if (productDev < 0) {
			this.errorMessage = "Le coût de développement du produit est trop bas";
			return "fail";
		}

		Product product = productManager.createProduct(productName,
				productPrice, productDev);
		if (product == null) {
			this.errorMessage = "Ce nom est déjà pris";
			return "fail";
		}
		this.products.add(product);

		return "continue";
	}

	public String goToMachine() {
		if (this.products.size() == 0) {
			this.errorMessage = "Il faut créer un moins une machine";
			return "fail";
		} else {
			return "success";
		}
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductDev() {
		return productDev;
	}

	public void setProductDev(int productDev) {
		this.productDev = productDev;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/*****************************/
	/****** Machine creation *****/
	/*****************************/

	private String machineName;
	private int machinePrice;
	private int machineEsperance;
	private int machineCapacity;
	private int machineEmployee;
	private List<Machine> machines = new ArrayList<Machine>();

	@EJB
	MachineManager machineManager;

	public String addMachine() {
		if (productName.trim().isEmpty()) {
			this.errorMessage = "Le nom du produit est vide";
			return "fail";
		}
		if (productPrice < 1) {
			this.errorMessage = "Le prix du produit est trop bas";
			return "fail";
		}
		if (productDev < 0) {
			this.errorMessage = "Le coût de développement du produit est trop bas";
			return "fail";
		}
		if (machineEmployee < 1) {
			this.errorMessage = "Le coût de développement du produit est trop bas";
			return "fail";
		}

		Machine machine = machineManager.createMachine(machineName,
				machinePrice, machineEsperance, machineCapacity,
				machineEmployee);
		if (machine == null) {
			this.errorMessage = "Ce nom est déjà pris";
			return "fail";
		}
		this.machines.add(machine);

		return "continue";
	}

	public String goToFixData() {
		if (this.machines.size() == 0) {
			this.errorMessage = "Il faut créer un moins une équipe";
			return "fail";
		} else {
			return "success";
		}
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public int getMachinePrice() {
		return machinePrice;
	}

	public void setMachinePrice(int machinePrice) {
		this.machinePrice = machinePrice;
	}

	public int getMachineEsperance() {
		return machineEsperance;
	}

	public void setMachineEsperance(int machineEsperance) {
		this.machineEsperance = machineEsperance;
	}

	public int getMachineCapacity() {
		return machineCapacity;
	}

	public void setMachineCapacity(int machineCapacity) {
		this.machineCapacity = machineCapacity;
	}

	public int getMachineEmployee() {
		return machineEmployee;
	}

	public void setMachineEmployee(int machineEmployee) {
		this.machineEmployee = machineEmployee;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}
}
