package com.github.bigbox89.studentsrandomizer.View;

import com.github.bigbox89.studentsrandomizer.Services.GridPaneService;
import com.github.bigbox89.studentsrandomizer.Services.HibernateSessionFactory;
import com.github.bigbox89.studentsrandomizer.Services.StudentsService;
import com.github.bigbox89.studentsrandomizer.Model.Student;
import com.github.bigbox89.studentsrandomizer.Repository.FileHandler;
import com.github.bigbox89.studentsrandomizer.Repository.IHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.hibernate.Session;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.prefs.Preferences;

public class UICreator {

    private ObservableList<Student> studentsData;
    private IHandler handler;
    private final Preferences prefs;
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

        Menu menuFile = new Menu("??????????????????");

        MenuItem fileMItem = new MenuItem("???? ??????????");
        menuFile.getItems().addAll(fileMItem);

        MenuItem dataBaseMItem = new MenuItem("???? ????????");
        menuFile.getItems().addAll(dataBaseMItem);

        Menu menuHelp = new Menu("????????????");
        MenuItem aboutMItem = new MenuItem("?? ??????????????????");
        menuHelp.getItems().addAll(aboutMItem);

        menuBar.getMenus().addAll(menuFile, menuHelp);

        Button selectAllBtn = new Button("?????????????? ??????");
        selectAllBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(selectAllBtn, 0, 3);

        Button addBtn = new Button("???????????????? ????????????????");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(addBtn, 0, 4);

        Button delBtn = new Button("?????????????? ????????????????");
        delBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(delBtn, 0, 5);

        Button editBtn = new Button("?????????????????????????? ????????????????");
        editBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(editBtn, 0, 6);

        CheckBox generateIfNotAskBox = new CheckBox();
        generateIfNotAskBox.setText("???????????????????? ????????????????????");
        grid.add(generateIfNotAskBox, 0, 8);

        Button generateBtn = new Button("?????????????????????????? ??????????");
        generateBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(generateBtn, 0, 9);


        Text infoTxt = new Text("????????????????");
        grid.add(infoTxt, 0, 12);

        Text numberOfRowsTxt = new Text();
        grid.add(numberOfRowsTxt, 1, 12);

        TableView<Student> studentsTable = new TableView<>();

        TableColumn nameCol = new TableColumn<>("??????????????");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("command"));
        nameCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.05));

        TableColumn directorCol = new TableColumn<>("??????");
        directorCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        directorCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.10));

        TableColumn countryCol = new TableColumn<>("??????????????");
        countryCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn yearCol = new TableColumn<>("?????????? ??????????");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("testBall"));

        TableColumn ratingCol = new TableColumn<>("??????????????");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.05));

        TableColumn actorsCol = new TableColumn<>("???????????????? ??????????????");
        actorsCol.setCellValueFactory(new PropertyValueFactory<>("homework"));
        actorsCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.1));

        TableColumn languageCol = new TableColumn<>("??????????????????????");
        languageCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        languageCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.15));

        TableColumn durationCol = new TableColumn<>("???????????????????? ??????????????????");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("numSkippings"));
        durationCol.prefWidthProperty().bind(studentsTable.widthProperty().multiply(0.15));

        TableColumn seenCol = new TableColumn<>("?????????????? ????????????");
        seenCol.setCellValueFactory(new PropertyValueFactory<>("asked"));

        TableColumn answeredCol = new TableColumn<>("?????????????? ???? ????????????");
        answeredCol.setCellValueFactory(new PropertyValueFactory<>("answered"));

        studentsTable.getColumns().addAll(nameCol, directorCol, countryCol, yearCol, ratingCol, actorsCol, languageCol, durationCol, seenCol, answeredCol);

        grid.add(studentsTable, 1, 2, 10, 9);

        fileMItem.setOnAction(event -> {
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

            infoTxt.setText("???????????????? ??????????.");
            selectAllBtn.fire();
        });

        dataBaseMItem.setOnAction(event -> {
            System.out.println("Hibernate tutorial");

            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            session.beginTransaction();

            Student studentEntity = new Student();

            Object[] arr = studentsTable.getItems().toArray();

            for (Object o : arr) {
                session.save((Student) o);
            }
            session.getTransaction().commit();
            session.close();
        });


        aboutMItem.setOnAction(event -> {
            Alert aboutAlert = new Alert(AlertType.INFORMATION);
            aboutAlert.setTitle("?? ??????????????????");
            aboutAlert.setHeaderText(null);
            aboutAlert.setContentText(
                    "?????????????? ???????????? ??????????????????\n\n2022 ??.");
            aboutAlert.showAndWait();
        });

        selectAllBtn.setOnAction(event -> {
            if (handler == null)
                infoTxt.setText("???????????? - ???? ????????????????????.");
            else {
                studentsData = FXCollections.observableArrayList(handler.selectAllStudents());
                studentsTable.setItems(studentsData);
                studentsTable.refresh();

                infoTxt.setText("?????????????? ??????.");
                numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));
            }
        });

        addBtn.setOnAction(event -> {
            Dialog<Student> dialog = new Dialog<>();
            dialog.setTitle("???????????????? ????????????????.");

            // Set the button types.
            ButtonType okBtn = new ButtonType("????????????????", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);

            // Create the username and password labels and fields.
            GridPaneService grid1 = new GridPaneService();
            dialog.getDialogPane().setContent(grid1.getGridPane());

            // Request focus on the username field by default.
            Platform.runLater(grid1.getNameTxt()::requestFocus);

            dialog.setResultConverter(dialogButton -> dialogButton == okBtn ? grid1.getStudentFromGrid() : null);

            Optional<Student> result = dialog.showAndWait();

            result.ifPresent(m -> {
                System.out.println("?????????????? =" + m.getCommand() + " ?????? =" + m.getSecondName() + " ?????????????? =" + m.getName() + " ?????????? ?????????? =" + m.getTestBall()
                        + " ?????????????? =" + m.getRating()
                        + " ???????????????? ?????????????? =" + m.getHomework()
                        + " ??????????????????????  =" + m.getComment()
                        + " ???????????????????? ??????????????????  =" + m.getNumSkippings()
                        + " ?????????????? ???????????? =" + m.getAsked());

                String resultText = (handler.addStudent(m));

                infoTxt.setText(resultText);
                selectAllBtn.fire();
                numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));

            });

        });
        delBtn.setOnAction(event -> {
            Student selMov = studentsTable.getSelectionModel().getSelectedItem();
            System.out.println(
                    "DELETE FROM student WHERE ?????????????? =\"" + selMov.getCommand() + "\" AND ?????????? ?????????? =" + selMov.getTestBall() + ";");
            handler.delStudent(selMov);

            infoTxt.setText("?????????????? ????????????.");
            selectAllBtn.fire();
            numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));
        });

        editBtn.setOnAction(event -> {
            Dialog<Student> dialog = new Dialog<>();
            dialog.setTitle("?????????????????????????? ????????????????.");

            Student selStudent = studentsTable.getSelectionModel().getSelectedItem();
            if (selStudent == null) {
                infoTxt.setText("?????????????? ???? ????????????.");
                return;
            }
            // Set the button types.
            ButtonType okBtn = new ButtonType("??????????????????????????", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);
            GridPaneService grid12 = new GridPaneService(selStudent);
            dialog.getDialogPane().setContent(grid12.getGridPane());
            // Request focus on the name field by default.
            Platform.runLater(grid12.getNameTxt()::requestFocus);
            dialog.setResultConverter(dialogButton -> dialogButton == okBtn ? grid12.getStudentFromGrid() : null);

            Optional<Student> result = dialog.showAndWait();
            result.ifPresent(m -> {
                System.out.println("UPDATE student SET ?????????????? =\"" + m.getCommand() + "\", ?????????? ??????????  =" + m.getTestBall() + ", ?????????????? ???????????? ="
                        + m.getAsked() + ", ?????? =(SELECT dirKey FROM director WHERE ?????????????? =\"" + m.getSecondName()
                        + "\"), ???????????????????? ?????????????????? =" + m.getNumSkippings() + " WHERE ?????????????? =\"" + selStudent.getCommand() + "\" AND ?????????? ??????????="
                        + selStudent.getTestBall() + ";");
                System.out.println("UPDATE ??????  SET name=\"" + m.getSecondName() + "\" WHERE name=\""
                        + selStudent.getSecondName() + "\";");
                handler.editStudent(m, selStudent);

                infoTxt.setText("?????????????? ????????????????????????????.");
                selectAllBtn.fire();
                numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));

            });

        });

        generateBtn.setOnAction(event -> {
            Dialog<Student> dialog = new Dialog<>();
            dialog.setTitle("?????????????????????????? ?????????? ??????????????????.");

            Alert unAskedAlert = new Alert(AlertType.ERROR, "???????????????????? ???? ???????????????????? ???????????? ??????????????????: 0", ButtonType.YES);
            Alert unAnsweredAlert = new Alert(AlertType.ERROR, "???????????????????? ???? ???????????????????? ??????????????????: 0", ButtonType.YES);

            Object[] arr = studentsTable.getItems().toArray();
            List<Student> studentsCrew = new ArrayList<>();

            Student[] studentsForAsking;

            for (Object o : arr) {
                studentsCrew.add((Student) o);
            }

            StudentsService studentsService = new StudentsService(unAskedAlert, unAnsweredAlert, studentsCrew, generateIfNotAskBox);
            studentsForAsking = studentsService.getStudents();

            if (studentsForAsking == null) return;

            Student askingStudent = studentsForAsking[0];
            Student answeringStudent = studentsForAsking[1];

            // Set the button types.
            ButtonType okBtn = new ButtonType("??????????????????", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okBtn, ButtonType.CANCEL);

            // Create the username and password labels and fields.
            GridPane grid14 = new GridPane();
            grid14.setHgap(10);
            grid14.setVgap(10);
            grid14.setPadding(new Insets(20, 20, 10, 10));

            Label firstStudent = new Label(askingStudent.getSecondName() + " " + askingStudent.getName() + " ???? ?????????????? " + askingStudent.getCommand());
            Label secondStudent = new Label(answeringStudent.getSecondName() + " " + answeringStudent.getName() + " ???? ?????????????? " + answeringStudent.getCommand());
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

            grid14.add(new Label("???????????? ???????????? :"), 0, 0);
            grid14.add(firstStudent, 1, 0);

            grid14.add(new Label("???????????????? ???? ???????????? :"), 0, 1);
            grid14.add(secondStudent, 1, 1);

            grid14.add(new Label("?????????? ???? ???????????? :"), 0, 2);
            grid14.add(questionRate, 1, 2);

            grid14.add(new Label("?????????? ???? ?????????? :"), 0, 3);
            grid14.add(answerRate, 1, 3);

            grid14.add(new Label("???????????? ???????????? :"), 0, 4);
            grid14.add(askedBox, 1, 4);

            grid14.add(new Label("?????????? ???????????? :"), 0, 5);
            grid14.add(answeredBox, 1, 5);

            dialog.getDialogPane().setContent(grid14);

            // Request focus on the name field by default.
            Platform.runLater(firstStudent::requestFocus);

            dialog.setResultConverter(dialogButton -> {
                int asked;
                if (askedBox.isSelected()) {
                    asked = 1;
                    askingStudent.setAsked(askedBox.isSelected());
                    askingStudent.setRating(askingStudent.getRating() + Float.parseFloat(questionRate.getText()));

                }

                int answered = 0;
                if (answeredBox.isSelected()) {
                    answeringStudent.setAnswered(answeredBox.isSelected());
                    answeringStudent.setRating(answeringStudent.getRating() + Float.parseFloat(answerRate.getText()));
                }

                if (dialogButton == okBtn) {
                    return askingStudent;
                }
                return null;
            });

            Optional<Student> result = dialog.showAndWait();

            result.ifPresent(m -> {
                handler.editTwoStudents(askingStudent, answeringStudent);
                infoTxt.setText("???????????????? ?????????? ????????????????.");
                selectAllBtn.fire();
                numberOfRowsTxt.setText(Integer.toString(studentsTable.getItems().size()));
            });

        });

        primaryStage.setOnCloseRequest(we -> {
            if (handler != null)
                handler.closeConn();
        });

        Scene scene = new Scene(new VBox(), 1200, 500);

        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, grid);

        primaryStage.setScene(scene);

        primaryStage.setTitle("?????????????? ???????????? ??????????????????");

        primaryStage.show();
    }
}
