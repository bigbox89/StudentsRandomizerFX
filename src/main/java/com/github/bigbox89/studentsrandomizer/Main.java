package com.github.bigbox89.studentsrandomizer;

import com.github.bigbox89.studentsrandomizer.View.UICreator;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	UICreator ui;

	@Override
	public void start(Stage primaryStage) {
		ui = new UICreator(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
