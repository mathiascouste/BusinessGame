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
	private int price;
	private int devPrice;
	private int advertising;
	private int quality;
	
	public Product() {
		this.name = "";
		this.price = 0;
		this.devPrice = 0;
		this.advertising = 0;
		this.quality = 0;
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
    @Column(name = "DEV_PRICE")
    @NotNull
	public int getDevPrice() {
		return devPrice;
	}

	public void setDevPrice(int devPrice) {
		this.devPrice = devPrice;
	}

    @Column(name = "ADVERTISING")
    @NotNull
	public int getAdvertising() {
		return advertising;
	}

	public void setAdvertising(int advertising) {
		this.advertising = advertising;
	}

    @Column(name = "QUALITY")
    @NotNull
	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}
}
