package bg.game.implem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import bg.company.entities.Company;
import bg.company.entities.Machine;
import bg.company.entities.StockedProduct;
import bg.company.interfaces.CompanyManager;
import bg.game.entities.Game;
import bg.game.interfaces.AdministrateGame;
import bg.game.interfaces.SimulateYear;
import bg.order.entities.Order;
import bg.order.entities.ProductionOrder;

@Stateless(name = "SimulateYear")
public class SimulateYearBean implements SimulateYear, Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	CompanyManager companyManager;
	@EJB
	AdministrateGame gameManager;

	@Override
	public void simulate(Game game) {
		// Faire les dépenses
		this.applyGainAndLoss(game);

		this.applyProduction(game);

		// TODO : Vendre sur le marché mondial
		this.worldWideMarket(game);

		// TODO : Calculer coût des stocks

		// TODO : Evaluer bénéfice pour payer impôt et actionaires

		// TODO : Calculer interêt en cas d'endetement

		this.saveChange(game);
	}

	private void worldWideMarket(Game game) {
		for (Company c : game.getCompanies()) {
			double gain = 0;
			for (StockedProduct sP : c.getProductList()) {
				gain += sP.getQuantity() * sP.getPrice();
				sP.setQuantity(0);
			}
			c.setTreasury(c.getTreasury() + gain);
		}
	}

	private void applyProduction(Game game) {
		for (Company c : game.getCompanies()) {
			int productionCapacity = calculateProductionCapacity(c);
			Order order = c.getValidatedOrder();
			List<StockedProduct> stockedProducts = new ArrayList<StockedProduct>();
			double cost = 0;
			for (ProductionOrder pO : order.getProductionOrders()) {
				int toProduce = pO.getQuantity();
				if (toProduce >= productionCapacity) {
					productionCapacity -= toProduce;
				} else {
					toProduce = productionCapacity;
					productionCapacity = 0;
				}
				if (toProduce <= 0) {
					break;
				}
				cost += pO.getProduct().getFixedProductionCost();
				cost += pO.getProduct().getCost() * toProduce;
				cost += pO.getAdvertising();
				cost += pO.getQuality();
				StockedProduct sP = new StockedProduct(pO.getProduct(),
						toProduce);
				sP.setAdvertising(pO.getAdvertising() / toProduce);
				sP.setQuality(pO.getQuality() / toProduce);
				sP.setPrice(pO.getSellPrice());
				stockedProducts.add(sP);
			}
			c.setTreasury(c.getTreasury() - cost);
			c.setProductList(stockedProducts);
		}
	}

	private int calculateProductionCapacity(Company c) {
		int employeesNeeded = 0;
		int productionCapacityMax = 0;
		for (Map.Entry<Machine, Integer> entry : c.getMachineList().entrySet()) {
			Machine m = entry.getKey();
			int quantity = entry.getValue().intValue();
			employeesNeeded += quantity * m.getEmployeeNeeded();
			productionCapacityMax += quantity * m.getProductionCapacity();
		}
		double coef = 0;
		if (employeesNeeded == 0) {
			coef = 1;
		} else {
			coef = c.getEmployeeQuantity() / employeesNeeded;
		}
		if (coef > 1) {
			coef = 1;
		}
		return (int) (productionCapacityMax * coef);
	}

	private int payAndApplyEmployee(Company company) {
		int loss = 0;
		int addEmployee = company.getValidatedOrder().getEmployee();
		if (addEmployee < 0) {
			loss += company.getValidatedOrder().getSalary()
					* company.getEmployeeQuantity();
		} else {
			loss += company.getValidatedOrder().getSalary()
					* (company.getEmployeeQuantity() + addEmployee);
		}
		int nEmployee = company.getEmployeeQuantity() + addEmployee;
		if (nEmployee < 0)
			nEmployee = 0;
		company.setEmployeeQuantity(nEmployee);

		return loss;
	}

	private void applyGainAndLoss(Game game) {
		for (Company c : game.getCompanies()) {
			Order order = c.getValidatedOrder();
			double loss = 0;
			double gain = 0;

			loss += game.getFixedData().getCompanyCost();
			loss += order.getResearch();
			loss += payAndApplyEmployee(c);
			loss += buyMachines(c);
			loss += c.getAmende();
			gain += c.getSubvention();
			c.setInvestments(c.getInvestments() + order.getResearch());
			c.setAmende(0);
			c.setSubvention(0);

			double diffTreasury = gain - loss;
			c.setTreasury(diffTreasury + c.getTreasury());
		}
	}

	private double buyMachines(Company c) {
		double loss = 0;
		Map<Machine, Integer> map = c.getValidatedOrder().getBuyMachines();
		for (Map.Entry<Machine, Integer> entry : map.entrySet()) {
			Machine m = entry.getKey();
			loss += entry.getValue().intValue() * m.getPrice();
			Integer previousI = c.getMachineList().get(m);
			int previous = 0;
			if (previousI != null) {
				previous = previousI.intValue();
			}
			c.getMachineList().put(m, previous + entry.getValue().intValue());
		}
		return loss;
	}

	private void saveChange(Game game) {
		for (Company c : game.getCompanies()) {
			c.setValidatedOrder(null);
			companyManager.saveCompany(c);
		}
		gameManager.saveGame(game);
	}

}
