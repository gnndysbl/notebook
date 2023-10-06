package edu.examples.java_classes.controller;

import java.io.IOException;

public class Controller {
	private final char paramDelimeter = '\n';

	private final CommandProvider provider = new CommandProvider();

	public String doAction(String request) throws IOException {
		String command;
		command = request.substring(0, request.indexOf(paramDelimeter));
		command = command.toUpperCase();

		Command executionCommand;

		executionCommand = provider.getCommand(command);

		String response;
		response = executionCommand.execute(request);

		return response;

	}

}