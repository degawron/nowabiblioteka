package pl.igorr.nowabiblioteka.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.igorr.nowabiblioteka.api.ReaderService;
import pl.igorr.nowabiblioteka.db.LibraryDAO;
import pl.igorr.nowabiblioteka.domain.Reader;
import pl.igorr.nowabiblioteka.domain.ReadersView;

@Service
public class ReaderServiceImpl implements ReaderService {

	private LibraryDAO libraryDAO;
	
	@Autowired
	public void setLibraryDAO(LibraryDAO libraryDAO) {
		this.libraryDAO = libraryDAO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReadersView> listReaders() {
		List<ReadersView> list = (List<ReadersView>) libraryDAO.list("from ReadersView");
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reader> listActiveReaders() {
		List<Reader> list = (List<Reader>) libraryDAO.list("from Reader where enabled=1");
		return list;
	}
	
	@Override
	public int addReader(Reader reader) {
		libraryDAO.add(reader);
		return reader.getId();
	}

	@Override
	public Reader getReader(int id) {
		Reader reader = (Reader) libraryDAO.get(Reader.class, id);
		return reader;
	}

	@Override
	public int updateReader(Reader reader) {
		libraryDAO.update(reader);
		return reader.getId();
	}
	
}
