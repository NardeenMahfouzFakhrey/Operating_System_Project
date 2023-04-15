package com.example.os_project;
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
    private static TextField textProcess;
    private static ComboBox comboBoxSchedulers;
    private String [] schedulers;

    private FlowPane flowPane;

    @Override
    public void start(Stage stage) throws IOException {
        schedulers = new String[]{"FCFS","SJF Preemptive", "SJF Non Preemptive" , "Priority Preemptive","Priority Non Preemptive","Round Robin"};
        buttonOK=new Button("OK");
        buttonOK.setStyle("-fx-padding: 10;-fx-font-size: 14px; -fx-font-weight: bold;");
        buttonOK.setPrefWidth(100);
        FlowPane flowPane1 = new FlowPane(buttonOK);
        flowPane1.setAlignment(Pos.CENTER);
        labelScheduler = new Label("Scheduler Type");
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
        comboBoxSchedulers.setPromptText("Select Scheduler");
        VBox vboxScheduler = new VBox(labelScheduler,comboBoxSchedulers);
        vboxScheduler.setSpacing(10);
        vboxScheduler.setStyle("-fx-padding: 25;");
        VBox vboxProcess = new VBox(labelProcess,textProcess);
        vboxProcess.setSpacing(10);
        vboxProcess.setStyle("-fx-padding: 26;");
        HBox hbox = new HBox(vboxScheduler,vboxProcess);
        hbox.setSpacing(15);
        VBox vbox = new VBox(flowPane,hbox,flowPane1);
        vbox.setStyle("-fx-padding: 16;");
        vbox.setSpacing(10);
        Scene scene = new Scene(vbox, 500, 300);
        stage.setTitle("Scheduler Project");
        stage.setScene(scene);
        stage.show();
        buttonOK.setOnAction(new HelloController());
    }
    static String getProcesses(){
        return
                textProcess.getText();
    }
    static String getScheduler(){
        final String scheduler = comboBoxSchedulers.getSelectionModel().getSelectedItem().toString();
        return scheduler;
    }
    public static void main(String[] args) {
        launch();
    }
}