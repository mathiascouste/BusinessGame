package bg.order.implem;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.order.entities.Order;
import bg.order.interfaces.OrderManager;

@Stateless
public class OrderManagerBean implements OrderManager, Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "database")
	EntityManager entityManager;

	@Override
	public Order createOrder() {
		Order order = new Order();
		entityManager.persist(order);
		return order;
	}

	@Override
	public void saveOrder(Order order) {
		if(entityManager.find(Order.class, order.getIdent()) == null) {
			entityManager.persist(order);
		} else {
			entityManager.merge(order);
		}
	}

}
