package paintbrushapp;

public class Rectangle extends Shape {

    //constructor
    //default constructor
    public Rectangle() {
        super(new double[]{10, 5});
        shapeType = "Rectangle";
    }

    public Rectangle(Rectangle originalRect) {
        super(originalRect.shapeDimensions);
        shapeType = "Rectangle";
    }

    //non default constructor
    public Rectangle(double height, double length) {
        super(new double[]{height, length});
        shapeType = "Rectangle";
    }

    //class method
    public double computePerimeter() {
        return 2 * (shapeDimensions[0] + shapeDimensions[1]);
    }

    public double computeArea() {
        return (shapeDimensions[0] * shapeDimensions[1]);
    }

    public Rectangle copy() {
        Rectangle copyRectangle = new Rectangle(shapeDimensions[0], shapeDimensions[1]);
        return copyRectangle;
    }

    public boolean equals(Rectangle otherRectangle) {
        if (this.shapeDimensions[0] == otherRectangle.shapeDimensions[0] && this.shapeDimensions[1] == otherRectangle.shapeDimensions[1]) {
            return true;
        } else {
            return false;
        }
    }

}
