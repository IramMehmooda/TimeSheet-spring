package service;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import domain.Timesheet;
import service.dao.TimesheetDao;


@Repository("timesheetDao")
public class TimesheetDaoImpl extends HibernateDao<Timesheet, Long> implements TimesheetDao{
	
	@Override
	public List<Timesheet> list(){
		return currentSession().createCriteria(Timesheet.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
}
