package pl.it.camp.watch.shop;

import pl.it.camp.watch.shop.database.Database;
import pl.it.camp.watch.shop.gui.GUI;
import pl.it.camp.watch.shop.model.ClassicWatch;
import pl.it.camp.watch.shop.model.Watch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Database database = new Database();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);

        int loginCounter = 0;
        while (true) {
            System.out.println("Podaj login: ");
            String login = reader.readLine();
            System.out.println("Podaj hasło: ");
            String password = reader.readLine();
           if (database.authenticate(login, password)) {
               break;
           }else{
               System.out.println("Nie poprawne dane !!");
               loginCounter++;
           }
           if (loginCounter >= 3) {
               System.out.println("Logowanie nieudane !!");
               System.exit(0);
           }
        }
        boolean flag = true;
        while (flag) {
            GUI.getInstance().showMainMenu();
            switch (reader.readLine()) {
                case "1":
                    GUI.getInstance().showAllWatches(database.getWatches());
                    break;
                case "2":
                    System.out.println("Podaj Numer ID zegarka: ");
                    String idNumber = reader.readLine();
                    boolean correctWatchId = database.availabilityChec(idNumber);
                    if (correctWatchId) {
                        System.out.println("Podaj ilość sztuk które chcesz zakupić: ");
                    } else {
                        System.out.println("Nie ma takiego zegarka !!\nZłóż zamówienie ponownie !! ");
                        break;
                    }
                    if (database.sellWatch(in.nextInt(), idNumber)) {
                        System.out.println("Zamówienie zostało przyjte do realizacji !!");
                    } else {
                        System.out.println("Ilość zegarkow w magazynie jest nie wystarczająca !!");
                    }
                    break;
                case "3":
                    System.out.println("Podaj Numer ID zegarka który chcesz zwrócić: ");
                    String idNumber1 = reader.readLine();
                    boolean correctWatchReturn = database.availabilityChec(idNumber1); //Zmienić nazwę
                    if (correctWatchReturn) {
                        System.out.println("Podaj ilość sztuk które chcesz zwrócić ");
                    } else {
                        System.out.println("Nie ma takiego zegarka !!");
                        break;
                    }
                    if (database.quantityReturn(in.nextInt(), idNumber1)) {
                        System.out.println("Zwrot został przyjęty do realizacji !!");
                    }
                    break;
                case "4":
                    flag = false;
                    System.out.println("Dziękujemy za wizyte w naszym sklepie !!\nDo widzenia !!");
                    break;
                default:
                    System.out.println("Nie ma takiej opcji w menu, proszę wybierz ponownie !!");
            }
        }
    }
}
