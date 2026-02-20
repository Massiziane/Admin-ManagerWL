module org.example.workoutlog {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;

    requires java.sql;       
    requires org.postgresql.jdbc;

    opens org.example.workoutlog.controllers to javafx.fxml;
    exports org.example.workoutlog;
}
