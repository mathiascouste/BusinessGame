package bg.order.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROFITANDLOSSSTATEMENT")
public class ProfitAndLossStatement {
	private Long ident;
	// annee passee evaluee
	private int year;
	/**** Charge et produit d'exploitation ****/
	/** Loss **/
	// achat de marchandise
	private double marchandise;
	// variation de stock
	private double stockVariation;
	// masse salariale
	private double payroll;
	// amortissement
	private double amortissement;
	// cout de production
	private double productionCost;
	/** Profit **/
	// vente de marchandise
	private double selling;

	/**** Charge financiere ****/
	/** Loss **/
	private double interest;

	/**** Charge et produit exceptionel ****/
	/** Loss **/
	private double amende;
	/** Profit **/
	private double subvention;

	public ProfitAndLossStatement() {
		this(0);
	}

	public ProfitAndLossStatement(int year) {
		this.year = year;
		this.marchandise = 0;
		this.stockVariation = 0;
		this.payroll = 0;
		this.amortissement = 0;
		this.selling = 0;
		this.interest = 0;
		this.amende = 0;
		this.subvention = 0;
		this.productionCost = 0;
	}

	public double calculateExploitationLoss() {
		double loss = 0;
		loss += marchandise;
		loss += stockVariation;
		loss += payroll;
		loss += amortissement;
		loss += productionCost;
		return loss;
	}

	public double calculateExploitationProfit() {
		double profit = 0;
		profit += selling;
		return profit;
	}

	public double calculateFinancialLoss() {
		double loss = 0;
		loss += interest;
		return loss;
	}

	public double calculateFinancialProfit() {
		double profit = 0;
		return profit;
	}

	public double calculateExceptionalLoss() {
		double loss = 0;
		loss += amende;
		return loss;
	}

	public double calculateExceptionalProfit() {
		double profit = 0;
		profit += subvention;
		return profit;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdent() {
		return ident;
	}

	public void setIdent(Long ident) {
		this.ident = ident;
	}

	@Column(name = "YEAR")
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(name = "MARCHANDISE", columnDefinition = "Double")
	public double getMarchandise() {
		return marchandise;
	}

	public void setMarchandise(double marchandise) {
		this.marchandise = marchandise;
	}

	@Column(name = "STOCK_VARIATION", columnDefinition = "Double")
	public double getStockVariation() {
		return stockVariation;
	}

	public void setStockVariation(double stockVariation) {
		this.stockVariation = stockVariation;
	}

	@Column(name = "PAYROLL", columnDefinition = "Double")
	public double getPayroll() {
		return payroll;
	}

	public void setPayroll(double payroll) {
		this.payroll = payroll;
	}

	@Column(name = "AMORTISSEMENT", columnDefinition = "Double")
	public double getAmortissement() {
		return amortissement;
	}

	public void setAmortissement(double amortissement) {
		this.amortissement = amortissement;
	}

	@Column(name = "SELLING", columnDefinition = "Double")
	public double getSelling() {
		return selling;
	}

	public void setSelling(double selling) {
		this.selling = selling;
	}

	@Column(name = "PRODUCTION_COST", columnDefinition = "Double")
	public double getProductionCost() {
		return productionCost;
	}

	public void setProductionCost(double productionCost) {
		this.productionCost = productionCost;
	}

	@Column(name = "INTEREST", columnDefinition = "Double")
	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	@Column(name = "AMENDE", columnDefinition = "Double")
	public double getAmende() {
		return amende;
	}

	public void setAmende(double amende) {
		this.amende = amende;
	}

	@Column(name = "SUBVENTION", columnDefinition = "Double")
	public double getSubvention() {
		return subvention;
	}

	public void setSubvention(double subvention) {
		this.subvention = subvention;
	}
}
