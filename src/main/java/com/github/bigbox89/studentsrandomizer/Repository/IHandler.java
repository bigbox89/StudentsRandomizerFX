package com.github.bigbox89.studentsrandomizer.Repository;

import com.github.bigbox89.studentsrandomizer.Model.Student;

import java.util.ArrayList;

public interface IHandler {
	public ArrayList<Student> selectAllStudents();

	public ArrayList<Student> selectAskedStudents();

	public ArrayList<Student> selectUnAskedStudents();

	public ArrayList<Student> selectAnsweredStudents();

	public ArrayList<Student> selectUnAnsweredStudents();


	public String addStudent(Student m);

	public void editStudent(Student newMovie, Student selectedMovie);

	public void editTwoStudents(Student newMovie, Student selectedMovie);

	public void delStudent(Student m);

	public void markAsked(Student m);

	public void markUnAsked(Student m);

	public void markAnswered(Student m);

	public void markUnAnswered(Student m);

	public void closeConn();

	public ArrayList<Student> filterStudents(Student m);

}
