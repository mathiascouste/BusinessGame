package bg.company.implem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import bg.company.entities.Product;
import bg.company.entities.StockedProduct;
import bg.company.interfaces.ProductManager;

@Stateless(name = "ProductManager")
public class ProductManagerBean implements ProductManager {

	@PersistenceContext(unitName = "database")
	EntityManager entityManager;

	@Override
	public Product createProduct(String name, int cost, int dev, int fixedCost) {
		Product product = new Product();
		product.setName(name);
		product.setCost(cost);
		product.setDevPrice(dev);
		product.setFixedProductionCost(fixedCost);
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

	@Override
	public void saveProduct(Product product) {
		if(entityManager.find(Product.class, product.getIdent()) == null) {
			entityManager.persist(product);
		} else {
			entityManager.merge(product);
		}
	}

	@Override
	public void saveStockedProduct(StockedProduct stockedProduct) {
		if(entityManager.find(StockedProduct.class, stockedProduct.getIdent()) == null) {
			entityManager.persist(stockedProduct);
		} else {
			entityManager.merge(stockedProduct);
		}
	}

}
