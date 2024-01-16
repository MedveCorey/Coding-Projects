package CarDealershipFXML;

import java.util.List;
import javafx.scene.image.Image;

public class Car {

    //class fields
    public Image carImage;
    public List<String> availableColors;
    public List<String> optionals;
    public String carType;
    public double price;
    public String description;

    public Car(Image carImage, List<String> availableColors, List<String> optionals, String carType, double price, String description) {
        this.carImage = carImage;
        this.availableColors = availableColors;
        this.optionals = optionals;
        this.carType = carType;
        this.price = price;
        this.description = description;
    }

}
