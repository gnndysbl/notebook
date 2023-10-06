package edu.examples.java_classes.controller.impl;

import java.util.Date;

import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.entity.Note;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NotebookLogic;
import edu.examples.java_classes.util.GenerateId;

public class AddNoteCommand implements Command {

	private final LogicProvider logicProvider = LogicProvider.getInstance();
	private final NotebookLogic logic = logicProvider.getNotebookLogic();

	@Override
	public String execute(String request) {
		String response = null;
		String[] params;
		Note newNote;

		params = request.split("\n");
		newNote = new Note();
		newNote.setTitle(params[1].split("=")[1]);
		newNote.setContent(params[2].split("=")[1]);
		newNote.setD(new Date());
		newNote.setId(GenerateId.getNextId());
		try {
			logic.add(newNote);
			response = "Запись сохранена успешно.";
		} catch (LogicException e) {
			// log
			response = "Что-то пошло не так. Попробуйте еще раз.";
		}

		return response;
	}

}
