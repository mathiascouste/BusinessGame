package bg.game.implem;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import bg.company.entities.Company;
import bg.company.interfaces.CompanyManager;
import bg.game.entities.Game;
import bg.game.interfaces.AdministrateGame;
import bg.game.interfaces.SimulateYear;
import bg.order.entities.Order;

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

		// TODO : Produire les produits

		// TODO : Vendre sur le marché mondial

		// TODO : Evaluer bénéfice pour payer impôt et actionaires

		// TODO : Calculer interêt en cas d'endetement

		// TODO : Calculer coût des stocks

		this.saveChange(game);
	}

	private void applyGainAndLoss(Game game) {
		for (Company c : game.getCompanies()) {
			Order order = c.getValidatedOrder();
			double loss = 0;
			double gain = 0;
			int nPrevEmployee = c.getEmployeeQuantity();
			int nNextEmployee = order.getEmployee();
			int max = nPrevEmployee > nNextEmployee ? nPrevEmployee
					: nNextEmployee;
			loss += game.getFixedData().getCompanyCost();
			loss += order.getResearch();
			c.setInvestments(c.getInvestments() + order.getResearch());
			loss += max * order.getSalary();
			loss += c.getAmende();
			gain += c.getSubvention();
			c.setEmployeeQuantity(nNextEmployee);
			c.setAmende(0);
			c.setSubvention(0);

			double diffTreasury = gain - loss;
			c.setTreasury(diffTreasury + c.getTreasury());
		}
	}

	private void saveChange(Game game) {
		for (int i = 0; i < game.getCompanies().size(); i++) {
			companyManager.saveCompany(game.getCompanies().get(i));
		}
		gameManager.saveGame(game);
	}

}
