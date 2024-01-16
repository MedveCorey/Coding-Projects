package StarbucksFXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class StarbucksFXMLController implements Initializable {

    @FXML
    private TextArea purchaseDescription;
    @FXML
    private CheckBox sugar;
    @FXML
    private CheckBox cream;
    @FXML
    private CheckBox spice;
    @FXML
    private CheckBox honey;
    @FXML
    private RadioButton blondeRadio;
    @FXML
    private RadioButton decafRadio;
    @FXML
    private RadioButton caffeMistoRadio;
    @FXML
    private RadioButton hotChocolateRadio;

    @FXML
    private RadioButton vetRadioButton;
    @FXML
    private ToggleGroup VeteranRadioGroup;
    @FXML
    private Button promoApplyButton;
    @FXML
    private TextField promoTextField;
    @FXML
    private Button resetButton;

    //list of prices
    private double price = 0;
    private String couponWord = "javafx";
    private double blondeRoastPrice = 3.50;
    private double caffeMistoPrice = 3.75;
    private double decafPikePrice = 3.00;
    private double hotChocolatePrice = 4.00;
    private double creamPrice = 1.00;
    private double sugarPrice = .50;
    private double spicePrice = 1.50;
    private double honeyPrice = .75;
    private double veteranDiscount = 10.0;
    private double couponDiscount = .20;
    @FXML
    private ToggleGroup CoffeeRadioGroup1;
    @FXML
    private RadioButton nonVetRadioButton;
    StringBuilder sb;
    

    @FXML
    private void updatePurchaseDescription(ActionEvent event) {
        System.out.println("Needs update.");
        sb = new StringBuilder();

        sb.append("Current Order\n\n");

        //checking the radio buttons (Drink Type)
        if (decafRadio.isSelected()) {
            sb.append("Drink Type: Decaf Pike\n");
            price = decafPikePrice;
        } else if (caffeMistoRadio.isSelected()) {
            sb.append("Drink Type: Caffe Misto\n");
            price = caffeMistoPrice;
        } else if (hotChocolateRadio.isSelected()) {
            sb.append("Drink Type: Hot Chocolate\n");
            price = hotChocolatePrice;
        } else if (blondeRadio.isSelected()) {
            sb.append("Drink Type: Blonde Roast\n");
            price = blondeRoastPrice;
        }

        //optionals
        sb.append("Extras:\n");
        if (sugar.isSelected()) {
            sb.append(" Sugar\n");
            price += sugarPrice;
        }
        if (spice.isSelected()) {
            sb.append(" Spice\n");
            price += spicePrice;
        }
        if (honey.isSelected()) {
            sb.append(" Honey\n");
            price += honeyPrice;
        }
        if (cream.isSelected()) {
            sb.append(" Cream\n");
            price += creamPrice;
        }
        //veteran Discount
        if (vetRadioButton.isSelected()) {
            price -= price / veteranDiscount;
            sb.append("\nVeteran Discount Applied ").append(price / veteranDiscount);
        }
         //promo code discount
        if(promoTextField.getText().equals(couponWord)){
            System.out.println("coupon Applied");
            price -=price*couponDiscount;
            sb.append("\nCoupon Applied: ").append(price*couponDiscount);
            purchaseDescription.setText(sb.toString());
        }
        sb.append("\nTotal Price: ").append(price);
        purchaseDescription.setText(sb.toString());

       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updatePurchaseDescription(null);
    }


    @FXML
    private void onResetButtonClicked(ActionEvent event) {
        blondeRadio.selectedProperty().setValue(Boolean.TRUE);
        sugar.selectedProperty().setValue(Boolean.FALSE);
        spice.selectedProperty().setValue(Boolean.FALSE);
        honey.selectedProperty().setValue(Boolean.FALSE);
        cream.selectedProperty().setValue(Boolean.FALSE);
        nonVetRadioButton.selectedProperty().setValue(Boolean.TRUE);
        purchaseDescription.clear();
        promoTextField.clear();
    }
}
