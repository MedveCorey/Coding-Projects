package lab05;

public abstract class merch_info {

    protected String brand;
    protected boolean in_stock;
    protected double price;

    public merch_info(String brand, boolean in_stock, double price) {
        this.brand = brand;
        this.in_stock = in_stock;
        this.price = price;
    }

}
