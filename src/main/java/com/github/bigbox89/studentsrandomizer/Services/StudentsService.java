package com.github.bigbox89.studentsrandomizer.Services;

import com.github.bigbox89.studentsrandomizer.Model.Student;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class StudentsService {
    Alert unAskedAlert;
    Alert unAnsweredAlert;
    List<Student> studentsCrew;
    CheckBox generateIfNotAskBox;

    public StudentsService(Alert unAskedAlert, Alert unAnsweredAlert, List<Student> studentsCrew, CheckBox generateIfNotAskBox) {
        this.unAskedAlert = unAskedAlert;
        this.unAnsweredAlert = unAnsweredAlert;
        this.studentsCrew = studentsCrew;
        this.generateIfNotAskBox = generateIfNotAskBox;
    }

    public Student[] getStudents() {
        Student[] studentsForAsking;
        if (generateIfNotAskBox.isSelected()) {
            List<Student> unaskedStudents = studentsCrew
                    .stream()
                    .filter(e -> e.getAsked() == 0)
                    .collect(Collectors.toList());

            List<Student> unasweredStudents = studentsCrew
                    .stream()
                    .filter(e -> e.getAnswered() == 0)
                    .collect(Collectors.toList());

            if (unaskedStudents.size() == 0) {
                unAskedAlert.showAndWait();
            }

            if (unasweredStudents.size() == 0) {
                unAnsweredAlert.showAndWait();
            }

            if (unAskedAlert.getResult() == ButtonType.YES || unAnsweredAlert.getResult() == ButtonType.YES) {
                return null;
            }

            studentsForAsking = generateRandom(unaskedStudents, unasweredStudents);

        } else {
            studentsForAsking = generateRandom(studentsCrew, studentsCrew);
        }
        return studentsForAsking;
}
    private Student[] generateRandom(List<Student> studentsForAsking, List<Student> studentsForAnswering) {
        Student[] randStudents = new Student[2];
        Random randNumsList = new Random();
        //получаем случайный номер студента

        int student1 = randNumsList.nextInt(studentsForAsking.size());
        int student2 = randNumsList.nextInt(studentsForAnswering.size());

        Student askingStudent = studentsForAsking.get(student1);
        Student answeringStudent = studentsForAnswering.get(student2);

        if (checkCommand(askingStudent, answeringStudent)) {
            generateRandom(studentsForAsking, studentsForAnswering);

        } else {
            randStudents[0] = askingStudent;
            randStudents[1] = answeringStudent;

            return randStudents;
        }
        return null;
    }

    private boolean checkCommand(Student first, Student second) {
        return Objects.equals(first.getCommand(), second.getCommand());
    }
}

