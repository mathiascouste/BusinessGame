package bg.company.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {
	private static final long serialVersionUID = 4853323732822378959L;
	private String name;
	private String password;
	private int treasury;
	private int investments;
	private int employeeQuantity;
	private List<Machine> machineList;
	private List<Product> productList;
	
	public Company() {
		this.treasury = 0;
		this.employeeQuantity = 0;
		this.investments = 0;
		this.machineList = new ArrayList<Machine>();
		this.productList = new ArrayList<Product>();
	}

	@Id
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

    @Column(name = "TREASURY")
    @NotNull
	public int getTreasury() {
		return treasury;
	}

	public void setTreasury(int treasury) {
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

	public List<Machine> getMachineList() {
		return machineList;
	}

	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}
