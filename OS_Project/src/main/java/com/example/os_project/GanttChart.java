package com.example.os_project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class GanttChart extends Application {

    Boolean done = false;
    int time = 1000;
    int i = 0;
    XYChart.Data data;


    @Override public void start(Stage stage) throws InterruptedException {
        stage.setTitle("Gantt Chart");
        final NumberAxis xAxis = new NumberAxis(0, 30, 1);
        final CategoryAxis yAxis = new CategoryAxis();
        final StackedBarChart<Number,String> bc =
                new StackedBarChart<>(xAxis,yAxis);

        bc.setTitle("Gantt Chart");
        bc.setLegendVisible(false);
        xAxis.setLabel("Timeline");
        xAxis.setTickLabelRotation(0);
        yAxis.setLabel("Process");
        yAxis.setTickLabelRotation(0);

        bc.setCategoryGap(150);

        //Color color[] = {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.BLACK}
        String [] color = {"RED", "BLUE", "GREEN", "CYAN", "MAGENTA", "BLACK"};

        Process p1 = new Process(1,0,2); // Red
        Process p2 = new Process(2,0,4); // Green
        Process p3 = new Process(3,2,1); // Blue
        Process p4 = new Process(4,3,5); // Cyan

        Process [] processes = {p1, p2, p3, p4};

        for(int i = 0; i < processes.length; i++){

            processes[i].color = color[i];

        }

        Burst p1_b1 = new Burst(p1,2);
        Burst p3_b1 = new Burst(p3,1);
        Burst p2_b1 = new Burst(p2,2);
        Burst p4_b1 = new Burst(p4,1);
        Burst p2_b2 = new Burst(p2,2);
        Burst p4_b2 = new Burst(p4,4);

        ArrayList<Burst> bs = new ArrayList<>();
        /*bs.add(b1);
        bs.add(b2);
        bs.add(b3);
        bs.add(b4);*/
        bs.add(p1_b1);
        bs.add(p3_b1);
        bs.add(p2_b1);
        bs.add(p4_b1);
        bs.add(p2_b2);
        bs.add(p4_b2);

        Label labelCnt = new Label();
        Label labelAnimated = new Label();

        TableView tableView =  new TableView();

        TableColumn<Process, String> remainingBurstTime   =
                new TableColumn<>("Remaining Burst Time");

        for(int i = 0; i < processes.length; i++){

            TableColumn<Process, String> column =
                    new TableColumn<>("P" + i);

            column.setCellValueFactory(
                    new PropertyValueFactory<>("P" + i));

            column.setSortable(false);

            //ObservableValue observableValue = column.getCellObservableValue(0);



            remainingBurstTime.getColumns().add(column);





        }

        tableView.getColumns().add(remainingBurstTime);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        VBox vBox = new VBox();
        vBox.getChildren().addAll(bc, tableView, labelAnimated);

        StackPane root = new StackPane();
        root.getChildren().add(vBox);


        Scene scene  = new Scene(root,1300,700);
        stage.setScene(scene);
        stage.show();

        ArrayList<XYChart.Series> series = new ArrayList<>();

        for(int i=0; i<bs.size(); i++){

            Burst b = bs.get(i);
            XYChart.Series s = new XYChart.Series();
            s.setName("P("+b.getP().getPid()+")");
            s.getData().add(new XYChart.Data(0,""));
            series.add(s);
            bc.getData().add(s);
        }

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000), (ActionEvent actionEvent) -> {


                    data = new XYChart.Data<>(bs.get(i).getQt(),"");
                    data.nodeProperty().addListener(new ChangeListener<Node>() {
                        @Override
                        public void changed(ObservableValue<? extends Node> ov, Node oldNode, Node newNode) {
                            newNode.setStyle("-fx-bar-fill: " + bs.get(i).getP().color + ";");
                        }
                    });

                    series.get(i).getData().set(0, data);
                    bs.get(i).getP().decrementRt(bs.get(i).getQt());

                    i++;

                    if(i == series.size()){
                        timeline.stop();
                    }

                }));


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        bc.setAnimated(false);

    }


    public static void main(String[] args) {
        launch(args);
    }
}