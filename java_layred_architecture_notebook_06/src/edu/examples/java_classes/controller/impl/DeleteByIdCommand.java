package edu.examples.java_classes.controller.impl;

import java.io.IOException;
import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NotebookLogic;

public class DeleteByIdCommand implements Command {

	private final LogicProvider logicProvider = LogicProvider.getInstance();
	private final NotebookLogic logic = logicProvider.getNotebookLogic();

	@Override
	public String execute(String request) {
		String response = null;
		String[] params;

		// request = "DELETE\nId=5";

		params = request.split("=");
		int deleteId = Integer.parseInt(params[1]);
		
		try {
			logic.deleteById(deleteId);
			response = "Запись удалена успешно.";
			
		} catch (LogicException | IOException | DaoException e) {
			
			response = "Что-то случилось. Запись не удалена.";
		
		}

		return response;
	}
}
