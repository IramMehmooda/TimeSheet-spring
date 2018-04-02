package service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import domain.Employee;
import domain.Manager;
import domain.Task;
import service.dao.TaskDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
@Service("timesheetService")
public class TimesheetServiceImpl implements TimeSheetService {

    // dependencies
    private SessionFactory sessionFactory;
    private TaskDao taskDao;

    private Random random = new Random();

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    
    public Task busiestTask() {
        List<Task> tasks = taskDao.list();
        if (tasks.isEmpty()) {
            return null;
        }
        
        Task busiest = tasks.get(0);
        for (Task task : tasks) {
            if (task.getAssignedEmployees().size() > busiest.getAssignedEmployees().size()) {
                busiest = task;
            }
        }
        
        return busiest;
    }

	public List<Task> taskforEmployee(Employee e) {
		List<Task> allTasks = taskDao.list();
        List<Task> tasksForEmployee = new ArrayList<Task>();
        
        for (Task task : allTasks) {
            if (task.getAssignedEmployees().contains(e)) {
                tasksForEmployee.add(task);
            }
        }

        return tasksForEmployee;
	}

	public List<Task> taskforManager(Manager m) {
		Query query = currentSession()
                .createQuery("from Task t where t.manager.id = :id");
        query.setParameter("id", m.getId());
        return query.list();
	}

	
}
