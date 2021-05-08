package pl.it.camp.watch.shop.model;

public class Smartwach extends Watch {

    private String display;
    private double batteryCapacity;

    public Smartwach(String brand, String model, String orgin, String face, String mechanism, boolean waterProof,
                     double price, int quantity, String idNumber, String display, double batteryCapacity, String availability) {
        super(brand, model, orgin, face, mechanism,waterProof, price, quantity, idNumber, availability);

        this.display = display;
        this.batteryCapacity = batteryCapacity;
    }

    public String getDisplay() {
        return display;
    }
    public double getBatteryCapacity() {
        return batteryCapacity;

    }

    @Override
    public String convertToDbRecord() {
        StringBuilder sb = new StringBuilder (  );

        sb.append ( "Smartwatch;" )
                .append ( this.getBrand () )
                .append ( ";" )
                .append ( this.getModel ( ) )
                .append ( ";" )
                .append ( this.getOrigin ( ) )
                .append ( ";" )
                .append ( this.getFace ( ) )
                .append ( ";" )
                .append ( this.getMechanism ( ) )
                .append ( ";" )
                .append ( this.isWaterProof ( ) ? "Tak" : "Nie" )
                .append ( ";" )
                .append ( this.getPrice ( ) )
                .append ( ";" )
                .append ( this.getQuantity () )
                .append ( ";" )
                .append ( this.getIdNumber () )
                .append ( ";" )
                .append ( this.display)
                .append ( ";" )
                .append ( this.batteryCapacity );

        sb.append ( ";" );
        if (this.getQuantity ( ) > 0) {
            sb.append ( "Dostepny" );
        } else {
            sb.append ( "Niedostepny" );
        }
        return sb.toString ( );
    }
}
