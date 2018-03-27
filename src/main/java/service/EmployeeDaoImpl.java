package service;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import domain.Employee;
import service.dao.EmployeeDao;

@Repository("employeeDao")
public class EmployeeDaoImpl extends HibernateDao<Employee, Long> implements EmployeeDao{

	public boolean removeEmployee(Employee employee) {
		Query employeeTaskQuery=currentSession().createQuery("from Task t where :id in elements(t.assignedEmployees)");
		employeeTaskQuery.setParameter("id", employee.getId());
		if(!employeeTaskQuery.list().isEmpty()) {
			return false;
		}
		Query employeeTimeSheetQuery=currentSession().createQuery("from Timesheet t where t.who.id= :id");
		if(!employeeTimeSheetQuery.list().isEmpty()) {
			return false;
		}
		remove(employee);
		return true;
	}
	
}
