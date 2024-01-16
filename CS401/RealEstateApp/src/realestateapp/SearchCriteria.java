package realestateapp;

import javax.swing.JOptionPane;

public class SearchCriteria {

    JOptionPane input = new JOptionPane(System.in);

    double minPrice;
    double maxPrice;
    int minBedrooms;
    int maxBedrooms;

    public SearchCriteria(String userSelection) {
        String[] parameters = userSelection.split(" ");
        if (parameters[0].equals("-p")) {
            minPrice = Double.parseDouble(parameters[1]);
            maxPrice = Double.parseDouble(parameters[2]);
        } else if (parameters[0].equals("-b")) {
            minBedrooms = Integer.parseInt(parameters[1]);
            maxBedrooms = Integer.parseInt(parameters[2]);

        } else if (parameters[0].equals("-p") && parameters[3].equals("-b")) {
            minPrice = Double.parseDouble(parameters[1]);
            maxPrice = Double.parseDouble(parameters[2]);
            minBedrooms = Integer.parseInt(parameters[4]);
            maxBedrooms = Integer.parseInt(parameters[5]);
        }
    }

    public boolean doesHouseMeetCriteria(House house) {
        if (house.price >= minPrice && house.price <= maxPrice) {
            return true;
        }
        if (house.beds >= minBedrooms && house.beds <= maxBedrooms) {
            return true;
        } else {
            return false;
        }
    }
}
