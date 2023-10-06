package edu.examples.java_classes.logic.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.dao.DaoProvider;
import edu.examples.java_classes.dao.NoteBookDao;
import edu.examples.java_classes.entity.Note;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.NotebookLogic;

public class NotebookLogicImpl implements NotebookLogic {
	private final DaoProvider provider = DaoProvider.getInstance();
	private final NoteBookDao dao = provider.getNoteBookDao();

	public void add(Note n) throws LogicException {
		try {
			try {
				dao.save(n);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	public void update(Note n) throws LogicException, IOException {

		try {
			dao.update(n);
		} catch (DaoException e) {
			throw new LogicException(e);
		}

	}

	public List<String> allNotes() throws LogicException, IOException {
		try {
			return dao.allNotes();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void findNoteByWord(String word) throws LogicException {

		try {
			dao.findNoteByWord(word);
		} catch (DaoException e) {
			throw new LogicException(e);

		}

	}

	@Override
	public List<Note> findByD(Date date) throws LogicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int deleteId) throws LogicException, IOException, DaoException {

		dao.deleteById(deleteId);

	}
}
