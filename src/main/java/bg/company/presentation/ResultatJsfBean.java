package bg.company.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Cookie;

import tools.CookieHelper;
import bg.company.entities.Company;
import bg.company.interfaces.CompanyManager;
import bg.connexion.interfaces.Connexion;
import bg.order.entities.ProfitAndLossStatement;
import bg.order.interfaces.OrderManager;

@SessionScoped
@ManagedBean
public class ResultatJsfBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Company company;
	private String selectedYear;
	private ProfitAndLossStatement currentStatement;
	private List<String> years;

	@EJB
	Connexion connexion;

	@EJB
	CompanyManager companyManager;

	@EJB
	OrderManager orderManager;

	public ResultatJsfBean() {
		this.company = null;
		this.selectedYear = "2015";
		this.currentStatement = null;
	}

	public List<String> getYears() {
		this.years = new ArrayList<String>();
		this.getCompany();
		if (this.company == null) {
			return years;
		}
		for (ProfitAndLossStatement pls : this.company.getPastStatements()) {
			years.add("" + pls.getYear());
		}
		return years;
	}

	private Company getCompany() {
		if (this.company != null) {
			this.company = companyManager.findCompanyByID(this.company
					.getIdent());
			return this.company;
		}
		String companyID = "";
		String companyPassword = "";
		Cookie cookie = new CookieHelper().getCookie("company_ident");
		if (cookie != null) {
			companyID = cookie.getValue();
		}
		cookie = new CookieHelper().getCookie("company_password");
		if (cookie != null) {
			companyPassword = cookie.getValue();
		}
		Long companyIDL = connexion.connectToCompany(new Long(companyID),
				companyPassword);
		this.company = companyManager.findCompanyByID(companyIDL);
		return this.company;
	}

	public String selectYear() {
		this.getCompany();
		if (this.company == null) {
			System.out.println("FAIL : not logged in a company");
			return "fail";
		}
		for (ProfitAndLossStatement pls : this.company.getPastStatements()) {
			if (pls.getYear() == new Integer(selectedYear).intValue()) {
				this.currentStatement = pls;
				return "success";
			}
		}
		System.out
				.println("FAIL : did not manage to find this year into the company's past statements");
		return "fail";
	}

	public String getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(String selectedYear) {
		this.selectedYear = selectedYear;
	}

	public ProfitAndLossStatement getCurrentStatement() {
		if (this.currentStatement == null) {
			getCompany();
			if (this.company != null) {
				int index = this.company.getPastStatements().size() - 1;
				if (index >= 0) {
					this.currentStatement = this.company.getPastStatements()
							.get(index);
				}
			}
		}
		return currentStatement;
	}

	public void setCurrentStatement(ProfitAndLossStatement currentStatement) {
		this.currentStatement = currentStatement;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

}
