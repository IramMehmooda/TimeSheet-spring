package service.dao;

import domain.Employee;
import service.GenericDao;

public interface EmployeeDao extends GenericDao<Employee, Long>{
	boolean removeEmployee(Employee employee);

}
