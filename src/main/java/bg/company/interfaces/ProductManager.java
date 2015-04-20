package bg.company.interfaces;

import javax.ejb.Local;

import bg.company.entities.Product;
import bg.company.entities.StockedProduct;

@Local
public interface ProductManager {
	public Product createProduct(String name, int cost, int dev, int fixedCost);
	
	public void saveProduct(Product product);
	
	public void saveStockedProduct(StockedProduct stockedProduct);

	public Product findProductByID(Long ident);
}
