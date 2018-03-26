package service;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDao<E,K> implements GenericDao<E, K> {
	
	private List<E> entities=new ArrayList<E>();

	public void add(E entity) {
		// TODO Auto-generated method stub
		entities.add(entity);
	}

	public void update(E entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet");
	}

	public void remove(E entity) {
		// TODO Auto-generated method stub
		entities.remove(entity);
		
	}

	public E find(K key) {
		// TODO Auto-generated method stub
		if(entities.isEmpty()) {
			return null;
		}
		//dummy , set as the first variable
		return entities.get(0);
	}

	public List<E> list() {
		// TODO Auto-generated method stub
		return entities;
	}

}
