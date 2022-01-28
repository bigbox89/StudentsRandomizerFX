package com.github.bigbox89.studentsrandomizer.Controllers;

import com.github.bigbox89.studentsrandomizer.Model.Student;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UIController {

    public TableView<Student> getStudentTableView() {
        TableView<Student> studentsTable = new TableView<>();

        TableColumn nameCol = new TableColumn<>("Команда");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("command"));
        nameCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.05));

        TableColumn directorCol = new TableColumn<>("Имя");
        directorCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        directorCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.10));

        TableColumn countryCol = new TableColumn<>("Фамилия");
        countryCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn yearCol = new TableColumn<>("Баллы теста");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("testBall"));

        TableColumn ratingCol = new TableColumn<>("Рейтинг");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.05));

        TableColumn actorsCol = new TableColumn<>("Домашнее задание");
        actorsCol.setCellValueFactory(new PropertyValueFactory<>("homework"));
        actorsCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.1));

        TableColumn languageCol = new TableColumn<>("Комментарий");
        languageCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        languageCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.15));

        TableColumn durationCol = new TableColumn<>("Количество пропусков");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("numSkippings"));
        durationCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.15));

        TableColumn seenCol = new TableColumn<>("Задавал вопрос");
        seenCol.setCellValueFactory(new PropertyValueFactory<>("asked"));

        TableColumn answeredCol = new TableColumn<>("Отвечал на вопрос");
        answeredCol.setCellValueFactory(new PropertyValueFactory<>("answered"));

        studentsTable.getColumns().addAll(nameCol, directorCol, countryCol, yearCol, ratingCol, actorsCol, languageCol, durationCol, seenCol, answeredCol);
        return studentsTable;
    }
}
