package bg.company.interfaces;

import javax.ejb.Local;

import bg.company.entities.Product;

@Local
public interface ProductManager {
	public Product createProduct(String name, int cost, int dev);

	public Product findProductByID(Long ident);
}
