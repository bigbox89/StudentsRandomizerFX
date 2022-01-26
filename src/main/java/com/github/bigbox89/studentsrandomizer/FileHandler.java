package com.github.bigbox89.studentsrandomizer;

import java.io.*;
import java.util.ArrayList;

public class FileHandler implements IHandler {
	private FileWriter fw;
	private OutputStreamWriter fwO;
	private FileReader fr;
	private InputStreamReader frI;
	private BufferedReader br;
	private BufferedReader brr;
	private File f;
	private ArrayList<Student> students;

	public FileHandler(File f) {
		students = new ArrayList<>();
		if (f == null) {
			throw new NullPointerException();
		}
		this.f = f;
		try {
			fr = new FileReader(f);
			frI= new InputStreamReader(
					new FileInputStream(f.getPath()), "UTF-8");
			br = new BufferedReader(fr);
			brr = new BufferedReader(frI);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// read all movies and save them in arraylist movies
		loadStudents();
	}

	private void loadStudents() {
		String student = null;

		try {
			brr.readLine();
			while ((student = brr.readLine()) != null) {
				System.out.println(student);
				String[] data = student.split(";");
				students.add(new Student(data[0], data[1], data[2], data[3], data[4], data[5], data[6], Integer.parseInt(data[7]), Integer.parseInt(data[8]),data[9]));
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Student> selectAllStudents() {
		return students;
	}

	@Override
	public String addStudent(Student m) {

		if (students.add(new Student(m.getCommand(), m.getSecondName(), m.getName(),m.getHomework(), m.getComment(), Integer.toString(m.getTestBall()),Integer.toString(m.getNumPropuskov()),
				 m.getAsked(), m.getAnswered(), Float.toString(m.getRating()))))
			return new String("Студент добавлен.");
		else
			return new String("Ошибка.");
	}

	@Override
	public void editStudent(Student newMovie, Student selectedMovie) {
		for (Student m : students) {
			if (m.getCommand().equals(selectedMovie.getCommand()) && m.getTestBall().equals(selectedMovie.getTestBall())) {
				m.setCommand(newMovie.getCommand());
				m.setSecondName(newMovie.getSecondName());
				m.setName(newMovie.getName());
				m.setHomework(newMovie.getHomework());
				m.setComment(newMovie.getComment());
				m.setTestBall(newMovie.getTestBall());
				m.setNumPropuskov(newMovie.getNumPropuskov());
				m.setAsked(newMovie.getAsked());
				m.setAnswered(newMovie.getAnswered());
				m.setRating(newMovie.getRating());
			}
		}
	}

	@Override
	public void editTwoStudents(Student askedStudent, Student answeredStudent) {
		for (Student m : students) {
			if (m.getCommand().equals(askedStudent.getCommand()) && m.getTestBall().equals(askedStudent.getTestBall())) {
				m.setCommand(askedStudent.getCommand());
				m.setSecondName(askedStudent.getSecondName());
				m.setName(askedStudent.getName());
				m.setHomework(askedStudent.getHomework());
				m.setComment(askedStudent.getComment());
				m.setTestBall(askedStudent.getTestBall());
				m.setNumPropuskov(askedStudent.getNumPropuskov());
				m.setAsked(askedStudent.getAsked());
				m.setAnswered(askedStudent.getAnswered());
				m.setRating(askedStudent.getRating());
			}

			if (m.getCommand().equals(answeredStudent.getCommand()) && m.getTestBall().equals(answeredStudent.getTestBall())) {
				m.setCommand(answeredStudent.getCommand());
				m.setSecondName(answeredStudent.getSecondName());
				m.setName(answeredStudent.getName());
				m.setHomework(answeredStudent.getHomework());
				m.setComment(answeredStudent.getComment());
				m.setTestBall(answeredStudent.getTestBall());
				m.setNumPropuskov(answeredStudent.getNumPropuskov());
				m.setAsked(answeredStudent.getAsked());
				m.setAnswered(answeredStudent.getAnswered());
				m.setRating(answeredStudent.getRating());
			}
		}
	}
	@Override
	public void delStudent(Student m) {
		Student toRemove = null;

		for (Student mov : students) {
			if (mov.getCommand().equals(m.getCommand()) && mov.getTestBall().equals(m.getTestBall())) {
				toRemove = mov;
				break;
			}
		}

		students.remove(toRemove);
	}

	@Override
	public ArrayList<Student> filterStudents(Student filter) {
		// TODO - check opciju koji se fieldovi gledaju
		ArrayList<Student> filteredMovies = new ArrayList<>();
		String tempFilterName = filter.getCommand();
		String tempFilterDir = filter.getSecondName();
		int tempFilterYear = filter.getTestBall();
		int tempFilterDur = filter.getNumPropuskov();
		String tempFilterCountry = filter.getName();
		String tempFilterActors = filter.getHomework();
		String tempFilterLanguage = filter.getComment();
		Float tempFilterRating =  filter.getRating();
		int tempFilterSeen = filter.getAsked();
		int tempFilterAnswered = filter.getAnswered();

		if (tempFilterDir.equals("")) // because an empty string occurs in every
										// string
			tempFilterDir = "zzzz";
		if (tempFilterName.equals("")) // because an empty string occurs in
										// every string
			tempFilterName = "zzzz";

		for (Student m : students) {
			if (m.getCommand().contains(tempFilterName) || m.getSecondName().contains(tempFilterDir)
					|| m.getTestBall().equals(tempFilterYear) || m.getNumPropuskov().equals(tempFilterDur)
					|| m.getName().contains(tempFilterCountry)  || m.getHomework().contains(tempFilterActors)
			|| m.getComment().contains(tempFilterLanguage)  || m.getRating().equals(tempFilterRating)
			|| m.getAsked() == tempFilterSeen || m.getAnswered() == tempFilterAnswered)

				filteredMovies.add(m);
		}

		return filteredMovies;
	}

	@Override
	public void markAsked(Student m) {
		for (Student mov : students) {
			if (m.getCommand().equals(mov.getCommand()) && m.getTestBall().equals(mov.getTestBall())) {
				mov.setAsked(1);
				break;
			}
		}

	}

	@Override
	public void markUnAsked(Student m) {
		for (Student mov : students) {
			if (m.getCommand().equals(mov.getCommand()) && m.getTestBall().equals(mov.getTestBall())) {
				mov.setAsked(0);
				break;
			}
		}

	}

	@Override
	public void markAnswered(Student m) {
		for (Student mov : students) {
			if (m.getCommand().equals(mov.getCommand()) && m.getTestBall().equals(mov.getTestBall())) {
				mov.setAnswered(1);
				break;
			}
		}

	}

	@Override
	public void markUnAnswered(Student m) {
		for (Student mov : students) {
			if (m.getCommand().equals(mov.getCommand()) && m.getTestBall().equals(mov.getTestBall())) {
				mov.setAnswered(0);
				break;
			}
		}

	}

	@Override
	public ArrayList<Student> selectAskedStudents() {
		ArrayList<Student> moviesSeen = new ArrayList<>();

		for (Student m : students)
			if (m.getAsked() == 1)
				moviesSeen.add(m);

		return moviesSeen;
	}

	@Override
	public ArrayList<Student> selectUnAskedStudents() {
		ArrayList<Student> moviesUnseen = new ArrayList<>();

		for (Student m : students)
			if (m.getAsked() == 0)
				moviesUnseen.add(m);

		return moviesUnseen;
	}


	@Override
	public ArrayList<Student> selectAnsweredStudents() {
		ArrayList<Student> moviesSeen = new ArrayList<>();

		for (Student m : students)
			if (m.getAnswered() == 1)
				moviesSeen.add(m);

		return moviesSeen;
	}

	@Override
	public ArrayList<Student> selectUnAnsweredStudents() {
		ArrayList<Student> moviesUnseen = new ArrayList<>();

		for (Student m : students)
			if (m.getAnswered() == 0)
				moviesUnseen.add(m);

		return moviesUnseen;
	}


	@Override
	public void closeConn() {

		try {
			fwO= new OutputStreamWriter(
					new FileOutputStream(f.getPath()), "UTF-8");
				fwO.write("КОМАНДА;ФАМИЛИЯ;ИМЯ;ДЗ;КОММЕНТ;БАЛЛЫ ТЕСТА;ПРОПУСКИ;СПРАШИВАЛ;ОТВЕЧАЛ;РЕЙТИНГ;" + System.lineSeparator());
			for (Student m : students) {
				fwO.write(m.toString());
			}
			fwO.close();
			fr.close();
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
