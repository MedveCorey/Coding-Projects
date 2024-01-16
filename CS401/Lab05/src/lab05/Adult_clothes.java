package lab05;

public class Adult_clothes extends Clothes {

    private String color;

    public Adult_clothes(String color, String size, String fabric, double price, boolean in_stock, String brand) {
        super(fabric, size, brand, in_stock, price);
        this.color = color;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adults Clothing: ").append("\nsize: ").append(size).append("\nprice: ").append(price).append("\nIn Stock: ").append(in_stock)
                .append("\nbrand: ").append(brand).append("\nfabric: ").append(fabric).append("\nColor: ").append(color);
        return sb.toString();

    }

}
