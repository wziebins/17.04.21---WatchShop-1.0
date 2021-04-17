package pl.it.camp.watch.shop.model;

public class Smartwach extends Watch {

    private String display;
    private double batteryCapacity;

    public Smartwach(String brand, String model, String orgin, String face, String mechanism, boolean waterProof,
                     double price, int quantity, String idNumber, String display, double batteryCapacity) {
        super(brand, model, orgin, face, mechanism,waterProof, price, quantity, idNumber);

        this.display = display;
        this.batteryCapacity = batteryCapacity;
    }

    public String getDisplay() {
        return display;
    }
    public double getBatteryCapacity() {
        return batteryCapacity;

    }

}
