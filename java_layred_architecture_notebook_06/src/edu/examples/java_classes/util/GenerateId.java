package edu.examples.java_classes.util;

public final class GenerateId {
	
	private GenerateId() {}
	
	private static int nextId = 1;
	
	public static int nextId() {
		return nextId++;
	}

	public static int getNextId() {
		return nextId;
	}

	public static void setNextId(int nextId) {
		GenerateId.nextId = nextId;
	}

}
