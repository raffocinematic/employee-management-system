package net.javaguides.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.ems.model.Employee;
import net.javaguides.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	// metodo per ritornare tutta la lista di impiegati al controller
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	//metodo per salvare un impiegato
	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);

	}

//metodo per prendere un impiegato dall'id
	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException("Employee not found for id : :" + id);
		}
		return employee;
	}

	//metodo per cancellare un impiegato
	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
		
	}

}
