package pl.it.camp.watch.shop.model;

public abstract class Watch {
    private String brand;
    private String model;
    private String origin;
    private String face;
    private String mechanism;
    private boolean waterProof;
    private double price;
    private int quantity;
    private String idNumber;
    private String availability;


    public Watch(String brand, String model, String origin,
                 String face, String mechanism, boolean waterProof,
                 double price, int quantity, String idNumber, String availability) {
        this.brand = brand;
        this.model = model;
        this.origin = origin;
        this.face = face;
        this.mechanism = mechanism;
        this.waterProof = waterProof;
        this.price = price;
        this.quantity = quantity;
        this.idNumber = idNumber;
        this.availability = availability;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getOrigin() {
        return origin;
    }

    public String getFace() {
        return face;
    }

    public String getMechanism() {
        return mechanism;
    }

    public boolean isWaterProof() {
        return waterProof;
    }

    public void setWaterProof(boolean waterProof) {
        this.waterProof = waterProof;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    public void sell(int i) {
        quantity -= i;
    }
    public void giveBack(int i) {
        quantity += i;
    }
    public double price(int i, double j){
        return i*j;
    }
    abstract public String convertToDbRecord();
}

