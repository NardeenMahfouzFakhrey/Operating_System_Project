package com.example.os_project;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelloController extends HelloApplication implements EventHandler {
    String Scheduler;
    @Override
    public void handle(Event event) {
        Stage stageScheduler = new Stage();
        Scheduler = HelloApplication.getScheduler();
        Label labelTitle1 = new Label(Scheduler);
        labelTitle1.setTextFill(Color.DARKRED);
        labelTitle1.setFont(Font.font("Verdana" , FontWeight.BOLD , FontPosture.ITALIC, 28));
        FlowPane flowTitle = new FlowPane(labelTitle1);
        flowTitle.setAlignment(Pos.CENTER);

        if(Scheduler.equals("FCFS")){

        }
        else if (Scheduler.equals("SJF Preemptive")) {

        }
        else if (Scheduler.equals("SJF Non Preemptive")) {

        }
        else if (Scheduler.equals("Priority Preemptive")) {

        }
        else if (Scheduler.equals("Priority Non Preemptive")){

        }
        else if (Scheduler.equals("Round Robin")){

        }

        Scene scene = new Scene(flowTitle, 550, 400);
        stageScheduler.setTitle("Scheduler Project");
        stageScheduler.setScene(scene);
        stageScheduler.show();
    }
}