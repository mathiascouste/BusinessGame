package bg.company.interfaces;

import javax.ejb.Local;

import bg.company.entities.Machine;

@Local
public interface MachineManager {
	public Machine createMachine(String name, int price, int esperance,
			int capacity, int employeeNeeded);

	public Machine findMachineByID(Long ident);
}
