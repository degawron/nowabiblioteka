package pl.igorr.nowabiblioteka.implementations;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.igorr.nowabiblioteka.db.LibraryDAO;


@Component
@Transactional
public class LibraryDAOImpl implements LibraryDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(Object object) {
		Session session = getSession();
		session.save(object);
	}

	@Override
	public Object get(Class<?> cl, int id) {
		Session session = getSession();
		Object object = session.get(cl, id);
		return object;
	}

	@Override
	public void update(Object object) {
		Session session = getSession();
		session.update(object);
	}

	@Override
	public List<?> list(String query) {
		Session session = getSession();
		List<?> list = session.createQuery(query).getResultList();
		return list;
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}
	
}
	