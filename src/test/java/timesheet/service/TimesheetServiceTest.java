package timesheet.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

import domain.Employee;
import domain.Manager;
import domain.Task;
import service.TimeSheetService;
import service.dao.EmployeeDao;
import service.dao.ManagerDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "/timesheet/src/main/resources/persistence-beans.xml")
public class TimesheetServiceTest extends AbstractJUnit4SpringContextTests {
    
    @Autowired
    private TimeSheetService timesheetService;

    // resources for accessing data during the testing
    @SuppressWarnings("deprecation")
	@Autowired
    private SimpleJdbcTemplate jdbcTemplate;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ManagerDao managerDao;

    private final String createScript = "/timesheet/src/main/resources/sql/create-data.sql";
    private final String deleteScript = "/timesheet/src/main/resources/sql/cleanup.sql";

    @Before
    public void insertData() {
        SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate,
                new FileSystemResource(createScript), false);
    }

    @After
    public void cleanUp() {
        SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate,
                new FileSystemResource(deleteScript), false);
    }

    @Test
    public void testBusiestTask() {
        Task task = timesheetService.busiestTask();
        assertTrue(1 == task.getId());
    }
    
    @Test
    public void testTasksForEmployees() {
        Employee steve = employeeDao.find(1L);
        Employee bill = employeeDao.find(2L);
        
        assertEquals(2, timesheetService.taskforEmployee(steve).size());
        assertEquals(1, timesheetService.taskforEmployee(bill).size());
    }
    
    @Test
    public void testTasksForManagers() {
        Manager eric = managerDao.find(1L);
        assertEquals(1, timesheetService.taskforManager(eric).size());
    }
    
}
