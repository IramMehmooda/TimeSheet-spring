package timesheet.service.Service;

/*
import java.nio.charset.StandardCharsets;
import org.hsqldb.jdbc.JDBCDriver;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.mysql.jdbc.Connection;*/

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import org.hsqldb.jdbc.JDBCDriver;
import javax.sql.DataSource;

import java.sql.DriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Base makes sure that before any test empty database is available.
 */
@ContextConfiguration(locations = {"/timesheet/src/main/resources/persistence-beans.xml"})
public abstract class DomainAwareBase extends AbstractJUnit4SpringContextTests {

	protected static DataSource datasource = new SimpleDriverDataSource(new JDBCDriver(), "jdbc:hsqldb:mem:dataSource", null, null);
	final Connection connection = datasource.getConnection();
	final JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
	private final String deleteScript = "/timesheet/src/main/resources/sql/cleanup.sql";


    @Before
    public void deleteAllDomainEntities() {
        ScriptUtils.executeSqlScript(connection, new EncodedResource(new ClassPathResource(deleteScript),StandardCharsets.UTF_8));
        
    }
}
