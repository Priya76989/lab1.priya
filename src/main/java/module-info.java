module org.example.priya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.priya to javafx.fxml;
    exports org.example.priya;


}