package edu.examples.java_classes.dao;

import java.io.IOException;
import java.util.List;

import edu.examples.java_classes.entity.Note;

public interface NoteBookDao {

	void save(Note n) throws DaoException, IOException;
	void update(Note n) throws DaoException, IOException;
	List<String> allNotes() throws DaoException, IOException;
	void findNoteByWord(String word) throws DaoException;
	void deleteById(int deleteId) throws DaoException, IOException;


}
