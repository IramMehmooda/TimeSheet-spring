package service;

import java.util.List;

import domain.Employee;
import domain.Manager;
import domain.Task;

public interface TimeSheetService {
	Task busiestTask();
	List<Task> taskforEmployee(Employee e);
	List<Task> taskforManager(Manager m);
	

}
