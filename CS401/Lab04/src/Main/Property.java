package Main;

public class Property {

    private House house; //  agregation
    private Location location;     //  agregation
    private String saleDate;
    private double price;

//constructors
    public Property(String rawInfo) {

        String[] propertyInfo = rawInfo.trim().split(",");

        String street = propertyInfo[0].trim();

        String city = propertyInfo[1].trim();

        int zip = Integer.parseInt(propertyInfo[2].trim());

        String state = propertyInfo[3].trim();

        int numberOfBeds = Integer.parseInt(propertyInfo[4].trim());

        int numberOfBaths = Integer.parseInt(propertyInfo[5].trim());

        double footage = Double.parseDouble(propertyInfo[6].trim());

        String type = propertyInfo[7].trim();

        saleDate = propertyInfo[8].trim();

        price = Double.parseDouble(propertyInfo[9].trim());

        location = new Location(street, city, zip, state);

        house = new House(numberOfBeds, numberOfBaths, footage, type);

    }

    public Property(Property property) {

    }

    //getters
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public double getPrice() {
        return price;
    }

    //to string 
    public String toString() {
        String info = "House info:\n" + location + house + "\nPrice: " + price + "\nSale Date: " + saleDate;
        return info;
    }

    //equals method
    public boolean equals(Property property) {
        if (property == property) {
            return true;
        } else {
            return false;
        }
    }
}
