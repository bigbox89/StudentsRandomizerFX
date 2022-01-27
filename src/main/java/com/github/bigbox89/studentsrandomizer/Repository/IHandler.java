package com.github.bigbox89.studentsrandomizer.Repository;

import com.github.bigbox89.studentsrandomizer.Model.Student;

import java.util.ArrayList;

public interface IHandler {
	ArrayList<Student> selectAllStudents();

	ArrayList<Student> selectAskedStudents();

	ArrayList<Student> selectUnAskedStudents();

	ArrayList<Student> selectAnsweredStudents();

	ArrayList<Student> selectUnAnsweredStudents();

	String addStudent(Student m);

	void editStudent(Student newMovie, Student selectedMovie);

	void editTwoStudents(Student newMovie, Student selectedMovie);

	void delStudent(Student m);

	void markAsked(Student m);

	void markUnAsked(Student m);

	void markAnswered(Student m);

	void markUnAnswered(Student m);

	void closeConn();

	ArrayList<Student> filterStudents(Student m);

}
