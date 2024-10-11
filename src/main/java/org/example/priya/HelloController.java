
package org.example.priya;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TableView<tution> tableView;
    @FXML
    private TableColumn<tution, Integer> id;
    @FXML
    private TableColumn<tution, String> student;
    @FXML
    private TableColumn<tution, String> teacher;
    @FXML
    private TableColumn<tution, Integer> className;

    private ObservableList<tution> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        student.setCellValueFactory(new PropertyValueFactory<>("student"));
        teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        className.setCellValueFactory(new PropertyValueFactory<>("className"));

        // Set items to the table view
        tableView.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("CLICKED");
        populateTable();
    }

    public void populateTable() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_lab1_priya";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM tbl_tution";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            list.clear();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No data found in the database.");
            } else {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String studentName = resultSet.getString("student");
                    String teacherName = resultSet.getString("teacher");
                    int classNumber = resultSet.getInt("class");


                    list.add(new tution(id, studentName, teacherName, classNumber));
                }
                tableView.setItems(list); // Refresh the table view
                System.out.println("Data loaded successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
        }
    }
}

