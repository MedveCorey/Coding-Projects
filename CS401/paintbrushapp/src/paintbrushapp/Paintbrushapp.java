package paintbrushapp;

import java.util.*;

public class Paintbrushapp {

    public static void main(String[] args) {
        new Paintbrushapp();
    }

    public Paintbrushapp() {
        Rectangle rect01 = new Rectangle();
        Rectangle rect02 = new Rectangle(3, 4);
        Rectangle rect03 = new Rectangle(3, 4);

        Triangle tri01 = new Triangle();
        Square square01 = new Square(5);

        Shape[] myShapes = {rect01, rect02, rect03, tri01, square01};
        List<Shape> myOtherShapes = new ArrayList();
        myOtherShapes.add(tri01);

        doubleIt(rect01.copy());
        doubleIt(tri01);

        int b = 10;
        tripleIt(b);

        if (rect02.equals(rect03)) {
            System.out.println("They have the same dimensions");

        } else {
            System.out.println("They are different rectangles.");
        }
        rect02.shapeDimensions[0] = -200;

        System.out.print(rect01.shapeDimensions[0]);
        System.out.print(rect01.shapeDimensions[1]);
        System.out.println(rect01.shapeType);

        int age = 23;
        int[] ages = {12, 32, 22};
        Rectangle[] rectangleList = {rect01, rect02, rect03};

        for (Rectangle currentRectangle : rectangleList) {
            System.out.println(currentRectangle);

        }
    }

    private void doubleIt(Shape shape) {
        for (double currentDimension : shape.getDimensions()) {
            double newDimension = 2 * currentDimension;
        }
    }

    private void tripleIt(int b) {
        b = b * 3;

    }
}
