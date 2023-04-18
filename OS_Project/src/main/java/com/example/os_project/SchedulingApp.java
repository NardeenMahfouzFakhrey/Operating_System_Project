package com.example.os_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class SchedulingApp extends Application {

    private String scheduler = null;
    TextField textProcess = null;
    String Processes = null;
    int nProcesses;
    public ArrayList<Process> ps;
    int quantum;
    Boolean isPriority;
    Boolean isQuantum;
    ArrayList<Burst> bs = null;

    int r = 0,k = 0;
    XYChart.Data data;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        scene1(stage);
    }

    public void scene1(Stage stage){

        Button buttonOK;
        Label labelScheduler;
        Label labelProcess;
        Label labelTitle;

        ComboBox comboBoxSchedulers;
        String [] schedulers;
        FlowPane flowPane;

        schedulers = new String[]{"FCFS","SJF Preemptive", "SJF Non Preemptive" , "Priority Preemptive","Priority Non Preemptive","Round Robin"};
        buttonOK=new Button("OK");
        buttonOK.setStyle("-fx-padding: 10;-fx-font-size: 14px; -fx-font-weight: bold;");
        buttonOK.setPrefWidth(100);
        FlowPane flowPane1 = new FlowPane(buttonOK);
        flowPane1.setAlignment(Pos.CENTER);
        labelScheduler = new Label("Scheduler Type");
        labelScheduler.setFont(Font.font("Arial", FontWeight.BOLD,14));
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

        buttonOK.setOnAction(e -> {
            Object o = comboBoxSchedulers.getSelectionModel().getSelectedItem();
            if(o == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Select a scheduler type");
                alert.showAndWait();
            } else if (textProcess == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Enter the processes number.");
                alert.showAndWait();
            } else{
                Processes = textProcess.getText();
                if (!(Processes.matches("-?\\d+"))) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Enter a Numerical value.");
                    alert.showAndWait();
                } else{
                    scheduler = comboBoxSchedulers.getSelectionModel().getSelectedItem().toString();
                    if(scheduler.equals("Priority Preemptive") || scheduler.equals("Priority Non Preemptive"))
                        isPriority = true;
                    else isPriority = false;
                    if(scheduler.equals("Round Robin"))
                        isQuantum = true;
                    else isQuantum = false;
                    scene2(stage);
                }
            }
        });
    }

    public void scene2(Stage stage){

        VBox vBoxProcess = new VBox();
        VBox vBoxID = new VBox();
        VBox vBoxArrival = new VBox();
        VBox vBoxBurst = new VBox();
        HBox hBoxP = null;
        HBox hBoxRR = null;
        Button ButtonRun;

        Label labelTitle1 = new Label(scheduler);
        labelTitle1.setTextFill(Color.DARKRED);
        labelTitle1.setFont(Font.font("Verdana" , FontWeight.BOLD , FontPosture.ITALIC, 28));
        FlowPane flowTitle = new FlowPane(labelTitle1);
        flowTitle.setAlignment(Pos.CENTER);

        vBoxBurst.setPrefWidth(70);
        vBoxProcess.setPrefWidth(70);
        vBoxProcess.setPrefWidth(70);
        Label labelProcess = new Label("Processes");
        labelProcess.setFont(Font.font("Calibri",FontWeight.BOLD,14));
        Label labelID = new Label("Process ID");
        labelID.setFont(Font.font("Calibri",FontWeight.BOLD,14));
        Label labelArrival = new Label("Arrival Time");
        labelArrival.setFont(Font.font("Calibri",FontWeight.BOLD,14));
        Label labelBurst = new Label("Burst Time");
        labelBurst.setFont(Font.font("Calibri",FontWeight.BOLD,14));
        vBoxProcess.getChildren().add(labelProcess);
        vBoxID.getChildren().add(labelID);
        vBoxArrival.getChildren().add(labelArrival);
        vBoxBurst.getChildren().add(labelBurst);

        nProcesses = Integer.parseInt(Processes);
        TextField Ids[] = new TextField[nProcesses];
        TextField arrivalTimes[] = new TextField[nProcesses];
        TextField burstTimes[] = new TextField[nProcesses];
        TextField priorities[] = new TextField[nProcesses];
        TextField quatumTime = new TextField();

        for(int i=0; i < nProcesses ; i++){
            Label label = new Label("Process" + Integer.toString(i+1));
            label.setFont(Font.font("Calibri",FontWeight.SEMI_BOLD,14));
            Ids[i] = new TextField();
            Ids[i].setPrefWidth(45);
            arrivalTimes[i] = new TextField();
            arrivalTimes[i].setPrefWidth(45);
            burstTimes[i] = new TextField();
            burstTimes[i].setPrefWidth(45);

            vBoxProcess.getChildren().add(label);
            vBoxID.getChildren().add(Ids[i]);
            vBoxArrival.getChildren().add(arrivalTimes[i]);
            vBoxBurst.getChildren().add(burstTimes[i]);
        }
        hBoxP = new HBox(vBoxProcess, vBoxID, vBoxArrival, vBoxBurst);

        if (isPriority) {
            Label labelPriority = new Label("Priority");
            labelPriority.setFont(Font.font("Calibri",FontWeight.BOLD,14));
            VBox vBoxPriority = new VBox(labelPriority);
            vBoxPriority.setPrefWidth(70);
            for(int i = 0; i < nProcesses; i++){
                priorities[i] = new TextField();
                priorities[i].setPrefWidth(45);
                vBoxPriority.getChildren().add(priorities[i]);
            }
            vBoxPriority.setSpacing(15);
            hBoxP.getChildren().add(vBoxPriority);
        }
        else if (isQuantum){
            Label labelRR = new Label("Quantum Time");
            labelRR.setFont(Font.font("Calibri",FontWeight.BOLD,14));
            quatumTime.setPrefWidth(70);
            hBoxRR = new HBox(labelRR,quatumTime);
            hBoxRR.setSpacing(10);
        }

        vBoxID.setSpacing(15);
        vBoxArrival.setSpacing(15);
        vBoxBurst.setSpacing(15);
        vBoxProcess.setSpacing(22);
        hBoxP.setSpacing(15);
        VBox VPane = new VBox(flowTitle,hBoxP);
        if(hBoxRR != null){
            VPane.getChildren().add(hBoxRR);
        }
        ButtonRun = new Button("Run");
        ButtonRun.setPrefWidth(75);
        VPane.getChildren().add(ButtonRun);
        VPane.setSpacing(15);
        VPane.setStyle("-fx-padding: 16;");
        ScrollPane sP = new ScrollPane(VPane);
        Scene scene = new Scene(sP, 550, 400);
        stage.setTitle("Scheduler Project");
        stage.setScene(scene);
        stage.show();


        ButtonRun.setOnAction(e -> {
            ps = new ArrayList<Process>();
            for(int i=0; i < nProcesses ; i++){
                if(Ids[i]==null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Enter process "+(i+1)+" ID.");
                    alert.showAndWait();
                    break;
                } else if (arrivalTimes[i]==null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Enter process "+(i+1)+" Arrival Time.");
                    alert.showAndWait();
                    break;
                } else if (burstTimes[i]==null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Enter process "+(i+1)+" Burst Time.");
                    alert.showAndWait();
                    break;
                } else if (isPriority && (priorities[i] == null)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Enter process " + (i+1) + " Priority.");
                    alert.showAndWait();
                    break;
                } else if (isQuantum && (quatumTime == null)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Enter Quantum Time.");
                    alert.showAndWait();
                    break;
                } else{
                    int pid, ar, bt, priority=-1;
                    if (!(Ids[i].getText().matches("-?\\d+"))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("Enter a Numerical value for process " + (i+1) + " ID.");
                        alert.showAndWait();
                        break;
                    } else{
                        pid = Integer.parseInt(Ids[i].getText());
                    }

                    if (!(arrivalTimes[i].getText().matches("-?\\d+"))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("Enter a Numerical value for process " + (i+1) + " Arrival Time.");
                        alert.showAndWait();
                        break;
                    } else{
                        ar = Integer.parseInt(arrivalTimes[i].getText());
                    }

                    if (!(burstTimes[i].getText().matches("-?\\d+"))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("Enter a Numerical value for process " + (i+1) + " Burst Time.");
                        alert.showAndWait();
                        break;
                    } else{
                        bt = Integer.parseInt(burstTimes[i].getText());
                    }

                    if(isPriority){
                        if (!(priorities[i].getText().matches("-?\\d+"))) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error");
                            alert.setHeaderText("Enter a Numerical value for process " + (i+1) + " priority.");
                            alert.showAndWait();
                            break;
                        } else
                            priority = Integer.parseInt(priorities[i].getText());
                    }

                    if(isQuantum){
                        if (!(quatumTime.getText().matches("-?\\d+"))) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error");
                            alert.setHeaderText("Enter a Numerical value for Quantum Time.");
                            alert.showAndWait();
                            break;
                        }else{
                            quantum = Integer.parseInt(quatumTime.getText());
                        }
                    }

                    if(isPriority){
                        ps.add(i,new Process(pid, ar, bt, priority));
                    }else{
                        ps.add(i,new Process(pid, ar, bt));
                    }

                    if(i == (nProcesses-1)){
                        System.out.println(ps.size());
                        scene3(stage);
                    }

                }
            }
        });
    }

    public void scene3(Stage stage){
        Algorithm algo = null;

        switch(scheduler){
            case "FCFS":
                algo = new FCFS(nProcesses,ps);
                break;
            case "Round Robin":
                algo = new RoundRobin(ps.size(), ps, quantum);
                break;
            case "SJF Preemptive":
                algo = new Preemptive(ps.size(), ps);
                break;
            case "SJF Non Preemptive":
                algo = new Non_Preemptive(ps.size(), ps);
                break;
            case "Priority Preemptive":
                algo = new Preemptive(ps.size(), ps);
                break;
            case "Priority Non Preemptive":
                algo = new Non_Preemptive(ps.size(), ps);
                break;
        }
        if(algo != null){
            bs = algo.schedule(scheduler);
        }

        Boolean done = false;
        int time = 1000;


        final NumberAxis xAxis = new NumberAxis(0, 30, 1);
        final CategoryAxis yAxis = new CategoryAxis();
        final StackedBarChart<Number, String> bc =
                new StackedBarChart<>(xAxis, yAxis);

        bc.setTitle("Gantt Chart");
        bc.setLegendVisible(false);
        xAxis.setLabel("Timeline");
        xAxis.setTickLabelRotation(0);
        yAxis.setLabel("Process");
        yAxis.setTickLabelRotation(0);
        bc.setCategoryGap(150);

        String[] color = {"RED", "BLUE", "GREEN", "CYAN", "MAGENTA", "BLACK"};

        String pLabel = new String("");
        String btLabel = new String("");
        for (int i = 0; i < ps.size(); i++) {
            Process p = ps.get(i);
            p.color = color[i];
            pLabel.concat("    P" + p.getPid());
            btLabel.concat("     " + p.getBt());
        }
        Label processesLabel = new Label(pLabel);
        Label remainingBurstTimeLable = new Label(btLabel);

        //ChartLegend chartLegend = new ChartLegend();
        //chartLegend.setAlignment(Pos.CENTER);


        VBox vBox = new VBox();
        vBox.getChildren().addAll(bc, processesLabel, remainingBurstTimeLable);
        // chartLegend,
        StackPane root = new StackPane();
        root.getChildren().add(vBox);


        Scene scene = new Scene(root, 1300, 700);
        stage.setScene(scene);
        stage.setTitle("Gantt Chart");
        stage.show();

        ArrayList<XYChart.Series> series = new ArrayList<>();

        for(int i=0; i<bs.size(); i++){
            Burst b = bs.get(i);

            for(int j=0; j<b.getQt(); j++){
                XYChart.Series s = new XYChart.Series();
                s.setName("P("+ b.getP().getPid()+ ")");
                s.getData().add(new XYChart.Data(0,""));
                series.add(s);
                bc.getData().add(s);
            }
        }

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000), (ActionEvent actionEvent) -> {

                    if(k == series.size()-1){
                        timeline.stop();
                    }

                    XYChart.Series my_s = series.get(k);
                    String name = "P("+bs.get(r).getP().getPid()+")";
                    if( name.compareTo(my_s.getName()) != 0 )
                        r++;
                    k++;
                    Burst b = bs.get(r);

                    data = new XYChart.Data<>(1, "");
                    data.nodeProperty().addListener(new ChangeListener<Node>() {
                        @Override
                        public void changed(ObservableValue<? extends Node> ov, Node oldNode, Node newNode) {
                            newNode.setStyle("-fx-bar-fill: " + b.getP().color + ";");
                        }
                    });

                    my_s.getData().set(0, data);
                    b.getP().decrementRt(1);


                    String s = "     ";

                    for (int i = 0; i < nProcesses; i++) {

                        s += ps.get(i).getRt();
                        s += "      ";
                        remainingBurstTimeLable.setText(s);


                    }
                }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        bc.setAnimated(false);


    }

    class ChartLegend extends GridPane {
        ChartLegend() {
            setHgap(10);
            setVgap(10);
            for (int i = 0; i < nProcesses; i++) {
                addColumn(i, createChartLegend(ps.get(i).color), new Label("P" + ps.get(i).getPid()));
            }
        }

        private Node createChartLegend(String fillStyle) {
            Shape symbol = new Rectangle(15, 15, 15, 15);
            symbol.setStyle("-fx-fill: " + fillStyle);
            //symbol.setStroke(Color.BLACK);
            symbol.setStrokeWidth(2);

            return symbol;
        }

        public static void main(String[] args) {
            launch(args);
        }


    }
}
