package bg.company.interfaces;

import javax.ejb.Local;

import bg.company.entities.Product;

@Local
public interface ProductManager {
	public Product createProduct(String name, int cost, int dev, int fixedCost);
	
	public void saveProduct(Product product);

	public Product findProductByID(Long ident);
}
