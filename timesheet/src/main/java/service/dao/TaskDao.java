package service.dao;

import domain.Task;
import service.GenericDao;

public interface TaskDao extends GenericDao<Task, Long>{
	boolean removeTask(Task task);

}
