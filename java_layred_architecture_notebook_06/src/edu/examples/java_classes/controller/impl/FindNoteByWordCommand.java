package edu.examples.java_classes.controller.impl;
import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NotebookLogic;

public class FindNoteByWordCommand implements Command {

	private final LogicProvider logicProvider = LogicProvider.getInstance();
	private final NotebookLogic logic = logicProvider.getNotebookLogic();

	@Override
	public String execute(String request) {
		String response = null;
		String[] params;

		// request = "FIND_NOTE_BY_WORD\nword=Посев"; 

		String word;
		params = request.split("\n");
		word = (params[1].split("=")[1]);
		
		try {
			logic.findNoteByWord(word);
			response = "Выведены подходящие по запросу записи";
		} catch (LogicException e) {
			// log
			response = "Что-то пошло не так. Попробуйте еще раз.";
		}

		return response;
	}

}
