package bg.order.interfaces;

import javax.ejb.Local;

import bg.order.entities.Order;

@Local
public interface OrderManager {
	public Order createOrder();
	public void saveOrder(Order order);
}
