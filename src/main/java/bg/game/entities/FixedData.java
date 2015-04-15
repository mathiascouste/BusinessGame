package bg.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FIXEDDATA")
public class FixedData {
	private Long ident;
	private int population;
	private double startTresory;
	private double tax;
	private double interest;
	private int companyCost;
	private int minimalSalary;
	
	public FixedData() {
		this.population = 0;
		this.startTresory = 0;
		this.tax = 0;
		this.interest = 0;
		this.companyCost = 0;
		this.minimalSalary = 0;
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

    @Column(name = "POPULATION")
    @NotNull
	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

    @Column(name = "START_TRESORY", columnDefinition = "Double")
    @NotNull
	public double getStartTresory() {
		return startTresory;
	}

	public void setStartTresory(double startTresory) {
		this.startTresory = startTresory;
	}

    @Column(name = "TAX", columnDefinition = "Double")
    @NotNull
	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

    @Column(name = "INTEREST", columnDefinition = "Double")
    @NotNull
	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

    @Column(name = "COMPANY_FIXED_COST")
    @NotNull
	public int getCompanyCost() {
		return companyCost;
	}

	public void setCompanyCost(int companyCost) {
		this.companyCost = companyCost;
	}

    @Column(name = "MINIMAL_SALARY")
    @NotNull
	public int getMinimalSalary() {
		return minimalSalary;
	}

	public void setMinimalSalary(int minimalSalary) {
		this.minimalSalary = minimalSalary;
	}
}
