module com.example.operating_system_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.operating_system_project to javafx.fxml;
    exports com.example.operating_system_project;
}