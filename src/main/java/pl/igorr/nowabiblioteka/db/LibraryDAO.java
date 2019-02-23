package pl.igorr.nowabiblioteka.db;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface LibraryDAO {
	
	public void add(Object object);
	public Object get(Class<?> cl, int id);
	public void update(Object object);
	public List<?> list(String query);

}
