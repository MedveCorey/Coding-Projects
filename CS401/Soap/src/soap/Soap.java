package soap;

import java.io.Serializable;


public class Soap implements Serializable {
    private String color;
    private String brand;
    private String scent;
    private double mass;

    public Soap(String color, String brand, String scent, double mass) {
        this.color = color;
        this.brand = brand;
        this.scent = scent;
        this.mass = mass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getScent() {
        return scent;
    }

    public void setScent(String scent) {
        this.scent = scent;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    
}
