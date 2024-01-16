package lab05;

public class Rings extends Jewelery {

    private double size;
    private boolean has_diamond;

    public Rings(double size, boolean has_diamond, String brand, double price, boolean in_stock, String cert, String cert_issuer) {
        super("Ring", brand, in_stock, price, cert, cert_issuer);
        this.size = size;
        this.has_diamond = has_diamond;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ring: ").append("\nSize: ").append(size).append("\nPrice: ").append(price).append("\nIn Stock: ").append(in_stock)
                .append("\nBrand: ").append(brand).append("\nHas a Diamond: ").append(has_diamond).append("\nCertificate: ").append(cert)
                .append("\nCertificate Issuer: ").append(cert_issuer);
        return sb.toString();

    }

}
