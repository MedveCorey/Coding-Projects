package CarDealershipFXML;

import java.util.List;
import javafx.scene.image.Image;

public class Car {

    //class fields
    protected Image carImage;
    protected List<String> availableColors;
    protected List<String> optionals;
    protected String carType;
    protected double price;
    protected String description;

    public Car(Image carImage, List<String> availableColors, List<String> optionals, String carType, double price, String description) {
        this.carImage = carImage;
        this.availableColors = availableColors;
        this.optionals = optionals;
        this.carType = carType;
        this.price = price;
        this.description = description;
    }

}
