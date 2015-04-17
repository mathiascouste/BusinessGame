package bg.order.entities;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import bg.company.entities.Machine;

@Entity
@Table(name = "ORDRE")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long ident;
	private int employee;
	private int salary;
	private int research;
	private Map<Machine, Integer> buyMachines;

	public Order() {
		this.employee = 0;
		this.salary = 0;
		this.research = 0;
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

	@Column(name = "EMPLOYEE")
	@NotNull
	public int getEmployee() {
		return employee;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	@Column(name = "SALARY")
	@NotNull
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Column(name = "RESEARCH")
	@NotNull
	public int getResearch() {
		return research;
	}

	public void setResearch(int research) {
		this.research = research;
	}

    @ElementCollection(fetch = FetchType.EAGER)
	public Map<Machine, Integer> getBuyMachines() {
		return buyMachines;
	}

	public void setBuyMachines(Map<Machine, Integer> buyMachines) {
		this.buyMachines = buyMachines;
	}
}
