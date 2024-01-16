package realestateapp;

public class House {

    public String street;
    public String city;
    public String zip;
    public String state;
    public int beds;
    public int baths;
    public int sq_ft;
    public String type;
    public String sale_date;
    public double price;

    public House() {

    }

    public House(String HouseListing) {
        String[] house_details = HouseListing.split(",");
        street = house_details[0];
        city = house_details[1];
        zip = house_details[2];
        state = house_details[3];
        beds = Integer.parseInt(house_details[4]);
        baths = Integer.parseInt(house_details[5]);
        sq_ft = Integer.parseInt(house_details[6]);
        type = house_details[7];
        sale_date = house_details[8];
        price = Double.parseDouble(house_details[9]);
    }

    public String displayHouseInfo() {
        String street = "Street: " + this.street;
        String city = "City: " + this.city;
        String zip = "Zip Code: " + this.zip;
        String state = "State: " + this.state;
        String beds = "Beds: " + this.beds;
        String baths = "Baths: " + this.baths;
        String sq_ft = "Square Feet: " + this.sq_ft;
        String type = "Construction Type: " + this.type;
        String sale_date = "Sale Date: " + this.sale_date;
        String price = "Price: " + this.price;
        String houseInfo = street + "\n"
                + city + "\n" + zip + "\n" + state + "\n" + beds + "\n"
                + baths + "\n" + sq_ft + "\n" + type + "\n" + sale_date + "\n" + price;
        return houseInfo;

    }
}
