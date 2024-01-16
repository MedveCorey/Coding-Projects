package Main;

class Location {

    private String street;

    private String city;

    private int zip;

    private String state;

    Location(String street, String city, int zip, String state) {
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.street = street;
    }

    //getters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }

    //setters
    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String toString() {
        String houseInfo = "Street: " + street + "\nCity: " + city + "\nZip Code: " + zip + "\nState: " + state;
        return houseInfo;
    }

}
