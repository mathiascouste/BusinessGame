package bg.company.entities;

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

@Entity
@Table(name = "STOCKED_PRODUCT")
public class StockedProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long ident;
	private Product product;
	private double price;
	private double quality;
	private double advertising;
	private int quantity;

	public StockedProduct() {
		this(null, 0);
	}

	public StockedProduct(Product product, int quantity) {
		this.product = product;
		this.price = 0;
		this.quality = 0;
		this.advertising = 0;
		this.quantity = quantity;
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
		if (!(obj instanceof StockedProduct)) {
			return false;
		}
		StockedProduct other = (StockedProduct) obj;
		if (getIdent() == getIdent()) {
			if (other.ident != null) {
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


	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "PRICE", columnDefinition = "Double")
	@NotNull
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "QUALITY", columnDefinition = "Double")
	@NotNull
	public double getQuality() {
		return quality;
	}

	public void setQuality(double quality) {
		this.quality = quality;
	}

	@Column(name = "ADVERTISING", columnDefinition = "Double")
	@NotNull
	public double getAdvertising() {
		return advertising;
	}

	public void setAdvertising(double advertising) {
		this.advertising = advertising;
	}

	@Column(name = "QUANTITY")
	@NotNull
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
