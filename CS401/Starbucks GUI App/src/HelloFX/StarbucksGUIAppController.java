package HelloFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class StarbucksGUIAppController {
    private double BlondeRoastPrice = 3.50;
    private double CafeMistoPrice = 3.75;
    private double DecafPikePrice = 3.00;
    private double HotChocolatePrice = 4.00;
    private double CreamPrice = 1.00;
    private double SugarPrice = .50;
    private double SpicePrice = 1.50;
    private double HoneyPrice = .75;
    private double VeteranDiscount = 10.0;
    private String CouponCode = "javafx";
    private double CouponDiscount = 20.0;
    
    @FXML
    private ToggleGroup BrewedCoffeeRadioButton;
    @FXML
    private RadioButton CafeMistoButton;
    @FXML
    private RadioButton DecafPikeButton;
    @FXML
    private RadioButton HotChocolateButton;
    @FXML
    private TextArea OrderSummaryArea;
    @FXML
    private CheckBox CreamCheckBox;
    @FXML
    private CheckBox SugarCheckBox;
    @FXML
    private CheckBox SpiceCheckBox;
    @FXML
    private CheckBox HoneyCheckBox;
    @FXML
    private RadioButton NonVeteranRadioID;
    @FXML
    private ToggleGroup VeteranRadioButtonGroup;
    @FXML
    private RadioButton VeteranRadioID;
    @FXML
    private TextField PromotionCodeField;
    @FXML
    private Button ResetOrderID;
    @FXML
    private Button UpdateButtonID;
    @FXML
    private RadioButton BlondeRoastButton;

    @FXML
    private void SelectCoffee(ActionEvent event) {
        if(BlondeRoastButton.isSelected()){
            OrderSummaryArea.setText("Order Summary:\n\n Coffee Type: Blonde Roast\n Coffee Price: "+ BlondeRoastPrice);
        }
    }

    @FXML
    private void AddExtra(ActionEvent event) {
    }

    @FXML
    private void OnResetButtonPress(ActionEvent event) {
        OrderSummaryArea.setText("Order Summary:\n\n Coffee Type: \n Extras:\n Coffee Price: \n Order Summary ");
    }

    @FXML
    private void ApplyPronoDIscount(ActionEvent event) {
    }

}
