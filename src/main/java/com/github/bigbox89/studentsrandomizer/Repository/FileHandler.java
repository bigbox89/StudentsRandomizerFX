package com.github.bigbox89.studentsrandomizer.Repository;

import com.github.bigbox89.studentsrandomizer.Model.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileHandler implements IHandler {
	private FileReader fr;
	private BufferedReader br;
	private BufferedReader brr;
	private final File f;
	private final ArrayList<Student> students;

	public FileHandler(File f) {
		students = new ArrayList<>();
		if (f == null) {
			throw new NullPointerException();
		}
		this.f = f;
		try {
			fr = new FileReader(f);
			InputStreamReader frI = new InputStreamReader(
					new FileInputStream(f.getPath()), StandardCharsets.UTF_8);
			br = new BufferedReader(fr);
			brr = new BufferedReader(frI);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// read all students and save them in arraylist students
		loadStudents();
	}

	private void loadStudents() {
		String student;

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

		if (students.add(new Student(m.getCommand(), m.getSecondName(), m.getName(),m.getHomework(), m.getComment(), Integer.toString(m.getTestBall()),Integer.toString(m.getNumSkippings()),
				 m.getAsked(), m.getAnswered(), Float.toString(m.getRating()))))
			return "Студент добавлен.";
		else
			return "Ошибка.";
	}

	@Override
	public void editStudent(Student newStudent, Student selectedMovie) {
		for (Student m : students) {
			if (m.getCommand().equals(selectedMovie.getCommand()) && m.getTestBall().equals(selectedMovie.getTestBall())) {
				m.setCommand(newStudent.getCommand());
				m.setSecondName(newStudent.getSecondName());
				m.setName(newStudent.getName());
				m.setHomework(newStudent.getHomework());
				m.setComment(newStudent.getComment());
				m.setTestBall(newStudent.getTestBall());
				m.setNumSkippings(newStudent.getNumSkippings());
				m.setAsked(newStudent.getAsked() == 1);
				m.setAnswered(newStudent.getAnswered() == 1);
				m.setRating(newStudent.getRating());
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
				m.setNumSkippings(askedStudent.getNumSkippings());
				m.setAsked(askedStudent.getAsked() == 1);
				m.setAnswered(askedStudent.getAnswered() == 1);
				m.setRating(askedStudent.getRating());
			}

			if (m.getCommand().equals(answeredStudent.getCommand()) && m.getTestBall().equals(answeredStudent.getTestBall())) {
				m.setCommand(answeredStudent.getCommand());
				m.setSecondName(answeredStudent.getSecondName());
				m.setName(answeredStudent.getName());
				m.setHomework(answeredStudent.getHomework());
				m.setComment(answeredStudent.getComment());
				m.setTestBall(answeredStudent.getTestBall());
				m.setNumSkippings(answeredStudent.getNumSkippings());
				m.setAsked(answeredStudent.getAsked() == 1);
				m.setAnswered(answeredStudent.getAnswered() == 1);
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
		ArrayList<Student> filteredStudents = new ArrayList<>();
		String tempFilterName = filter.getCommand();
		String tempFilterDir = filter.getSecondName();
		int tempFilterYear = filter.getTestBall();
		int tempFilterDur = filter.getNumSkippings();
		String tempFilterCountry = filter.getName();
		String tempFilterActors = filter.getHomework();
		String tempFilterLanguage = filter.getComment();
		Float tempFilterRating =  filter.getRating();
		int tempFilterSeen = filter.getAsked();
		int tempFilterAnswered = filter.getAnswered();

		if (tempFilterDir.equals("")) // because an empty string occurs in every
										// string
			tempFilterDir = "pzazz";
		if (tempFilterName.equals("")) // because an empty string occurs in
										// every string
			tempFilterName = "pzazz";

		for (Student m : students) {
			if (m.getCommand().contains(tempFilterName) || m.getSecondName().contains(tempFilterDir)
					|| m.getTestBall().equals(tempFilterYear) || m.getNumSkippings().equals(tempFilterDur)
					|| m.getName().contains(tempFilterCountry)  || m.getHomework().contains(tempFilterActors)
			|| m.getComment().contains(tempFilterLanguage)  || m.getRating().equals(tempFilterRating)
			|| m.getAsked() == tempFilterSeen || m.getAnswered() == tempFilterAnswered)

				filteredStudents.add(m);
		}

		return filteredStudents;
	}

	@Override
	public void markAsked(Student m) {
		for (Student mov : students) {
			if (m.getCommand().equals(mov.getCommand()) && m.getTestBall().equals(mov.getTestBall())) {
				mov.setAsked(true);
				break;
			}
		}

	}

	@Override
	public void markUnAsked(Student m) {
		for (Student mov : students) {
			if (m.getCommand().equals(mov.getCommand()) && m.getTestBall().equals(mov.getTestBall())) {
				mov.setAsked(false);
				break;
			}
		}

	}

	@Override
	public void markAnswered(Student m) {
		for (Student mov : students) {
			if (m.getCommand().equals(mov.getCommand()) && m.getTestBall().equals(mov.getTestBall())) {
				mov.setAnswered(true);
				break;
			}
		}

	}

	@Override
	public void markUnAnswered(Student m) {
		for (Student mov : students) {
			if (m.getCommand().equals(mov.getCommand()) && m.getTestBall().equals(mov.getTestBall())) {
				mov.setAnswered(false);
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
			OutputStreamWriter fwO = new OutputStreamWriter(
					new FileOutputStream(f.getPath()), StandardCharsets.UTF_8);
				fwO.write("КОМАНДА;ФАМИЛИЯ;ИМЯ;ДЗ;КОММЕНТ;БАЛЛЫ ТЕСТА;ПРОПУСКИ;СПРАШИВАЛ;ОТВЕЧАЛ;РЕЙТИНГ;" + System.lineSeparator());
			for (Student m : students) {
				fwO.write(m.toString());
			}
			fwO.close();
			fr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
