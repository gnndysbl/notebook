package edu.examples.java_classes.controller;

import java.util.HashMap;

import java.util.Map;

import edu.examples.java_classes.controller.impl.AddNoteCommand;
import edu.examples.java_classes.controller.impl.AllNotesCommand;
import edu.examples.java_classes.controller.impl.DeleteByIdCommand;
import edu.examples.java_classes.controller.impl.NoSuchCommand;
import edu.examples.java_classes.controller.impl.UpdateNoteCommand;
import edu.examples.java_classes.controller.impl.FindNoteByWordCommand;



public class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<>();
	
	CommandProvider(){
		repository.put(CommandName.ADD, new AddNoteCommand());
		repository.put(CommandName.UPDATE, new UpdateNoteCommand());
		repository.put(CommandName.ALL_NOTES, new AllNotesCommand());
		repository.put(CommandName.FIND_NOTE_BY_WORD, new FindNoteByWordCommand());
		repository.put(CommandName.DELETE, new DeleteByIdCommand());
		
		
		//...
		repository.put(CommandName.WRONG_REQUEST, new NoSuchCommand());
	}
	
	Command getCommand(String name){
		CommandName commandName =null;
		Command command = null;
		
		try{
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		}catch(IllegalArgumentException | NullPointerException e){
			//write log
			command = repository.get(CommandName.WRONG_REQUEST);
		}				
		return command;
	}

}
