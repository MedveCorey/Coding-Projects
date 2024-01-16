package CarDealershipFXML;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CarDealershipController implements Initializable, Serializable {

    List<Car> listOfCars = new ArrayList();
    Car selectedCar = null;
    
    double totalPrice;
    double insurance;
    double optionalsPrice = 0;
    
    @FXML
    private ChoiceBox<String> carTypeChoiceBoxId;

    @FXML
    private ImageView carImageId;
    @FXML
    private ImageView logoImageID;
    @FXML
    private CheckBox powerLocksId;
    @FXML
    private CheckBox powerWindowsId;
    @FXML
    private CheckBox airConditionerId;
    @FXML
    private ChoiceBox<String> colorChoiceBoxID;
    @FXML
    private TextArea descriptionId;
    @FXML
    private RadioButton threeYearInsuranceId;
    @FXML
    private ToggleGroup insuranceRadioGroup;
    @FXML
    private RadioButton oneYearInsuranceId;
    @FXML
    private RadioButton noInsuranceId;
    @FXML
    private Label pricelabelId;
    @FXML
    private Button exitButtonId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createListOfCars();
        createListenersForChoiceBoxes();
        selectedCar = listOfCars.get(0);
        totalPrice = selectedCar.price;
        populateCarTypeChoiceBox();
        placeCompanyLogo();
        updateGUI();
    }

    public void placeCompanyLogo() {
        Image carImage = new Image(getClass().getResourceAsStream(
                "/resources/images/toyota_logo.png"));
        logoImageID.setImage(carImage);
    }

    private void updateGUI() {
        updateDescription();
        updateAvailableColors();
        updateCarImage();
        updateOptionalCheckBoxes();
        updatePrice();
        
    }

    //
    public void updateOptionalCheckBoxes() {
        powerWindowsId.setDisable(true);
        powerLocksId.setDisable(true);
        airConditionerId.setDisable(true);

        for (String currentOptional : selectedCar.optionals) {
            if (currentOptional.equalsIgnoreCase("power windows")) {
                powerWindowsId.setDisable(false);
            }

            if (currentOptional.equalsIgnoreCase("power locks")) {
                powerLocksId.setDisable(false);
            }

            if (currentOptional.equalsIgnoreCase("air conditioner")) {
                airConditionerId.setDisable(false);
            }
        }
    }

    private void createListOfCars() {
        Image carImage = new Image(getClass().getResourceAsStream("/resources/images/toyota.gif"));
        List<String> availableColors = new ArrayList();
        availableColors.add("blue");
        availableColors.add("green");
        availableColors.add("red");
        List<String> optionals = new ArrayList();
        optionals.add("power windows");
        optionals.add("power locks");
        String carType = "Toyota";
        double price = 20000;
        String description = "Toyota is a very popular car... ";
        Car toyotaCar = new Car(carImage, availableColors, optionals,
                carType, price, description);
        listOfCars.add(toyotaCar);
        // creating lamborgini car
        Image carImage2 = new Image(getClass().getResourceAsStream("/resources/images/lamborghini_1.gif"));;
        List<String> availableColors2 = new ArrayList();
        availableColors2.add("Orange");
        availableColors2.add("blue");
        availableColors2.add("silver");
        List<String> optionals2 = new ArrayList();
        optionals2.add("air conditioner");
        String carType2 = "Lamborghini";
        double price2 = 200000;
        String description2 = "Lamborghini is a very expensive car... ";
        Car lamborghiniCar = new Car(carImage2, availableColors2, optionals2,
                carType2, price2, description2);
        listOfCars.add(lamborghiniCar);
        Image carImage3 = new Image(getClass().getResourceAsStream("/resources/images/ferrari.png"));;
        List<String> availableColors3 = new ArrayList();
        availableColors3.add("Brown");
        availableColors3.add("Pink");
        availableColors3.add("Gold");
        List<String> optionals3 = new ArrayList();
        optionals3.add("air conditioner");
        optionals3.add("power locks");
        String carType3 = "Ferrari";
        double price3 = 100000;
        String description3 = "Ferrari is a semi expensive car... ";
        Car FerrariCar = new Car(carImage3, availableColors3, optionals3,
                carType3, price3, description3);
        listOfCars.add(FerrariCar);
    }

    private void populateCarTypeChoiceBox() {
        List<String> listOfCarTypes = new ArrayList();
        listOfCarTypes.add("Toyota");
        listOfCarTypes.add("Lamborghini");
        listOfCarTypes.add("Ferrari");
        for (String currentCar : listOfCarTypes) {
            carTypeChoiceBoxId.getItems().add(currentCar);
        }
        carTypeChoiceBoxId.getSelectionModel().selectFirst();
    }

    private void updateDescription() {
        descriptionId.setText(selectedCar.description);
    }

    private void updateAvailableColors() {
        // availableColorsChoiceBoxId
        colorChoiceBoxID.getItems().clear();
        for (String currentColor : selectedCar.availableColors) {
            colorChoiceBoxID.getItems().add(currentColor);
        }
        colorChoiceBoxID.getSelectionModel().selectFirst();
    }

    private void createListenersForChoiceBoxes() {
        // MyCarTypeChoiceBoxListener myListener = new MyCarTypeChoiceBoxListener();
        // carTypeChoiceBoxId.valueProperty().addListener(myListener);
        carTypeChoiceBoxId.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                if (t1.equals("Toyota")) {
                    selectedCar = listOfCars.get(0);
                } else if (t1.equals("Lamborghini")) {
                    selectedCar = listOfCars.get(1);
                } else {
                    selectedCar = listOfCars.get(2);
                }
                updateGUI();
            }

        });
    }

    private void updateCarImage() {
        carImageId.setImage(selectedCar.carImage);
    }

    @FXML
    private void optionalsEvent(ActionEvent event) {
        
        //insurance prices
       if(threeYearInsuranceId.isSelected()){
           insurance =  3000;
       }else if(oneYearInsuranceId.isSelected()){
           insurance = 1000;
       }else{
           insurance = 0;
       }
       //optionals prices
       if(powerWindowsId.isSelected()){
           optionalsPrice += 500;
       }
       if(powerLocksId.isSelected()){
           optionalsPrice += 800;
       }
       if(airConditionerId.isSelected()){
           optionalsPrice += 300;
       }
       
       updateGUI();
    }
    private void updatePrice(){
      totalPrice = selectedCar.price + insurance + optionalsPrice;
      pricelabelId.setText(Double.toString(totalPrice));
    }

    @FXML
    private void closeProgram(ActionEvent event) {
        Stage stage = (Stage) exitButtonId.getScene().getWindow();
        stage.close();
    }

    
}
