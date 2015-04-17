package bg.company.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import bg.company.entities.Company;
import bg.company.entities.Machine;
import bg.company.interfaces.CompanyManager;

@ViewScoped
@ManagedBean
public class ShowCompanyJsfBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Company company;

	@EJB
	CompanyManager companyManager;

	private Company getCompany() {
		if (this.company != null) {
			return this.company;
		}
		String strID = ((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getParameter("id");
		if (strID != null && !strID.isEmpty()) {
			Long id = new Long(strID);
			this.company = companyManager.findCompanyByID(id);
		} else {
		}
		return this.company;
	}

	public String getName() {
		if (this.getCompany() != null) {
			return company.getName();
		} else {
			return "";
		}
	}

	public double getTreasury() {
		if (this.getCompany() != null) {
			return company.getTreasury();
		} else {
			return 0;
		}
	}

	public int getResearch() {
		if (this.getCompany() != null) {
			return company.getInvestments();
		} else {
			return 0;
		}
	}

	public int getEmployee() {
		if (this.getCompany() != null) {
			return company.getEmployeeQuantity();
		} else {
			return 0;
		}
	}

	public class Entry {
		public String name;
		public int quantity;

		public Entry(String name, int quantity) {
			this.name = name;
			this.quantity = quantity;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	}

	public List<Entry> getMachineList() {
		List<Entry> entries = new ArrayList<Entry>();
		if (this.getCompany() != null) {
			for (Map.Entry<Machine, Integer> entry : company.getMachineList()
					.entrySet()) {
				entries.add(new Entry(entry.getKey().getName(), entry
						.getValue().intValue()));
			}
		}
		return entries;
	}
}
