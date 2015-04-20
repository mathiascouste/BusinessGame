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
@Table(name = "PRODUCT")
public class Product implements Serializable {
	private static final long serialVersionUID = 4656376279001993272L;
	private Long ident;
	private String name;
	private int devPrice;
	private int fixedProductionCost;
	private int cost;

	public Product() {
		this.name = "";
		this.devPrice = 0;
		this.fixedProductionCost = 0;
		this.setCost(0);
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
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		if (getIdent() == null) {
			if (other.getIdent() != null) {
				return false;
			}
		} else if (!getIdent().equals(other.getIdent())) {
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

	@Column(name = "DEV_PRICE")
	@NotNull
	public int getDevPrice() {
		return devPrice;
	}

	public void setDevPrice(int devPrice) {
		this.devPrice = devPrice;
	}

	@Column(name = "FIXED_PRODUCTION_COST")
	@NotNull
	public int getFixedProductionCost() {
		return fixedProductionCost;
	}

	public void setFixedProductionCost(int fixedProductionCost) {
		this.fixedProductionCost = fixedProductionCost;
	}

	@Column(name = "COST")
	@NotNull
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
