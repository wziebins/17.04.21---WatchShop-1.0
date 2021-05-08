package pl.it.camp.watch.shop.model;

public class LuxuryWatch extends Watch {

    private final String material;
    private final String faceGlass;

    public LuxuryWatch(String brand, String model, String origin, String face, String mechanism, boolean waterProof,
                       double price, int quantity, String idNumber, String material, String faceGlass, String availability) {
        super(brand, model, origin, face, mechanism, waterProof, price, quantity, idNumber, availability);
        this.material = material;
        this.faceGlass = faceGlass;
    }

    public String getMaterial() {
        return material;
    }

    public String getFaceGlass() {
        return faceGlass;
    }

    @Override
    public String convertToDbRecord() {
        StringBuilder sb = new StringBuilder (  );

        sb.append ( "LuxuryWatch;" )
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
                .append ( this.material )
                .append ( ";" )
                .append ( this.faceGlass );

        sb.append ( ";" );
        if (this.getQuantity ( ) > 0) {
            sb.append ( "Dostepny" );
        } else {
            sb.append ( "Niedostepny" );
        }
        return sb.toString ( );
    }
}
