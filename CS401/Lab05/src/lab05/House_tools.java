
package lab05;

public class House_tools extends Tools {
    private boolean water_resistant;
    
    public House_tools(String material, double price, boolean in_stock, String brand, boolean water_resistant) {
        super(material, "House", price, in_stock, brand);
        this.water_resistant = water_resistant;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("House Tools: ").append("\nType: ").append(type).append("\nPrice: ").append(price).append("\nIn Stock: ").append(in_stock)
                .append("\nBrand: ").append(brand).append("\nWater resistant: ").append(water_resistant);
        return sb.toString();
    }
    
}
