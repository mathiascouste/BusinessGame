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

@Entity
@Table(name = "GAME")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long ident;
	private String name;
	private String password;
	private int lapQuantity;
	private List<Company> companies;
	private FixedData fixedData;
	private FloatingData floatingData;

	public Game() {
		this.name = "";
		this.password = "";
		this.lapQuantity = 0;
		this.companies = new ArrayList<Company>();
		this.fixedData = null;
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
