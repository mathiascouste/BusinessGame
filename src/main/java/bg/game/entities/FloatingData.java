package bg.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FLOATINGDATA")
public class FloatingData {
	private Long ident;
	private double quality;
	private double advertising;
	private double fidelity;

	public FloatingData() {
		this.quality = 0;
		this.advertising = 0;
		this.fidelity = 0;
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
		if (!(obj instanceof FloatingData)) {
			return false;
		}
		FloatingData other = (FloatingData) obj;
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

	@Column(name = "FIDELITY", columnDefinition = "Double")
	@NotNull
	public double getFidelity() {
		return fidelity;
	}

	public void setFidelity(double fidelity) {
		this.fidelity = fidelity;
	}
}
