package com.github.bigbox89.studentsrandomizer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.prefs.Preferences;

public class UICreator {

    private ObservableList<Student> studentsData;
    private IHandler handler;
    private Preferences prefs;
    private File f;

    public UICreator(Stage stage) {
        prefs = Preferences.userNodeForPackage(this.getClass());

        create(stage);
    }

    public void createFileDialog(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        f = fileChooser.showSaveDialog(stage);
        try {
            prefs.putBoolean("FILE_SELECTED", true);
            prefs.put("PATH", f.getAbsolutePath());
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create(Stage primaryStage) {
        GridPane grid = new GridPane();

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(15);
        grid.getColumnConstraints().add(column);

        column = new ColumnConstraints();
        column.setPercentWidth(15);
        grid.getColumnConstraints().add(column);

        column = new ColumnConstraints();
        column.setPercentWidth(70);
        grid.getColumnConstraints().add(column);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15, 15, 15, 15));

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("Загрузить");

        MenuItem fileMItem = new MenuItem("Из файла");
        menuFile.getItems().addAll(fileMItem);

        Menu menuHelp = new Menu("Помощь");
        MenuItem aboutMItem = new MenuItem("О программе");
        menuHelp.getItems().addAll(aboutMItem);

        menuBar.getMenus().addAll(menuFile, menuHelp);

        Button selectAllBtn = new Button("Выбрать все");
        selectAllBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(selectAllBtn, 0, 3);

        Button addBtn = new Button("Добавить студента");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(addBtn, 0, 4);

        Button delBtn = new Button("Удалить студента");
        delBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(delBtn, 0, 5);

        Button editBtn = new Button("Редактировать студента");
        editBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(editBtn, 0, 6);

        Button filterBtn = new Button("Фильтр");
        filterBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(filterBtn, 0, 7);

        Button generateBtn = new Button("Сгенерировать опрос");
        generateBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(generateBtn, 0, 8);


        Text infoTxt = new Text("Запущено");
        grid.add(infoTxt, 0, 12);

        Text numberOfRowsTxt = new Text();
        grid.add(numberOfRowsTxt, 1, 12);

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
        durationCol.setCellValueFactory(new PropertyValueFactory<>("numPropuskov"));
        durationCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.15));

        TableColumn seenCol = new TableColumn<>("Задавал вопрос");
        seenCol.setCellValueFactory(new PropertyValueFactory<>("asked"));

        TableColumn answeredCol = new TableColumn<>("Отвечал на вопрос");
        answeredCol.setCellValueFactory(new PropertyValueFactory<>("answered"));

        studentsTable.getColumns().addAll(nameCol, directorCol, countryCol, yearCol, ratingCol, actorsCol, languageCol, durationCol, seenCol, answeredCol);

        grid.add(studentsTable, 1, 2, 10, 9);

        fileMItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (prefs.getBoolean("FILE_SELECTED", false)) { // prompts the
                    // user for
                    // creating a
                    // file to save
                    // students
                    f = new File("students.csv");
                } else {
                    createFileDialog(primaryStage);
                }

                handler = new FileHandler(f);

                infoTxt.setText("Файловый режим.");
                selectAllBtn.fire();
            }
        });

        aboutMItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert aboutAlert = new Alert(AlertType.INFORMATION);
                aboutAlert.setTitle("О программе");
                aboutAlert.setHeaderText(null);
                aboutAlert.setContentText(
                        "Система опроса студентов\n\n2022 г.");

                aboutAlert.showAndWait();
            }
        });

        selectAllBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (handler == null)
                    infoTxt.setText("Ошибка - не подключено.");
                else {
                    studentsData = FXCollections.observableArrayList(handler.selectAllStudents());
                    studentsTable.setItems(studentsData);
                    studentsTable.refresh();

                    infoTxt.setText("Выбрано все.");
                    numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));
                }
            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialog<Student> dialog = new Dialog<>();
                dialog.setTitle("Добавить название.");

                // Set the button types.
                ButtonType okBtn = new ButtonType("Добавить", ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);

                // Create the username and password labels and fields.
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 20, 10, 10));

                TextField nameTxt = new TextField();
                TextField dirTxt = new TextField();
                TextField yearTxt = new TextField();
                TextField durTxt = new TextField();
                TextField counTxt = new TextField();
                TextField actTxt = new TextField();
                TextField langTxt = new TextField();
                TextField raitTxt = new TextField();
                CheckBox askedBox = new CheckBox();
                CheckBox answeredBox = new CheckBox();

                grid.add(new Label("Команда :"), 0, 0);
                grid.add(nameTxt, 1, 0);
                grid.add(new Label("Имя :"), 0, 1);
                grid.add(dirTxt, 1, 1);
                grid.add(new Label("Фамилия :"), 0, 2);
                grid.add(counTxt, 1, 2);
                grid.add(new Label("Баллы теста :"), 0, 3);
                grid.add(yearTxt, 1, 3);
                grid.add(new Label("Рейтинг :"), 0, 4);
                grid.add(raitTxt, 1, 4);
                grid.add(new Label("Домашнее задание :"), 0, 5);
                grid.add(actTxt, 1, 5);
                grid.add(new Label("Комментарий :"), 0, 6);
                grid.add(langTxt, 1, 6);
                grid.add(new Label("Количество пропусков :"), 0, 7);
                grid.add(durTxt, 1, 7);
                grid.add(new Label("Задавал вопрос :"), 0, 8);
                grid.add(askedBox, 1, 8);
                grid.add(new Label("Отвечал на вопрос :"), 0, 9);
                grid.add(answeredBox, 1, 9);

                dialog.getDialogPane().setContent(grid);

                // Request focus on the username field by default.
                Platform.runLater(() -> nameTxt.requestFocus());

                dialog.setResultConverter(dialogButton -> {
                    int asked = 0;
                    if (askedBox.isSelected()) {
                        asked = 1;
                    }

                    int answered = 0;
                    if (askedBox.isSelected()) {
                        answered = 1;
                    }

                    if (dialogButton == okBtn) {
                        return new Student(nameTxt.getText(), dirTxt.getText(), counTxt.getText(), actTxt.getText(), langTxt.getText(), yearTxt.getText(), durTxt.getText(), asked, answered, raitTxt.getText()
                        );

                    }
                    return null;
                });

                Optional<Student> result = dialog.showAndWait();

                result.ifPresent(m -> {
                    System.out.println("Команда =" + m.getCommand() + " Имя =" + m.getSecondName() + " Фамилия =" + m.getName() + " Баллы теста =" + m.getTestBall()
                            + " Рейтинг =" + m.getRating()
                            + " Домашнее задание =" + m.getHomework()
                            + " Комментарий  =" + m.getComment()
                            + " Количество пропусков  =" + m.getNumPropuskov()
                            + " Задавал вопрос =" + m.getAsked());


                    String resultText = (handler.addStudent(m));

                    infoTxt.setText(resultText);
                    selectAllBtn.fire();
                    numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));

                });

            }
        });
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Student selMov = studentsTable.getSelectionModel().getSelectedItem();
                System.out.println(
                        "DELETE FROM student WHERE Команда =\"" + selMov.getCommand() + "\" AND Баллы теста =" + selMov.getTestBall() + ";");
                handler.delStudent(selMov);

                /** if not exception raised **/
                infoTxt.setText("Студент удален.");
                selectAllBtn.fire();
                numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));
            }
        });

        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialog<Student> dialog = new Dialog<>();
                dialog.setTitle("Редактировать студента.");

                Student selMov = studentsTable.getSelectionModel().getSelectedItem();
                if (selMov == null) {
                    infoTxt.setText("Студент не выбран.");
                    return;
                }

                // Set the button types.
                ButtonType okBtn = new ButtonType("Редактировать", ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);

                // Create the username and password labels and fields.
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 20, 10, 10));

                TextField nameTxt = new TextField(selMov.getCommand());
                TextField dirTxt = new TextField(selMov.getSecondName());
                TextField yearTxt = new TextField(Integer.toString(selMov.getTestBall()));
                TextField durTxt = new TextField(Integer.toString(selMov.getNumPropuskov()));
                TextField counTxt = new TextField(selMov.getName());
                TextField actTxt = new TextField(selMov.getHomework());
                TextField langTxt = new TextField(selMov.getComment());
                TextField raitTxt = new TextField(Float.toString(selMov.getRating()));
                CheckBox seenBox = new CheckBox();
                CheckBox answeredBox = new CheckBox();
                if (selMov.getAsked() == 1)
                    seenBox.setSelected(true);
                if (selMov.getAnswered() == 1)
                    answeredBox.setSelected(true);

                grid.add(new Label("Команда :"), 0, 0);
                grid.add(nameTxt, 1, 0);
                grid.add(new Label("Имя :"), 0, 1);
                grid.add(dirTxt, 1, 1);
                grid.add(new Label("Фамилия :"), 0, 2);
                grid.add(counTxt, 1, 2);
                grid.add(new Label("Баллы теста :"), 0, 3);
                grid.add(yearTxt, 1, 3);
                grid.add(new Label("Рейтинг:"), 0, 4);
                grid.add(raitTxt, 1, 4);
                grid.add(new Label("Домашнее задание :"), 0, 5);
                grid.add(actTxt, 1, 5);
                grid.add(new Label("Комментарий :"), 0, 6);
                grid.add(langTxt, 1, 6);
                grid.add(new Label("Количество пропусков :"), 0, 7);
                grid.add(durTxt, 1, 7);
                grid.add(new Label("Задавал вопрос :"), 0, 8);
                grid.add(seenBox, 1, 8);

                grid.add(new Label("Отвечал на вопрос :"), 0, 9);
                grid.add(answeredBox, 1, 9);

                dialog.getDialogPane().setContent(grid);

                // Request focus on the name field by default.
                Platform.runLater(() -> nameTxt.requestFocus());

                dialog.setResultConverter(dialogButton -> {
                    int asked = 0;
                    if (seenBox.isSelected()) {
                        asked = 1;
                    }

                    int answered = 0;
                    if (answeredBox.isSelected()) {
                        answered = 1;
                    }

                    if (dialogButton == okBtn) {
                        return new Student(nameTxt.getText(), dirTxt.getText(), counTxt.getText(), actTxt.getText(), langTxt.getText(), yearTxt.getText(), durTxt.getText(), asked, answered, raitTxt.getText()
                        );
                    }
                    return null;
                });

                Optional<Student> result = dialog.showAndWait();

                result.ifPresent(m -> {
                    System.out.println("UPDATE student SET Команда =\"" + m.getCommand() + "\", Баллы теста  =" + m.getTestBall() + ", Задавал вопрос ="
                            + m.getAsked() + ", Имя =(SELECT dirKey FROM director WHERE Команда =\"" + m.getSecondName()
                            + "\"), Количество пропусков =" + m.getNumPropuskov() + " WHERE Команда =\"" + selMov.getCommand() + "\" AND Баллы теста="
                            + selMov.getTestBall() + ";");
                    System.out.println("UPDATE Имя  SET name=\"" + m.getSecondName() + "\" WHERE name=\""
                            + selMov.getSecondName() + "\";");
                    handler.editStudent(m, selMov);

                    infoTxt.setText("Студент отредактирован.");
                    selectAllBtn.fire();
                    numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));

                });

            }
        });

        filterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialog<Student> dialog = new Dialog<>();
                dialog.setTitle("Фильтр");

                // Set the button types.
                ButtonType okBtn = new ButtonType("Фильтр", ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);

                // Create the username and password labels and fields.
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 20, 10, 10));

                TextField nameTxt = new TextField();
                TextField dirTxt = new TextField();
                TextField yearTxt = new TextField();
                TextField durTxt = new TextField();
                TextField counTxt = new TextField();
                TextField actTxt = new TextField();
                TextField langTxt = new TextField();
                CheckBox seenBox = new CheckBox();
                CheckBox answeredBox = new CheckBox();

                grid.add(new Label("Команда :"), 0, 0);
                grid.add(nameTxt, 1, 0);
                grid.add(new Label("Имя :"), 0, 1);
                grid.add(dirTxt, 1, 1);
                grid.add(new Label("Фамилия :"), 0, 2);
                grid.add(counTxt, 1, 2);
                grid.add(new Label("Баллы теста :"), 0, 3);
                grid.add(yearTxt, 1, 3);
                grid.add(new Label("Домашнее задание :"), 0, 4);
                grid.add(actTxt, 1, 4);
                grid.add(new Label("Комментарий :"), 0, 5);
                grid.add(langTxt, 1, 5);
                grid.add(new Label("Количество пропусков :"), 0, 6);
                grid.add(durTxt, 1, 6);
                grid.add(new Label("Задавал вопрос :"), 0, 7);
                grid.add(seenBox, 1, 7);
                grid.add(new Label("Отвечал на вопрос :"), 0, 8);
                grid.add(answeredBox, 1, 8);
                dialog.getDialogPane().setContent(grid);

                // Request focus on the username field by default.
                Platform.runLater(() -> nameTxt.requestFocus());

                dialog.setResultConverter(dialogButton -> {
                    int asked = 0;
                    int answered = 0;
                    if (dialogButton == okBtn) {
                        //set defaults for filtering
                        String name = "null";
                        String dir = "null";
                        String country = "null";
                        String actors = "null";
                        String lang = "null";
                        String year = "0";
                        String dur = "0";
                        String rait = "0.0";
                        if (nameTxt.getText().length() != 0)
                            name = nameTxt.getText();

                        if (dirTxt.getText().length() != 0)
                            dir = dirTxt.getText();

                        if (counTxt.getText().length() != 0)
                            country = counTxt.getText();

                        if (actTxt.getText().length() != 0)
                            actors = actTxt.getText();

                        if (langTxt.getText().length() != 0)
                            lang = langTxt.getText();

                        if (yearTxt.getText().length() != 0)
                            year = yearTxt.getText();

                        if (durTxt.getText().length() != 0)
                            dur = durTxt.getText();

                        if (seenBox.isSelected())
                            asked = 1;

                        if (answeredBox.isSelected())
                            answered = 1;

                        return new Student(name, dir, country, actors, lang, year, dur, asked, answered, rait
                        );
                    }
                    return null;
                });

                Optional<Student> result = dialog.showAndWait();

                result.ifPresent(m -> {
                    studentsData = FXCollections.observableArrayList(handler.filterStudents(m));
                    studentsTable.setItems(studentsData);
                    studentsTable.refresh();

                    infoTxt.setText("Студенты отфильтрованы.");
                    numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));
                });

            }
        });

        generateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialog<Student> dialog = new Dialog<>();
                dialog.setTitle("Сгенерировать опрос студентов.");

                Object[] arr = studentsTable.getItems().toArray();
                List<Student> studentsCrew = new ArrayList<>();

                for (int i = 0; i < arr.length; i++) {
                    studentsCrew.add((Student) arr[i]);
                }

                Student[] randStudents = new Student[2];
                Random randNumsList = new Random();
                //получаем случайный номер студента
                int student1 = randNumsList.nextInt(studentsCrew.size());
                int student2 = randNumsList.nextInt(studentsCrew.size());

                Student askingStudent = studentsCrew.get(student1);
                Student answeringStudent = studentsCrew.get(student2);

                // Set the button types.
                ButtonType okBtn = new ButtonType("Сохранить", ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);

                // Create the username and password labels and fields.
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 20, 10, 10));

                Label firstStudent = new Label(askingStudent.getSecondName() + " " + askingStudent.getName() + " из команды " + askingStudent.getCommand());
                Label secondStudent = new Label(answeringStudent.getSecondName() + " " + answeringStudent.getName() + " из команды " + answeringStudent.getCommand());
                TextField questionRate = new TextField();
                questionRate.setText("0.2");
                questionRate.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.01));
                TextField answerRate = new TextField();
                answerRate.setText("0.2");
                answerRate.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.01));

                CheckBox askedBox = new CheckBox();
                CheckBox answeredBox = new CheckBox();
                if (askingStudent.getAsked() == 1)
                    askedBox.setSelected(true);
                if (answeringStudent.getAnswered() == 1)
                    answeredBox.setSelected(true);

                grid.add(new Label("Задает вопрос :"), 0, 0);
                grid.add(firstStudent, 1, 0);

                grid.add(new Label("Отвечает на вопрос :"), 0, 1);
                grid.add(secondStudent, 1, 1);

                grid.add(new Label("Баллы за вопрос :"), 0, 2);
                grid.add(questionRate, 1, 2);

                grid.add(new Label("Баллы за ответ :"), 0, 3);
                grid.add(answerRate, 1, 3);


                grid.add(new Label("Вопрос зачтен :"), 0, 4);
                grid.add(askedBox, 1, 4);

                grid.add(new Label("Ответ зачтен :"), 0, 5);
                grid.add(answeredBox, 1, 5);

                dialog.getDialogPane().setContent(grid);

                // Request focus on the name field by default.
                Platform.runLater(() -> firstStudent.requestFocus());

                dialog.setResultConverter(dialogButton -> {
                    int asked = 0;
                    if (askedBox.isSelected()) {
                        asked = 1;
                        askingStudent.setAsked(asked);
                        askingStudent.setRating(askingStudent.getRating() + Float.parseFloat(questionRate.getText()));

                    } else {
                        asked = 0;
                        askingStudent.setAsked(asked);
                    }

                    int answered = 0;
                    if (answeredBox.isSelected()) {
                        answered = 1;
                        answeringStudent.setAnswered(answered);
                        answeringStudent.setRating(answeringStudent.getRating() + Float.parseFloat(answerRate.getText()));
                    } else {
                        answered = 0;
                        answeringStudent.setAnswered(answered);
                    }

                    if (dialogButton == okBtn) {
                        return askingStudent;
                    }
                    return null;
                });

                Optional<Student> result = dialog.showAndWait();

                result.ifPresent(m -> {
                    handler.editTwoStudents(askingStudent, answeringStudent);
                    infoTxt.setText("Взаимный опрос проведен.");
                    selectAllBtn.fire();
                    numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));
                });

            }
        });

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if (handler != null)
                    handler.closeConn();
            }
        });

        Scene scene = new Scene(new VBox(), 1200, 500);
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Система опроса студентов");

        primaryStage.show();
    }
}
