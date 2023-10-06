package edu.examples.java_classes.dao.impl;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import edu.examples.java_classes.util.GenerateId;
import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.dao.NoteBookDao;
import edu.examples.java_classes.entity.Note;

public class FileNoteBookDao implements NoteBookDao {

	@Override
	public void save(Note n) throws DaoException, IOException {

		InputStreamReader reader = new InputStreamReader(new FileInputStream("resources/notes.txt"), "UTF-8");
		BufferedReader breader = new BufferedReader(reader);
		@SuppressWarnings("unused")
		String line = null;
		try {
			while ((line = breader.readLine()) != null) {
				GenerateId.nextId();
				n.setId(GenerateId.getNextId());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String dateToStr = DateFormat.getDateInstance().format(n.getD());
		BufferedWriter bufWriter = null;

		try {
			bufWriter = new BufferedWriter(new FileWriter("resources/notes.txt", true));
			bufWriter.write(n.getId() + "\t" + dateToStr + "\t" + n.getTitle() + "\t" + n.getContent() + "\n");
			bufWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Note n) throws DaoException, IOException {

		int position = n.getId(); // позиция строки, которую будем обновлять
		int counter = 1;

		// читаю весь файл и записываю строки в два списка. список до строки update и
		// список после строки update

		InputStreamReader reader = new InputStreamReader(new FileInputStream("resources/notes.txt"), "UTF-8");
		BufferedReader breader = new BufferedReader(reader);
		String line = null;

		List<String> listNote = new ArrayList<>();
		List<String> listNote2 = new ArrayList<>();

		try {
			while ((line = breader.readLine()) != null) {
				if (counter < position) {
					listNote.add(line);
				}
				if (counter > position) {
					listNote2.add(line);
				}
				counter++;
			}
			reader.close();

		} catch (IOException e) {
		}

		// проверяю чтобы была запись с таким id и чтобы не уйти в 0 или минус.

		if (counter > 1 & counter > n.getId() & n.getId() > 0) {

		} else
			throw new DaoException();

		// удаляю все из файла
		try {
			PrintWriter pw = new PrintWriter("resources/notes.txt", "UTF-8");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// пишу в файле по очерези первый список, затем новую строку "update", затем
		// второй список
		BufferedWriter bufWriter = null;
		try {
			bufWriter = new BufferedWriter(new FileWriter("resources/notes.txt", true));
			for (String element : listNote) {
				bufWriter.write(element + "\n");
			}

			String dateToStr = DateFormat.getDateInstance().format(n.getD());

			bufWriter.write(n.getId() + "\t" + dateToStr + "\t" + n.getTitle() + "\t" + n.getContent() + "\n");

			for (String element : listNote2) {
				bufWriter.write(element + "\n");
			}

			bufWriter.close();
		} catch (IOException e) {
		}

	}

	@Override
	public List<String> allNotes() throws DaoException, IOException {

		InputStreamReader reader = new InputStreamReader(new FileInputStream("resources/notes.txt"), "UTF-8");
		BufferedReader breader = new BufferedReader(reader);
		String line = null;

		List<String> listNote = new ArrayList<>();

		try {
			while ((line = breader.readLine()) != null) {
				listNote.add(line);
			}
			reader.close();

		} catch (IOException e) {
		}

		return listNote;
	}

	public void findNoteByWord(String word) throws DaoException {

		List<Note> listNotes = new ArrayList<>();
		String[] params;
		List<String> listNote = null;

		try {
			listNote = allNotes();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		word = word.toLowerCase();

		for (String element : listNote) {

			// разбиваю строку и собираю в объект Note
			Note newNote = new Note();
			params = element.split("\t");
			int id = Integer.valueOf(params[0]);
			newNote.setId(id);
			newNote.setTitle(params[2]);
			newNote.setContent(params[3]);

			// собираю часть строки в объект Date
			String sDate1 = params[1];
			SimpleDateFormat formatter1 = new SimpleDateFormat("d MMM yyyy");
			try {
				newNote.setD(formatter1.parse(sDate1));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			listNotes.add(newNote);

		}

		// вывожу на консоль подходящие по поисковому запросу записи
		for (Note element : listNotes) {

			if (element.getContent().toLowerCase().contains(word) | element.getTitle().toLowerCase().contains(word)) {

				String dateToStr = DateFormat.getDateInstance().format(element.getD());
				System.out.println(
						element.getId() + "\t" + dateToStr + "\t" + element.getTitle() + "\t" + element.getContent());
			}

		}

	}

	public void deleteById(int deleteId) throws DaoException, IOException {

		// читаю весь файл и записываю строки в два списка. список до строки delete и
		// список после строки delete

		int counter = 1;

		InputStreamReader reader = new InputStreamReader(new FileInputStream("resources/notes.txt"), "UTF-8");
		BufferedReader breader = new BufferedReader(reader);
		String line = null;

		List<String> listNote = new ArrayList<>();
		List<String> listNote2 = new ArrayList<>();

		try {
			while ((line = breader.readLine()) != null) {
				if (counter < deleteId) {
					listNote.add(line);
				}
				if (counter > deleteId) {
					listNote2.add(line);
				}
				counter++;
			}
			reader.close();

		} catch (IOException e) {
		}

		// проверяю чтобы была запись с таким id

		if (counter > 1 & counter > deleteId & deleteId > 0) {

		} else
			throw new DaoException();

		// удаляю все из файла
		try {
			PrintWriter pw = new PrintWriter("resources/notes.txt", "UTF-8");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// пишу в файле по очерези первый список, затем второй список. разбираю строки и
		// собираю в объекты типа Note,
		// чтобы вернуть нумерацию
		BufferedWriter bufWriter = null;
		String[] params;
		int id = 1;

		try {
			bufWriter = new BufferedWriter(new FileWriter("resources/notes.txt", true));
			for (String element : listNote) {

				Note newNote = new Note();
				params = element.split("\t");
				newNote.setId(id);
				newNote.setTitle(params[2]);
				newNote.setContent(params[3]);
				id++;

				// собираю строку в объект Date
				String sDate1 = params[1];
				SimpleDateFormat formatter1 = new SimpleDateFormat("d MMM yyyy");
				try {
					newNote.setD(formatter1.parse(sDate1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String dateToStr = DateFormat.getDateInstance().format(newNote.getD());
				bufWriter.write(newNote.getId() + "\t" + dateToStr + "\t" + newNote.getTitle() + "\t"
						+ newNote.getContent() + "\n");
			}

			for (String element : listNote2) {
				Note newNote = new Note();
				params = element.split("\t");
				newNote.setId(id);
				newNote.setTitle(params[2]);
				newNote.setContent(params[3]);
				id++;

				// собираю строку в объект Date
				String sDate1 = params[1];
				SimpleDateFormat formatter1 = new SimpleDateFormat("d MMM yyyy");
				try {
					newNote.setD(formatter1.parse(sDate1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String dateToStr = DateFormat.getDateInstance().format(newNote.getD());
				bufWriter.write(newNote.getId() + "\t" + dateToStr + "\t" + newNote.getTitle() + "\t"
						+ newNote.getContent() + "\n");
			}

			bufWriter.close();
		} catch (IOException e) {
		}

	}

}
