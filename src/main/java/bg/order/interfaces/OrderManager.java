package bg.order.interfaces;

import javax.ejb.Local;

import bg.order.entities.Order;
import bg.order.entities.ProfitAndLossStatement;

@Local
public interface OrderManager {
	public Order createOrder();
	public void saveOrder(Order order);
	public void saveProfitAndLossStatement(ProfitAndLossStatement pls);
}
