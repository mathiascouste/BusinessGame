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
import bg.connexion.interfaces.Connexion;
import bg.game.entities.FixedData;
import bg.game.entities.FloatingData;
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
	private int productCost;
	private int productDev;
	private int productFixedProductionCost;
	private List<Product> products = new ArrayList<Product>();

	@EJB
	ProductManager productManager;

	public String addProduct() {
		if (productName.trim().isEmpty()) {
			this.errorMessage = "Le nom du produit est vide";
			return "fail";
		}
		if (productCost < 0) {
			this.errorMessage = "Le prix du produit est trop bas";
			return "fail";
		}
		if (productDev < 0) {
			this.errorMessage = "Le coût de développement du produit est trop bas";
			return "fail";
		}

		Product product = productManager.createProduct(productName,
				productCost, productDev, productFixedProductionCost);
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

	public int getProductCost() {
		return productCost;
	}

	public void setProductCost(int productCost) {
		this.productCost = productCost;
	}

	public int getProductDev() {
		return productDev;
	}

	public void setProductDev(int productDev) {
		this.productDev = productDev;
	}

	public int getProductFixedProductionCost() {
		return productFixedProductionCost;
	}

	public void setProductFixedProductionCost(int productFixedProductionCost) {
		this.productFixedProductionCost = productFixedProductionCost;
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
		if (machinePrice < 0) {
			this.errorMessage = "Le prix du produit est trop bas";
			return "fail";
		}
		if (productDev < 0) {
			this.errorMessage = "Le coût de développement du produit est trop bas";
			return "fail";
		}
		if (machineEmployee < 0) {
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

	/*****************************/
	/******* Fix variables *******/
	/*****************************/
	private int fixedPopulation = 0;
	private double fixedStartTresory = 0;
	private double fixedTax = 0;
	private double fixedInterest = 0;
	private int fixedCompanyCost = 0;
	private int fixedMinimalSalary = 0;

	private FixedData fixedData = null;
	
	public String validateFix() {
		if (this.fixedPopulation < 0) {
			this.errorMessage = "Population négative";
			return "fail";
		}
		if (this.fixedStartTresory < 0) {
			this.errorMessage = "Trésorie de départ négative";
			return "fail";
		}
		if (this.fixedTax < 0 || this.fixedTax > 1) {
			this.errorMessage = "Impôt négatif ou suppérieur à 1";
			return "fail";
		}
		if (this.fixedInterest < 0 || this.fixedInterest > 1) {
			this.errorMessage = "Interêt négatif ou suppérieur à 1";
			return "fail";
		}
		if (this.fixedCompanyCost < 0) {
			this.errorMessage = "L'entreprise rapporte en ne faisant rien ?";
			return "fail";
		}
		if (this.fixedMinimalSalary < 0) {
			this.errorMessage = "Vous ne pouvez pas faire payer vos employés pour travailler";
			return "fail";
		}

		fixedData = gameAdministrator.createFixedData();
		fixedData.setPopulation(fixedPopulation);
		fixedData.setCompanyCost(fixedCompanyCost);
		fixedData.setInterest(fixedInterest);
		fixedData.setMinimalSalary(fixedMinimalSalary);
		fixedData.setStartTresory(fixedStartTresory);
		fixedData.setTax(fixedTax);

		this.game.setFixedData(fixedData);

		return "success";
	}

	public int getFixedPopulation() {
		return fixedPopulation;
	}

	public void setFixedPopulation(int fixedPopulation) {
		this.fixedPopulation = fixedPopulation;
	}

	public double getFixedStartTresory() {
		return fixedStartTresory;
	}

	public void setFixedStartTresory(double fixedStartTresory) {
		this.fixedStartTresory = fixedStartTresory;
	}

	public double getFixedTax() {
		return fixedTax;
	}

	public void setFixedTax(double fixedTax) {
		this.fixedTax = fixedTax;
	}

	public double getFixedInterest() {
		return fixedInterest;
	}

	public void setFixedInterest(double fixedInterest) {
		this.fixedInterest = fixedInterest;
	}

	public int getFixedCompanyCost() {
		return fixedCompanyCost;
	}

	public void setFixedCompanyCost(int fixedCompanyCost) {
		this.fixedCompanyCost = fixedCompanyCost;
	}

	public int getFixedMinimalSalary() {
		return fixedMinimalSalary;
	}

	public void setFixedMinimalSalary(int fixedMinimalSalary) {
		this.fixedMinimalSalary = fixedMinimalSalary;
	}

	/*****************************/
	/**** Floating variables *****/
	/*****************************/

	private double floatingQuality = 0;
	private double floatingAdvertising = 0;
	private double floatingFidelity = 0;

	private FloatingData floatingData = null;
	
	public String validateFloatting() {
		if (floatingQuality < 0 || floatingQuality > 1) {
			this.errorMessage = "Coefficient de qualité négatif ou suppérieur à 1";
			return "fail";
		}
		if (floatingAdvertising < 0 || floatingAdvertising > 1) {
			this.errorMessage = "Coefficient de publicité négatif ou suppérieur à 1";
			return "fail";
		}
		if (floatingFidelity < 0 || floatingFidelity > 1) {
			this.errorMessage = "Coefficient de fidelité négatif ou suppérieur à 1";
			return "fail";
		}

		floatingData = gameAdministrator.createFloatingData();
		floatingData.setAdvertising(floatingAdvertising);
		floatingData.setFidelity(floatingFidelity);
		floatingData.setQuality(floatingQuality);

		this.game.setFloatingData(floatingData);

		return "success";

	}

	public double getFloatingQuality() {
		return floatingQuality;
	}

	public void setFloatingQuality(double floatingQuality) {
		this.floatingQuality = floatingQuality;
	}

	public double getFloatingAdvertising() {
		return floatingAdvertising;
	}

	public void setFloatingAdvertising(double floatingAdvertising) {
		this.floatingAdvertising = floatingAdvertising;
	}

	public double getFloatingFidelity() {
		return floatingFidelity;
	}

	public void setFloatingFidelity(double floatingFidelity) {
		this.floatingFidelity = floatingFidelity;
	}

	/*****************************/
	/****** Launch variables *****/
	/*****************************/
	
	@EJB
	Connexion connexion;
	
	public String launch() {
		if (game == null) {
			return "fail";
		}
		if (teams == null || teams.size() == 0) {
			return "fail";
		}
		if (products == null || products.size() == 0) {
			return "fail";
		}
		if (machines == null || machines.size() == 0) {
			return "fail";
		}
		if (fixedData == null) {
			return "fail";
		}
		if (floatingData == null) {
			return "fail";
		}
		this.game.setFixedData(fixedData);
		this.game.setFloatingData(floatingData);
		
		gameAdministrator.saveFixedData(fixedData);
		gameAdministrator.saveFloatingData(floatingData);
		for(Company c : teams) {
			c.setTreasury(game.getFixedData().getStartTresory());
			companyManager.saveCompany(c);
			game.getCompanies().add(c);
		}
		for(Machine m : machines) {
			machineManager.saveMachine(m);
			game.getMachines().add(m);
		}
		for(Product p : products) {
			productManager.saveProduct(p);
			game.getProducts().add(p);
		}
		gameAdministrator.saveGame(game);
		
		connexion.connectToGame(game.getIdent(), game.getPassword());
		
		return "success";
	}
}
