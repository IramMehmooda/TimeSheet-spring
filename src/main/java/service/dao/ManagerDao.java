package service.dao;


import domain.Manager;
import service.GenericDao;

public interface ManagerDao extends GenericDao<Manager, Long>{
	boolean removeManager(Manager manager);

}
