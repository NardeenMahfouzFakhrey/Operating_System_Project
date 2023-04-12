package com.example.os_project;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelloController extends HelloApplication implements EventHandler {
    String Scheduler;
    String Processes;
    int nProcesses;
    HBox hBoxRR;
    VBox vBoxProcess = new VBox();
    VBox vBoxArrival = new VBox();
    VBox vBoxBurst = new VBox();
    HBox hBoxP;
    @Override
    public void handle(Event event) {
        Stage stageScheduler = new Stage();

        Scheduler = HelloApplication.getScheduler();
        Label labelTitle1 = new Label(Scheduler);
        labelTitle1.setTextFill(Color.DARKRED);
        labelTitle1.setFont(Font.font("Verdana" , FontWeight.BOLD , FontPosture.ITALIC, 28));
        FlowPane flowTitle = new FlowPane(labelTitle1);
        flowTitle.setAlignment(Pos.CENTER);
        Processes = getProcesses();
        if(!(Processes.matches("-?\\d+"))){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Enter a Numerical value.");
            alert.showAndWait();
        }
        else{
            vBoxBurst.setPrefWidth(70);
            vBoxProcess.setPrefWidth(70);
            vBoxProcess.setPrefWidth(70);
            Label labelProcess = new Label("Processes");
            labelProcess.setFont(Font.font("Calibri",FontWeight.BOLD,14));
            Label labelArrival = new Label("Arrival Time");
            labelArrival.setFont(Font.font("Calibri",FontWeight.BOLD,14));
            Label labelBurst = new Label("Burst Time");
            labelBurst.setFont(Font.font("Calibri",FontWeight.BOLD,14));
            vBoxProcess.getChildren().add(labelProcess);
            vBoxArrival.getChildren().add(labelArrival);
            vBoxBurst.getChildren().add(labelBurst);
         nProcesses = Integer.parseInt(Processes);
         for(int i=1; i <= nProcesses ; i++){
             Label label = new Label("Process" + Integer.toString(i));
             label.setFont(Font.font("Calibri",FontWeight.SEMI_BOLD,14));
             TextField text1 = new TextField();
             text1.setPrefWidth(45);
             TextField text2 = new TextField();
             text2.setPrefWidth(45);
             vBoxProcess.getChildren().add(label);
             vBoxArrival.getChildren().add(text1);
             vBoxBurst.getChildren().add(text2);
         }
         hBoxP = new HBox(vBoxProcess,vBoxArrival,vBoxBurst);
        }
        if (Scheduler.equals("Priority Preemptive") || Scheduler.equals("Priority Non Preemptive") ) {
            Label labelPriority = new Label("Priority");
            labelPriority.setFont(Font.font("Calibri",FontWeight.BOLD,14));
            VBox vBoxPriority = new VBox(labelPriority);
            vBoxPriority.setPrefWidth(70);
            for(int i = 0; i < nProcesses; i++){
                TextField text = new TextField();
                text.setPrefWidth(45);
                vBoxPriority.getChildren().add(text);
            }
            vBoxPriority.setSpacing(15);
            hBoxP.getChildren().add(vBoxPriority);
        }
        else if (Scheduler.equals("Round Robin")){
            Label labelRR = new Label("Quantum Time");
            labelRR.setFont(Font.font("Calibri",FontWeight.BOLD,14));
            //labelRR.setWrapText(true);
            TextField textRR =new TextField();
            textRR.setPrefWidth(70);
            hBoxRR = new HBox(labelRR,textRR);
            hBoxRR.setSpacing(10);
        }
        vBoxArrival.setSpacing(15);
        vBoxBurst.setSpacing(15);
        vBoxProcess.setSpacing(22);
        hBoxP.setSpacing(15);
        VBox VPane = new VBox(flowTitle,hBoxP);
        if(hBoxRR != null){
            VPane.getChildren().add(hBoxRR);
        }
        VPane.setSpacing(15);
        VPane.setStyle("-fx-padding: 16;");
        Scene scene = new Scene(VPane, 550, 400);
        stageScheduler.setTitle("Scheduler Project");
        stageScheduler.setScene(scene);
        stageScheduler.show();
    }
}