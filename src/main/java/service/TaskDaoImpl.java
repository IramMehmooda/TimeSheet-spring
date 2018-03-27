package service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import domain.Task;
import service.dao.TaskDao;

@Repository("taskDao")
public class TaskDaoImpl extends HibernateDao<Task, Long> implements TaskDao{

	public boolean removeTask(Task task) {
		Query taskQuery=currentSession().createQuery("from Timesheet t where t.task.id= :id");
		taskQuery.setParameter("id", task.getId());
		
		if(!taskQuery.list().isEmpty()) {
			return false;
		}
		
		remove(task);
		return true;
	}
	
	@Override
	public List<Task> list(){
		return currentSession().createCriteria(Task.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
}
