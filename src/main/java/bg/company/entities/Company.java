package bg.company.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import bg.order.entities.Order;

@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {
	private static final long serialVersionUID = 4853323732822378959L;
	private Long ident;
	private String name;
	private String password;
	private double treasury;
	private int investments;
	private int employeeQuantity;
	private Map<Machine, Integer> machineList;
	private List<StockedProduct> productList;
	private double amende;
	private double subvention;
	private Order currentOrder;
	private Order validatedOrder;

	public Company() {
		this.treasury = 0;
		this.employeeQuantity = 0;
		this.investments = 0;
		this.machineList = new HashMap<Machine, Integer>();
		this.productList = new ArrayList<StockedProduct>();
		this.amende = 0;
		this.subvention = 0;
		this.currentOrder = null;
		this.validatedOrder = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.getIdent() == null) ? 0 : this.getIdent().hashCode());
		return result;
	}

	// 1 is ready, else 0
	public int isReady() {
		if (validatedOrder != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Company)) {
			return false;
		}
		Company other = (Company) obj;
		if (this.getIdent() == null) {
			if (other.getIdent() != null) {
				return false;
			}
		} else if (!this.getIdent().equals(other.getIdent())) {
			return false;
		}
		return true;
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

	@Column(name = "NAME")
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASSWORD")
	@NotNull
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "TREASURY", columnDefinition = "Double")
	@NotNull
	public double getTreasury() {
		return treasury;
	}

	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}

	@Column(name = "INVESTMENT")
	@NotNull
	public int getInvestments() {
		return investments;
	}

	public void setInvestments(int investments) {
		this.investments = investments;
	}

	@Column(name = "N_EMPLOYEE")
	@NotNull
	public int getEmployeeQuantity() {
		return employeeQuantity;
	}

	public void setEmployeeQuantity(int employeeQuantity) {
		this.employeeQuantity = employeeQuantity;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	public Map<Machine, Integer> getMachineList() {
		return machineList;
	}

	public void setMachineList(Map<Machine, Integer> machineList) {
		this.machineList = machineList;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<StockedProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<StockedProduct> productList) {
		this.productList = productList;
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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Order getValidatedOrder() {
		return validatedOrder;
	}

	public void setValidatedOrder(Order validatedOrder) {
		this.validatedOrder = validatedOrder;
	}
}
