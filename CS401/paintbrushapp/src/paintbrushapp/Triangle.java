package paintbrushapp;

public class Triangle extends Shape {

    public Triangle() {
        super(new double[]{1,2});
        shapeType = "Triangle";
    }

    public Triangle(double height, double length) {
        super(new double[]{height, length});
        shapeType = "Triangle";
    }

    public double computeArea() {
        return .5 * shapeDimensions[0] * shapeDimensions[1];
    }

    public double computePerimeter() {
        return shapeDimensions[0] + shapeDimensions[1] + Math.sqrt(Math.pow(shapeDimensions[0], 2) + Math.pow(shapeDimensions[1], 2));
    }

    public boolean equals(Triangle otherTriangle) {
        if (this.shapeDimensions[0] == otherTriangle.shapeDimensions[0] && this.shapeDimensions[1] == otherTriangle.shapeDimensions[1]) {
            return true;
        } else {
            return false;
        }
    }

}
