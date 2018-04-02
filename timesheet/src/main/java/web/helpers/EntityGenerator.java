package web.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Employee;
import domain.Manager;
import domain.Task;
import domain.Timesheet;
import service.dao.EmployeeDao;
import service.dao.ManagerDao;
import service.dao.TaskDao;
import service.dao.TimesheetDao;
import service.GenericDao;
import java.util.List;

/**
 * Small util helper for generating entities to simulate real system.
 */
@Service
public final class EntityGenerator {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TimesheetDao timesheetDao;

    public void generateDomain() {
        Employee steve = new Employee("Steve", "Design");
        Employee bill = new Employee("Bill", "Marketing");
        Employee linus = new Employee("Linus", "Programming");
        
        // free employees (no tasks/timesheets)
        Employee john = new Employee("John", "Beatles");
        Employee george = new Employee("Ringo", "Beatles");
        Employee paul = new Employee("Paul", "Beatles");

        Manager eric = new Manager("Eric");
        Manager larry = new Manager("Larry");
        
        // free managers
        Manager simon = new Manager("Simon");
        Manager garfunkel = new Manager("Garfunkel");

        addAll(employeeDao, steve, bill, linus, john, george,paul);
        addAll(managerDao, eric, larry, simon, garfunkel);

        Task springTask = new Task("Migration to Spring 3.1", eric, steve, linus);
        Task tomcatTask = new Task("Optimizing Tomcat", eric, bill);
        Task centosTask = new Task("Deploying to CentOS", larry, linus);

        addAll(taskDao, springTask, tomcatTask, centosTask);

        Timesheet linusOnSpring = new Timesheet(linus, springTask, 42);
        Timesheet billOnTomcat = new Timesheet(bill, tomcatTask, 30);

        addAll(timesheetDao, linusOnSpring, billOnTomcat);
    }
    
    public void deleteDomain() {
        List<Timesheet> timesheets = timesheetDao.list();
        for (Timesheet timesheet : timesheets) {
            timesheetDao.remove(timesheet);
        }

        List<Task> tasks = taskDao.list();
        for (Task task : tasks) {
            taskDao.remove(task);
        }

        List<Manager> managers = managerDao.list();
        for (Manager manager : managers) {
            managerDao.remove(manager);
        }

        List<Employee> employees = employeeDao.list();
        for (Employee employee : employees) {
            employeeDao.remove(employee);
        }
    }
    
    private <T> void addAll(GenericDao<T, Long> dao, T... entites) {
        for (T o : entites) {
            dao.add(o);
        }
    }
}
