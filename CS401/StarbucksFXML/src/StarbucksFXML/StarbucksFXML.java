package StarbucksFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;;
import javafx.scene.*; 
import javafx.stage.Stage;

public class StarbucksFXML extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StarbucksFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
