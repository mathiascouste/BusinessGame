package bg.company.implem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import bg.company.entities.Company;
import bg.company.interfaces.CompanyManager;
import bg.game.entities.Game;

@Stateless(name = "CompanyManager")
public class CompanyManagerBean implements CompanyManager {

	@PersistenceContext(unitName = "database")
	EntityManager entityManager;

	@Override
	public Company createCompany(String name, String password) {
		Company company = entityManager.find(Company.class, name);
		if (company != null) {
			return null;
		} else {
			company = new Company();
			company.setName(name);
			company.setPassword(password);
			entityManager.persist(company);
		}
		return company;
	}

	@Override
	public Company findCompanyByID(Long ident) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		Root<Company> from = criteria.from(Company.class);
		criteria.select(from);
		criteria.where(builder.equal(from.get("ID"), ident));
		TypedQuery<Company> query = entityManager.createQuery(criteria);

		return query.getSingleResult();
	}

	@Override
	public Company findCompanyByName(String name) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		Root<Company> from = criteria.from(Company.class);
		criteria.select(from);
		criteria.where(builder.equal(from.get("NAME"), name));
		TypedQuery<Company> query = entityManager.createQuery(criteria);

		return query.getSingleResult();
	}

}
