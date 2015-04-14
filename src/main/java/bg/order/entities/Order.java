package bg.order.entities;

import java.util.HashMap;
import java.util.Map;

import bg.company.entities.Company;

public class Order {
	private int employee;
	private int machine;
	private Map<Long, Integer> productProduction;
	private Map<Long, Integer> productAdvertising;
	private Map<Long, Integer> productQuality;
	private Map<Long, Integer> productPrice;
	private int generalAdvertising;
	private int investments;
	private int dividend;
	private Company company;

	public Order() {
		this.employee = 0;
		this.machine = 0;
		this.generalAdvertising = 0;
		this.investments = 0;
		this.dividend = 0;
		this.productAdvertising = new HashMap<Long, Integer>();
		this.productPrice = new HashMap<Long, Integer>();
		this.productProduction = new HashMap<Long, Integer>();
		this.productQuality = new HashMap<Long, Integer>();
		this.company = null;
	}

	public int getEmployee() {
		return employee;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	public int getMachine() {
		return machine;
	}

	public void setMachine(int machine) {
		this.machine = machine;
	}

	public Map<Long, Integer> getProductProduction() {
		return productProduction;
	}

	public void setProductProduction(Map<Long, Integer> productProduction) {
		this.productProduction = productProduction;
	}

	public Map<Long, Integer> getProductAdvertising() {
		return productAdvertising;
	}

	public void setProductAdvertising(Map<Long, Integer> productAdvertising) {
		this.productAdvertising = productAdvertising;
	}

	public Map<Long, Integer> getProductQuality() {
		return productQuality;
	}

	public void setProductQuality(Map<Long, Integer> productQuality) {
		this.productQuality = productQuality;
	}

	public Map<Long, Integer> getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Map<Long, Integer> productPrice) {
		this.productPrice = productPrice;
	}

	public int getGeneralAdvertising() {
		return generalAdvertising;
	}

	public void setGeneralAdvertising(int generalAdvertising) {
		this.generalAdvertising = generalAdvertising;
	}

	public int getInvestments() {
		return investments;
	}

	public void setInvestments(int investments) {
		this.investments = investments;
	}

	public int getDividend() {
		return dividend;
	}

	public void setDividend(int dividend) {
		this.dividend = dividend;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
