module com.example.os_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.os_project to javafx.fxml;
    exports com.example.os_project;
}