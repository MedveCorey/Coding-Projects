package lab05;

public abstract class Clothes extends merch_info {

    protected String fabric;
    protected String size;

    public Clothes(String fabric, String size, String brand, boolean in_stock, double price) {
        super(brand, in_stock, price);
        this.size = size;
        this.fabric = fabric;
    }

}
