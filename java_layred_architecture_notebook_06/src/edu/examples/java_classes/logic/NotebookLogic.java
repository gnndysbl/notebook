package edu.examples.java_classes.logic;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.entity.Note;

public interface NotebookLogic {

	public void add(Note n) throws LogicException;

	public void update(Note n) throws LogicException, IOException;

	public void findNoteByWord(String word) throws LogicException;

	//public List<Note> findByD(Date date) throws LogicException;

	public List<String> allNotes() throws LogicException, IOException;

	public void deleteById(int deleteId) throws LogicException, IOException, DaoException;

	public List<Note> findByD(Date date) throws LogicException;
}
