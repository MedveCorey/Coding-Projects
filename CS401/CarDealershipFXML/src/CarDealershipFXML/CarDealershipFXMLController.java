package CarDealershipFXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

public class CarDealershipFXMLController implements Initializable {

    List<Car> listOfCars = new ArrayList();
    Car selectedCar;

    @FXML
    private TextArea descriptionId;
    @FXML
    private ChoiceBox<String> carTypeChoiceID;
    @FXML
    private ChoiceBox<String> colorChoiceBoxID;

    
    public void initialize(URL url, ResourceBundle rb) {
        createListOfCars();
        createListenersForChoiceBoxes();
        selectedCar = listOfCars.get(0);
        populateCarTypeChoiceBox();
        updateGUI();
    }

    private void updateGUI() {
        updateDescriptionFunction();
        updateAvailableColors();
        //updateOptionals();

    }

    private void createListOfCars() {
        Image carImage = null;
        List<String> availablecolor = new ArrayList();
        availablecolor.add("yellow");
        availablecolor.add("red");
        availablecolor.add("blue");
        List<String> optionals = new ArrayList();
        optionals.add("Air Conditioning");
        optionals.add("Power Locks");
        optionals.add("Power Doors");
        String carType = "toyota";
        double price = 20000;
        String description = "toyota is a very popular car";

        Car toyotaCar = new Car(carImage, availablecolor, optionals, carType, price, description);
        updateGUI();
        listOfCars.add(toyotaCar);

        //create lambo
        Image carImage2 = null;

        List<String> availablecolor2 = new ArrayList();
        availablecolor.add("gold");
        availablecolor.add("silver");
        availablecolor.add("blue");

        List<String> optionals2 = new ArrayList();
        optionals.add("Air Conditioning");
        String carType2 = "lamborghini";
        double price2 = 200000;
        String description2 = "lamborgini is a very expensive car";

        Car lamborginiCar = new Car(carImage2, availablecolor2, optionals2, carType2, price2, description2);
        updateGUI();
        listOfCars.add(lamborginiCar);
    }

    private void populateCarTypeChoiceBox() {
        List<String> CarTypes = new ArrayList();
        CarTypes.add("Toyota");
        CarTypes.add("Lamborgini");
        CarTypes.add("Ferrari");

        for (String currentCar : CarTypes) {
            carTypeChoiceID.getItems().add(currentCar);
        }

        carTypeChoiceID.getSelectionModel().selectFirst();
    }

    private void updateDescriptionFunction() {
        descriptionId.setText(selectedCar.description);
    }

    private void updateAvailableColors() {
        colorChoiceBoxID.getItems().clear();
        for (String currentColor : selectedCar.availableColors) {
            colorChoiceBoxID.getItems().add(currentColor);
        }

        colorChoiceBoxID.getSelectionModel().selectFirst();
    }

    private void createListenersForChoiceBoxes() {
        carTypeChoiceID.valueProperty().addListener(new MyCarTypeChoiceBoxListener());

    }

    class MyCarTypeChoiceBoxListener implements ChangeListener {

        @Override
        public void changed(ObservableValue ov, Object t, Object t1) {
            if (t1.equals("toyota")) {
                selectedCar = listOfCars.get(0);
            } else if (t1.equals("lamborgini")) {
                selectedCar = listOfCars.get(1);
            } else {
                selectedCar = listOfCars.get(2);
            }
            updateGUI();
        }

    }
}
