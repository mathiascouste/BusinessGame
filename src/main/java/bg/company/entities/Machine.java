package bg.company.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MACHINE")
public class Machine implements Serializable {
	private static final long serialVersionUID = 582704735663312251L;
	private Long ident;
	private String name;
	private int price;
	private int productionCapacity;
	private int age;
	private int employeeNeeded;
	private int esperance;

	public Machine() {
		this.name = "";
		this.price = 0;
		this.productionCapacity = 0;
		this.age = 0;
		this.employeeNeeded = 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getIdent() == null) ? 0 : getIdent().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Machine)) {
			return false;
		}
		Machine other = (Machine) obj;
		if (getIdent() == null) {
			if (other.getIdent() != null) {
				return false;
			}
		} else if (!ident.equals(other.getIdent())) {
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

	@Column(name = "PRICE")
	@NotNull
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Column(name = "PROD_CAPACITY")
	@NotNull
	public int getProductionCapacity() {
		return productionCapacity;
	}

	public void setProductionCapacity(int productionCapacity) {
		this.productionCapacity = productionCapacity;
	}

	@Column(name = "AGE")
	@NotNull
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(name = "EMPLOYEE_NEEDED")
	@NotNull
	public int getEmployeeNeeded() {
		return employeeNeeded;
	}

	public void setEmployeeNeeded(int employeeNeeded) {
		this.employeeNeeded = employeeNeeded;
	}

	@Column(name = "ESPERANCE")
	@NotNull
	public int getEsperance() {
		return esperance;
	}

	public void setEsperance(int esperance) {
		this.esperance = esperance;
	}
}
