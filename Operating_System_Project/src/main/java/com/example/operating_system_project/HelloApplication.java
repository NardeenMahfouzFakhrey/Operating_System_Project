package com.example.operating_system_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {

    private Button buttonOK;
    private Label labelScheduler;
    private Label labelProcess;
    private Label labelTitle;
    private TextField textProcess;
    private ComboBox comboBoxSchedulers;
    private String [] schedulers;

    private FlowPane flowPane;

    @Override
    public void start(Stage stage) throws IOException {
        schedulers = new String[]{"FCFS","SJF Preemptive", "SJF Non Preemptive" , "Priority Preemptive","Priority Non Preemptive","Round Robin"};
        buttonOK=new Button("OK");
        FlowPane flowPane1 = new FlowPane(buttonOK);
        labelScheduler = new Label("SchedulerType");
        labelScheduler.setFont(Font.font("Arial",FontWeight.BOLD,14));
        labelProcess = new Label("Number of processes");
        labelProcess.setFont(Font.font("Arial",FontWeight.BOLD,14));
        labelTitle = new Label("Scheduler");
        labelTitle.setTextFill(Color.DARKRED);
        labelTitle.setFont(Font.font("Verdana" , FontWeight.BOLD , FontPosture.ITALIC, 28));
        flowPane = new FlowPane(labelTitle);
        flowPane.setAlignment(Pos.CENTER);
        textProcess=new TextField();
        comboBoxSchedulers = new ComboBox<>(FXCollections.observableArrayList(schedulers));
        HBox hbox = new HBox(labelScheduler,labelProcess);
        hbox.setSpacing(30);
        hbox.setStyle("-fx-padding: 30;");
        HBox hbox1 = new HBox(comboBoxSchedulers,textProcess);
        hbox1.setSpacing(25);
        VBox vbox = new VBox(flowPane,hbox,hbox1,flowPane1);
        Scene scene = new Scene(vbox, 550, 400);
        stage.setTitle("Scheduler Project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}