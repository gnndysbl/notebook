package edu.examples.java_classes.controller;

import java.io.IOException;

public interface Command {
	String execute(String request) throws IOException;
}
