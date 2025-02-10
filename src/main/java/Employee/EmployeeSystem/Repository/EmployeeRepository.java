package Employee.EmployeeSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Employee.EmployeeSystem.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
