package lab05;

public class Mechanic_Tools extends Tools {

    private double cert_num;
    private String cert_issuer;

    public Mechanic_Tools(String material, double price, boolean in_stock, String brand, double cert_num, String cert_issuer) {
        super(material, "Mechanic", price, in_stock, brand);
        this.cert_num = cert_num;
        this.cert_issuer = cert_issuer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMechanic Tool: ").append("\nType: ").append(type).append("\nPrice: ").append(price).append("\nIn Stock: ").append(in_stock)
                .append("\nBrand: ").append(brand).append("\nCertificate: ").append(cert_num).append("\nCertificate Issuer: ")
                .append(cert_issuer);
        return sb.toString();
    }
    
}