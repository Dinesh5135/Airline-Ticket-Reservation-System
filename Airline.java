package Fx;
import java.sql.*;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
public class Airline extends Application {
      public static void main(String[] args) {
        launch(args);
    }
    Connection conn;
    Statement stmt;
    TextField departureField;
    TextField destinationField;
    DatePicker departureDate;
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlines", "root", "5135");
            stmt = conn.createStatement();
        } catch (Exception e) {
            Alert a1 = new Alert(Alert.AlertType.ERROR,""+e+"");
            a1.show();
        }
        
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        Label ageLabel = new Label("Age:");
        TextField ageTextField = new TextField();
        Label fromLabel = new Label("From:");
        TextField fromTextField = new TextField();
        Label toLabel = new Label("To:");
        TextField toTextField = new TextField();
        Label dateLabel = new Label("Date:");
        //TextField dateTextField = new TextField();
        Button reserveButton = new Button("Reserve");
          departureDate = new DatePicker();

        GridPane root = new GridPane();
        root.add(nameLabel, 0, 0);
        root.add(nameTextField, 1, 0);
        root.add(ageLabel, 0, 1);
        root.add(ageTextField, 1, 1);
        root.add(fromLabel, 0, 2);
        root.add(fromTextField, 1, 2);
        root.add(toLabel, 0, 3);
        root.add(toTextField, 1, 3);
        root.add(dateLabel, 0, 4);
        root.add(reserveButton, 1, 5);
        root.add(departureDate,1,4);
        Scene scene = new Scene(root, 300, 250);
        
        
        reserveButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                try{
                String name = nameTextField.getText();
                String age = ageTextField.getText();
                String from_place = fromTextField.getText();
                String to_place = toTextField.getText();
                LocalDate date = departureDate.getValue();
                System.out.println(date);
                Integer intage = Integer.parseInt(age);
                String sql = "insert into passenger values('"+name+"',"+age+",'"+from_place+"','"+to_place+"','"+date+"')";
                stmt.execute(sql);
                Alert a1 = new Alert(Alert.AlertType.INFORMATION,"Reservation Successful");
            a1.show();
                }catch(Exception e){
                    Alert a1 = new Alert(Alert.AlertType.ERROR,""+e+"");
            a1.show();
                }
            }
        });
        primaryStage.setTitle("Airline Ticket Reservation System");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
        
 
}