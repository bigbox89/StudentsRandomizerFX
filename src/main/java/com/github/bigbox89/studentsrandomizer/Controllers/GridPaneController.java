package com.github.bigbox89.studentsrandomizer.Controllers;

import com.github.bigbox89.studentsrandomizer.Model.Student;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GridPaneController {
    private Student selStudent;
    private  GridPane gridPane;

    private TextField commandTxt;
    private TextField nameTxt;
    private TextField secondNameTxt;
    private TextField testRaitingTxt;
    private TextField numSkippingsTxt;
    private TextField homeworkTxt;
    private TextField commentTxt;
    private TextField raitTxt;
    private CheckBox askedBox;
    private CheckBox answeredBox;

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public CheckBox getAskedBox() {
        return askedBox;
    }

    public CheckBox getAnsweredBox() {
        return answeredBox;
    }

    public GridPaneController() {
        this.commandTxt = new TextField();
        this.nameTxt = new TextField();
        this.secondNameTxt = new TextField();
        this.testRaitingTxt = new TextField();
        this.numSkippingsTxt = new TextField();
        this.homeworkTxt = new TextField();
        this.commentTxt = new TextField();
        this.raitTxt = new TextField();
        this.askedBox = new CheckBox();
        this.answeredBox = new CheckBox();

        this.gridPane = new GridPane();
        this.gridPane.setHgap(10);
        this.gridPane.setVgap(10);
        this.gridPane.setPadding(new Insets(20, 20, 10, 10));

        this.gridPane.add(new Label("Команда :"), 0, 0);
        this.gridPane.add(commandTxt, 1, 0);
        this.gridPane.add(new Label("Имя :"), 0, 1);
        this.gridPane.add(nameTxt, 1, 1);
        this.gridPane.add(new Label("Фамилия :"), 0, 2);
        this.gridPane.add(secondNameTxt, 1, 2);
        this.gridPane.add(new Label("Баллы теста :"), 0, 3);
        this.gridPane.add(testRaitingTxt, 1, 3);
        this.gridPane.add(new Label("Рейтинг:"), 0, 4);
        this.gridPane.add(raitTxt, 1, 4);
        this.gridPane.add(new Label("Домашнее задание :"), 0, 5);
        this.gridPane.add(homeworkTxt, 1, 5);
        this.gridPane.add(new Label("Комментарий :"), 0, 6);
        this.gridPane.add(commentTxt, 1, 6);
        this.gridPane.add(new Label("Количество пропусков :"), 0, 7);
        this.gridPane.add(numSkippingsTxt, 1, 7);
        this.gridPane.add(new Label("Задавал вопрос :"), 0, 8);
        this.gridPane.add(askedBox, 1, 8);
        this.gridPane.add(new Label("Отвечал на вопрос :"), 0, 9);
        this.gridPane.add(answeredBox, 1, 9);
    }

    public void setAskedBox(boolean setBox) {
        this.askedBox.setSelected(setBox);
    }

    public void setAnsweredBox(boolean setBox) {
        this.answeredBox.setSelected(setBox);
    }

    public TextField getCommandTxt() {
        return commandTxt;
    }

    public void setCommandTxt(TextField commandTxt) {
        this.commandTxt = commandTxt;
    }

    public TextField getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(TextField nameTxt) {
        this.nameTxt = nameTxt;
    }

    public TextField getSecondNameTxt() {
        return secondNameTxt;
    }

    public void setSecondNameTxt(TextField secondNameTxt) {
        this.secondNameTxt = secondNameTxt;
    }

    public TextField getTestRaitingTxt() {
        return testRaitingTxt;
    }

    public void setTestRaitingTxt(TextField testRaitingTxt) {
        this.testRaitingTxt = testRaitingTxt;
    }

    public TextField getNumSkippingsTxt() {
        return numSkippingsTxt;
    }

    public void setNumSkippingsTxt(TextField numSkippingsTxt) {
        this.numSkippingsTxt = numSkippingsTxt;
    }

    public TextField getHomeworkTxt() {
        return homeworkTxt;
    }

    public void setHomeworkTxt(TextField homeworkTxt) {
        this.homeworkTxt = homeworkTxt;
    }

    public TextField getCommentTxt() {
        return commentTxt;
    }

    public void setCommentTxt(TextField commentTxt) {
        this.commentTxt = commentTxt;
    }

    public Student getSelStudent() {
        return selStudent;
    }

    public void setSelStudent(Student selStudent) {
        this.selStudent = selStudent;
    }

    public TextField getRaitTxt() {
        return raitTxt;
    }

    public void setRaitTxt(TextField raitTxt) {
        this.raitTxt = raitTxt;
    }



}
