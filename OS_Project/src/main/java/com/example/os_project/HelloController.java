package com.example.os_project;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class HelloController extends HelloApplication implements EventHandler {
    Label labelTitle;
    String Scheduler;

    @Override
    public void handle(Event event) {
        System.out.println(HelloApplication.getScheduler());
    }
}