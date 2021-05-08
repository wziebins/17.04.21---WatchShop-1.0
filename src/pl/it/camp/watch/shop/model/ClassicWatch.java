package pl.it.camp.watch.shop.model;

public class ClassicWatch extends Watch {

    public ClassicWatch(String brand, String model, String orgin, String face, String mechanism, boolean waterProof,
                        double price, int quantity, String idNumber, String availability) {
        super ( brand, model, orgin, face, mechanism, waterProof, price, quantity, idNumber, availability);
    }

    @Override
    public String convertToDbRecord() {
        StringBuilder sb = new StringBuilder ( );

        sb.append ( "ClassicWatch;" )
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
                .append ( this.getQuantity ( ) )
                .append ( ";" )
                .append ( this.getIdNumber ( ) );

        sb.append ( ";" );
        if (this.getQuantity ( ) > 0) {
            sb.append ( "Dostepny" );
        } else {
            sb.append ( "Niedostepny" );
        }
        return sb.toString ( );
    }
}
