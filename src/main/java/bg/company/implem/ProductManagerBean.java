package bg.company.implem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import bg.company.entities.Product;
import bg.company.interfaces.ProductManager;

@Stateless(name = "ProductManager")
public class ProductManagerBean implements ProductManager {

	@PersistenceContext(unitName = "database")
	EntityManager entityManager;

	@Override
	public Product createProduct(String name, int cost, int dev) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(cost);
		product.setDevPrice(dev);
		entityManager.persist(product);
		return product;
	}

	@Override
	public Product findProductByID(Long ident) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> from = criteria.from(Product.class);
		criteria.select(from);
		criteria.where(builder.equal(from.get("ID"), ident));
		TypedQuery<Product> query = entityManager.createQuery(criteria);

		return query.getSingleResult();
	}

}
