package service;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;
import service.dao.ManagerDao;

@Repository("managerDao")
public class ManagerDaoImpl extends HibernateDao<Manager, Long> implements ManagerDao{

	public boolean removeManager(Manager manager) {
		
		Query managerquery = currentSession().createQuery("from Task t where t.manager.id= :id");
		managerquery.setParameter("id", manager.getId());
		
		if(!managerquery.list().isEmpty()) {
			return false;
		}
		
		remove(manager);
		return true;
	}
	

}
