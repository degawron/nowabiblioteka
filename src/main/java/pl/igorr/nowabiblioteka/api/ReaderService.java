package pl.igorr.nowabiblioteka.api;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.igorr.nowabiblioteka.domain.Reader;
import pl.igorr.nowabiblioteka.domain.ReadersView;

@Repository
public interface ReaderService {

	public List<ReadersView> listReaders();
	public List<ReadersView> listActiveReaders();
	public int addReader(Reader reader);
	public Reader getReader(int id);
	public int updateReader(Reader reader);
	
}
