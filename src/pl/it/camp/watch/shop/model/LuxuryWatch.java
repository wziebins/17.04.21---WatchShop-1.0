package pl.it.camp.watch.shop.model;

public class LuxuryWatch extends Watch {

    private String material;
    private String faceGlass;

    public LuxuryWatch(String brand, String model, String origin, String face, String mechanism, boolean waterProof,
                       double price, int quantity, String idNumber, String material, String faceGlass) {
        super(brand, model, origin, face, mechanism, waterProof, price, quantity, idNumber);
        this.material = material;
        this.faceGlass = faceGlass;
    }

    public String getMaterial() {
        return material;
    }

    public String getFaceGlass() {
        return faceGlass;
    }
}
