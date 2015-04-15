package bg.company.interfaces;

import javax.ejb.Local;

import bg.company.entities.Company;

@Local
public interface CompanyManager {
	public Company createCompany(String name, String password);

	public void saveCompany(Company company);

	public Company findCompanyByID(Long ident);

	public Company findCompanyByName(String name);
}
