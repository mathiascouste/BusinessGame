package bg.game.entities;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import bg.company.entities.Company;
import bg.company.entities.Machine;
import bg.company.entities.Product;

@Entity
@Table(name = "GAME")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long ident;
	private String name;
	private String password;
	private int lapQuantity;
	private List<Company> companies;
	private List<Product> products;
	private List<Machine> machines;
	private FixedData fixedData;
	private FloatingData floatingData;

	public Game() {
		this.name = "";
		this.password = "";
		this.lapQuantity = 0;
		this.companies = new ArrayList<Company>();
		this.products = new ArrayList<Product>();
		this.machines = new ArrayList<Machine>();
		this.fixedData = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getIdent() == null) ? 0 : getIdent().hashCode());
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
		if (!(obj instanceof Game)) {
			return false;
		}
		Game other = (Game) obj;
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
		return this.ident;
	}

	public void setIdent(Long ident) {
		this.ident = ident;
	}

	@Column(name = "NAME")
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

	@Column(name = "LAP_QUANTITY")
	@NotNull
	public int getLapQuantity() {
		return lapQuantity;
	}

	public void setLapQuantity(int lapQuantity) {
		this.lapQuantity = lapQuantity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Machine> getMachines() {
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public FixedData getFixedData() {
		return fixedData;
	}

	public void setFixedData(FixedData fixedData) {
		this.fixedData = fixedData;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public FloatingData getFloatingData() {
		return floatingData;
	}

	public void setFloatingData(FloatingData floatingData) {
		this.floatingData = floatingData;
	}
}
