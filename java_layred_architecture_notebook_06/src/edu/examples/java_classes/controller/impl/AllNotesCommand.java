package edu.examples.java_classes.controller.impl;

import java.io.IOException;

import java.util.List;

import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NotebookLogic;

public class AllNotesCommand implements Command {

	private final LogicProvider logicProvider = LogicProvider.getInstance();
	private final NotebookLogic logic = logicProvider.getNotebookLogic();

	@Override
	public String execute(String request) throws IOException {
		String response = null;

		try {
			logic.allNotes();
			List<String> listNote = logic.allNotes();
			for (String element : listNote) {
				System.out.println(element);
			}
			response = "Выгружены все записи.";
		} catch (LogicException e) {
			// log
			response = "Что-то пошло не так. Попробуйте еще раз.";
		}

		return response;
	}

}
