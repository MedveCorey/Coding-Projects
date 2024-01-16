package lab05;

public class Watches extends Jewelery {

    private String type;
    private String band_material;

    public Watches(String type, String band_material, String brand, double price, boolean in_stock, String authentic_cert, String cert_issuer) {
        super("Watch", brand, in_stock, price, authentic_cert, cert_issuer);
        this.type = type;
        this.band_material = band_material;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Watches: ").append("\nType: ").append(type).append("\nPrice: ").append(price).append("\nIn Stock: ").append(in_stock)
                .append("\n Brand: ").append(brand).append("\nBand Material: ").append(band_material).append("\nCertificate: ").append(cert)
                .append("\nCertificate Issuer: ").append(cert_issuer);
        return sb.toString();
    }
    
}
