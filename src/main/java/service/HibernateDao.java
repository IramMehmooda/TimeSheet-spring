package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class HibernateDao<E,K extends Serializable> implements GenericDao<E, K> {
	private SessionFactory sessionFactory;
	protected Class<? extends E> daoType;
	
	public HibernateDao() {
		daoType=(Class<E>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//@Override
	public void add(E entity) {
		// TODO Auto-generated method stub
		currentSession().save(entity);
	}

	public void update(E entity) {
		// TODO Auto-generated method stub
		currentSession().saveOrUpdate(entity);
		
	}

	public void remove(E entity) {
		// TODO Auto-generated method stub
		currentSession().delete(entity);
		
	}

	public E find(K key) {
		// TODO Auto-generated method stub
		return (E)currentSession().get(daoType, key);
	}

	public List<E> list() {
		// TODO Auto-generated method stub
		return currentSession().createCriteria(daoType).list();
	}

}
