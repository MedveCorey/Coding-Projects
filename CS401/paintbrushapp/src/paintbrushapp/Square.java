package paintbrushapp;

public class Square extends Rectangle {

    public Square() {
        shapeType = "Square";

    }
    public Square(double sideLength){
        super(sideLength,sideLength);
    }
}
