package bg.company.implem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import bg.company.entities.Machine;
import bg.company.interfaces.MachineManager;

@Stateless(name = "MachineManager")
public class MachineManagerBean implements MachineManager {

	@PersistenceContext(unitName = "database")
	EntityManager entityManager;

	@Override
	public Machine createMachine(String name, int price, int esperance,
			int capacity, int employeeNeeded) {
		Machine machine = new Machine();
		machine.setName(name);
		machine.setPrice(price);
		machine.setEsperance(esperance);
		machine.setProductionCapacity(capacity);
		machine.setEmployeeNeeded(employeeNeeded);
		entityManager.persist(machine);
		return machine;
	}

	@Override
	public Machine findMachineByID(Long ident) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Machine> criteria = builder.createQuery(Machine.class);
		Root<Machine> from = criteria.from(Machine.class);
		criteria.select(from);
		criteria.where(builder.equal(from.get("ID"), ident));
		TypedQuery<Machine> query = entityManager.createQuery(criteria);

		return query.getSingleResult();
	}
}
