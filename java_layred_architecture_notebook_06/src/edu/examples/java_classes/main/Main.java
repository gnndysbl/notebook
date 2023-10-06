package edu.examples.java_classes.main;

import java.io.IOException;

import edu.examples.java_classes.controller.Controller;

public class Main {

	public static void main(String[] args) throws IOException {
		Controller controller = new Controller();

		String request;
		String response;

//		request = "ADD\ntitle=Читать\nсоntent=перечитать лесика";
//		response = controller.doAction(request);
//		System.out.println(response);

		//
		request = "ALL_NOTES\n"; 
		response = controller.doAction(request);
		System.out.println(response);
				
//		request = "UPDATE\nid=1\ntitle=Купить\ncontent=цветы домой";
//		response = controller.doAction(request);
//		System.out.println(response);	
		
		request = "FIND_NOTE_BY_WORD\nword=гор"; 
		response = controller.doAction(request);
		System.out.println(response);
		
//		request = "DELETE\nId=5"; 
//		response = controller.doAction(request);
//		System.out.println(response);
		
		request = "ALL_NOTES\n"; 
		response = controller.doAction(request);
		System.out.println(response);
		
//		request = "bonk\n";
//		response = controller.doAction(request);
//		System.out.println(response);	
		
	}

}
