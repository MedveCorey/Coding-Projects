package lab05;

public abstract class Tools extends merch_info {

    protected String material;
    protected String type;

    public Tools(String material, String tool_type, double price, boolean in_stock, String brand) {
        super(brand, in_stock, price);
        this.material = material;
        this.type = tool_type;
    }

}
