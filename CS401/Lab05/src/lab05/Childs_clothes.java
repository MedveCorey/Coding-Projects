package lab05;

public class Childs_clothes extends Clothes {

    public String cert;
    public String cert_issuer;

    public Childs_clothes(String cert, String cert_issuer, double price, String brand, boolean in_stock, String fabric, String size) {
        super(fabric, size, brand, in_stock, price);
        this.cert = cert;
        this.cert_issuer = cert_issuer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Childs Clothing: ").append("\nSize: ").append(size).append("\nPrice: ").append(price).append("\nIn Stock: ").append(in_stock)
                .append("\nBrand: ").append(brand).append("\nFabric: ").append(fabric).append("\nCertificate: ").append(cert).append("\nCertificate Issuer: ")
                .append(cert_issuer);
        return sb.toString();

    }

}
