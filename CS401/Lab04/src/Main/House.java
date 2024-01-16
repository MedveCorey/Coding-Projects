package Main;

public class House {

    // class fields
    private int numberOfBeds;

    private int numberOfBaths;

    private double footage;

    private String type;

    House(int numberOfBeds, int numberOfBaths, double footage, String type) {
        this.numberOfBeds = numberOfBeds;
        this.numberOfBaths = numberOfBaths;
        this.type = type;
        this.footage = footage;

    }

    //getters and setters;
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getNumberOfBaths() {
        return numberOfBaths;
    }

    public void setNumberOfBaths(int numberOfBaths) {
        this.numberOfBaths = numberOfBaths;
    }

    public double getFootage() {
        return footage;
    }

    public void setFootage(double footage) {
        this.footage = footage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //methods
    public String toString() {
        String info = "\nNumber of beds: " + numberOfBeds + "\nNumber of Baths: " + numberOfBaths + "\n Square Footage: " + footage + "\nType: " + type;
        return info;
    }

}
