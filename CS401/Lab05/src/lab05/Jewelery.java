package lab05;

public abstract class Jewelery extends merch_info {

    protected String type;
    protected String cert;
    protected String cert_issuer;

    public Jewelery(String type, String brand, boolean in_stock, double price, String cert, String cert_issuer) {
        super(brand, in_stock, price);
        this.type = type;
        this.cert_issuer = cert_issuer;
        this.cert = cert;
    }

}
