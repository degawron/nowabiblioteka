package pl.igorr.nowabiblioteka.tests;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=pl.igorr.nowabiblioteka.db.PersistenceConfig.class)
public class HibernateTest {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Test
	public void factoryNotNull() {
		assertNotNull(sessionFactory);	
	}

	@Test
	@Transactional
	public void createdSessionNotNull() {
		Session session = sessionFactory.getCurrentSession();
		assertNotNull(session);
	}
	
}
