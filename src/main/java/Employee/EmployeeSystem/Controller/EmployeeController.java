package Employee.EmployeeSystem.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Employee.EmployeeSystem.Exception.ResourceNotFoundException;
import Employee.EmployeeSystem.Repository.EmployeeRepository;
import Employee.EmployeeSystem.model.Employee;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeRepository empRepo;

	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return empRepo.findAll();
	}
	
	//create rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return empRepo.save(employee);
	}
	
	//get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee>  getEmployeeById(@PathVariable Long id) {
		Employee employee=empRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not exist with the id"+id));
		return ResponseEntity.ok(employee);
	}
	
	//update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
		Employee employee=empRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not exist with the id"+id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailID(employeeDetails.getEmailID());
		
		Employee updatedEmployee=empRepo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
		
	}
	
	//delete rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>>  deleteEmployee(@PathVariable Long id){
		Employee employee=empRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not exist with the id"+id));
		empRepo.delete(employee);
		Map<String, Boolean> response=new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
