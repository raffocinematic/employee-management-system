package net.javaguides.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.ems.model.Employee;
import net.javaguides.ems.service.EmployeeService;

@Controller
public class EmployeeController {
		
	@Autowired
	private EmployeeService employeeService;
	
	//mostra la lista di impiegati
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees",employeeService.getAllEmployees());
		return"index";
	}
	
	//handler method per mostrare il form di salvataggio del nuovo impiegato che arriva da index.html
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		//create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
		
	}
	
	@PostMapping("/saveEmployee") //all the form data will be binded to the employee
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		//salva un impiegato nel db
		employeeService.saveEmployee(employee);
		return "redirect:/";		
	}
	//NOTA: qui per un FOR mancante nel get mapping non funzionava 
//	@GetMapping("showFormUpdate/{id}")
//	public String showFormUpdate(@PathVariable ( value = "id") long id, Model model) {
//		
//		//get employee from the service
//		Employee employee = employeeService.getEmployeeById(id);
//		
//		//set employee as a model attribute to pre-populate the form
//		model.addAttribute("employee", employee);
//		return "update_employee";
//		
//	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable ( value = "id") long id) {
		//call delete employee method
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
		
		
		
		
		
	}

}
