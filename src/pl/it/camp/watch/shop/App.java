package pl.it.camp.watch.shop;

import org.apache.commons.codec.digest.DigestUtils;
import pl.it.camp.watch.shop.database.Database;
import pl.it.camp.watch.shop.gui.GUI;
import pl.it.camp.watch.shop.model.ClassicWatch;
import pl.it.camp.watch.shop.model.User;
import pl.it.camp.watch.shop.model.Watch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Database database = Database.getInstance ( );
        BufferedReader reader = new BufferedReader ( new InputStreamReader ( System.in ) );
        Scanner in = new Scanner ( System.in );

        boolean flag1 = true;
        while (flag1) {
            try {
                GUI.getInstance ( ).showIntranceMenu ( );
                switch (reader.readLine ( )) {
                    case "1":
                        database.loginMechanism ( reader ); // NIe wiem czy takie pisanie metod jest w porządku - zdecydowałem się na taki zapis ponieważ
                        break;                              // naza wskauje jednoznacznie na to co tm się w tej metodzie dzieje a ilość napisanego kodu jest znacznie mniejsza
                    case "2":                               // przez co dużo łatwiej jest czytać tą klase
                        database.registerMechanism ( reader );
                        database.loginAfterRegisterMechanism ( reader );
                        break;
                    case "3":
                        flag1 = false;
                        System.out.println ( "Dziękujemy za wizyte w naszym sklepie !!\nDo widzenia !!" );
                        System.exit ( 0 );
                        break;
                    default:
                        System.out.println ( "Nie ma takiej opcji w menu, wybierz ponownie !!" );
                        continue;
                }
                boolean flag = true;
                while (flag) {
                    try {
                        GUI.getInstance ( ).showMainMenu ( );
                        switch (reader.readLine ( )) {
                            case "1":
                                GUI.getInstance ( ).showAllWatches ( database.getWatches ( ) );
                                break;
                            case "2":
                                System.out.println ( "Podaj Numer ID zegarka: " );
                                String idNumber = reader.readLine ( );
                                boolean correctWatchId = database.availabilityCheck ( idNumber );
                                if (correctWatchId) {
                                    System.out.println ( "Podaj ilość sztuk które chcesz zakupić: " );
                                } else {
                                    System.out.println ( "Nie ma takiego zegarka !!\nZłóż zamówienie ponownie !! " );
                                    break;
                                }
                                if (database.sellWatch ( in.nextInt ( ), idNumber )) {
                                    System.out.println ( "Zamówienie zostało przyjte do realizacji !!" );
                                } else {
                                    System.out.println ( "Ilość zegarkow w magazynie jest nie wystarczająca !!" );
                                }
                                break;
                            case "3":
                                System.out.println ( "Podaj Numer ID zegarka który chcesz zwrócić: " );
                                String idNumber1 = reader.readLine ( );
                                boolean correctWatchReturn = database.availabilityCheck ( idNumber1 );
                                if (correctWatchReturn) {
                                    System.out.println ( "Podaj ilość sztuk które chcesz zwrócić " );
                                } else {
                                    System.out.println ( "Nie ma takiego zegarka !!" );
                                    break;
                                }
                                if (database.quantityReturn ( in.nextInt ( ), idNumber1 )) {
                                    System.out.println ( "Zwrot został przyjęty do realizacji !!" );
                                }
                                break;
                            case "4":
                                flag = false;
                                Database.getInstance ().writeDataToFile ();
                                System.out.println ( "Dziękujemy za wizyte w naszym sklepie !!\nDo widzenia !!" );
                                System.exit ( 0 );
                            default:
                                System.out.println ( "Nie ma takiej opcji w menu, proszę wybierz ponownie !!" );
                        }

                    } catch (IOException e) {
                        System.out.println ( "Wczytywanie danych nie udane" );
                    }
                }
            } catch (IOException e) {
                e.printStackTrace ( );
            }
        }
    }
}

