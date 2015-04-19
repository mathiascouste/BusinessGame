package bg.order.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import bg.company.entities.Product;

@Entity
@Table(name = "PRODUCTION_ORDRE")
public class ProductionOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long ident;
	private int quantity;
	private int quality;
	private int advertising;
	private int sellPrice;
	private Product product;

	public ProductionOrder() {
		this.quantity = 0;
		this.quality = 0;
		this.advertising = 0;
		this.sellPrice = 0;
		this.product = null;
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
		if (!(obj instanceof ProductionOrder)) {
			return false;
		}
		ProductionOrder other = (ProductionOrder) obj;
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

	@Column(name = "QUANTITY")
	@NotNull
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "QUALITY")
	@NotNull
	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	@Column(name = "ADVERTISING")
	@NotNull
	public int getAdvertising() {
		return advertising;
	}

	public void setAdvertising(int advertising) {
		this.advertising = advertising;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "SELL_PRICE")
	@NotNull
	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
}
