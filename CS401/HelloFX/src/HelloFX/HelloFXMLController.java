package HelloFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloFXMLController {

    private TextField emailTextBox;

    private TextField nameTextBox;

    private Button submitButton;
    

    private void handleButton(ActionEvent event) {
        System.out.println("The Submit button has been clicked");
        String name = nameTextBox.getText();
        String email = emailTextBox.getText();
        System.out.println("name: "+ name);
        System.out.println("email: "+ email);
        submitButton.setDisable(true);

    }

}
