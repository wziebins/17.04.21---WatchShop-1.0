package pl.it.camp.watch.shop.database;

import org.apache.commons.codec.digest.DigestUtils;
import pl.it.camp.watch.shop.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Database {
    final List<Watch> watches = new ArrayList<> ( );
    final List<User> users = new ArrayList<> ( );
    private static Database instance = new Database ( );
    private final String pathToDbFile = "watchShopDb.txt";


    private Database() {
        this.loadDataFromFile ();


    }
    public static Database getInstance() {
        if (Database.instance == null) {
            Database.instance = new Database ( );
        }
        return Database.instance;

    }

    public List<Watch> getWatches() {
        return watches;
    }

    public boolean availabilityCheck(String idNumber) {
        for (Watch watch : this.watches) {
            if (watch.getIdNumber ( ).equals ( idNumber ) && watch.getQuantity ( ) >= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean sellWatch(int orderNumber, String idNumber) {
        for (Watch watch : this.watches) {
            if (orderNumber <= watch.getQuantity ( ) && watch.getIdNumber ( ).equals ( idNumber )) {
                watch.sell ( orderNumber );
                System.out.println ( "Kwota do zapłaty: " + watch.price ( orderNumber, watch.getPrice ( ) ) + " zł" ); // czy to wyliczanie kwoty można jakoś sprytnie przenieść do GUI ?
                return true;                                                                                  // chodzi o to że bez Listy zegarków nie wczytuj mi ceny zegarka do wyliczenia kwoty
            }
        }
        return false;
    }

    public boolean quantityReturn(int returnNumber, String idNumber1) {
        for (Watch watch : this.watches) {
            if (returnNumber > 0 && watch.getIdNumber ( ).equals ( idNumber1 )) {
                watch.giveBack ( returnNumber );
                return true;
            }
        }
        return false;
    }

    public boolean authenticateUser(String login, String password) {
        for (User currentUser : this.users) {
            if (currentUser.getLogin ( ).equals ( login ) && currentUser.getPassword ( ).equals ( DigestUtils.md5Hex ( password ) )) {
                return true;
            }
        }
        return false;
    }

    public String regLogin(String login) {
        return login;
    }

    public String regPassword(String password) {
        return DigestUtils.md5Hex ( password );
    }

    public User createUser(String login, String password) {
        return new User ( login, DigestUtils.md5Hex ( password ) );
    }

    public void addNewUser(User user) {
        users.add ( user );
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean authenticateNewUser(String login, String password) {
        for (User users : this.users) {
            if (users.getLogin ( ).equals ( login ) && users.getPassword ( ).equals ( DigestUtils.md5Hex ( password ) )) {
                return true;
            }
        }
        return true;
    }

    public void loginMechanism(BufferedReader reader) { //NIestety nie udało mi się tego tak podzelić żeby nie było tutaj wyświetlania,
        //które powinno się znajdować w GUI, czy jest na to jakiś fajny sposób ?
        while (true) {
            String login, password;
            try {
                System.out.println ( "Podaj login: " );
                login = reader.readLine ( );
                System.out.println ( "Podaj hasło: " );
                password = reader.readLine ( );
            } catch (IOException e) {
                System.out.println ( "Wczytywanie danych nie udane !!" );
                continue;
            }
            if (authenticateUser ( login, password )) {
                System.out.println ( "Logowanie poprawne, życzmy udanych zakupów !!" );
                break;
            } else {
                System.out.println ( "Nie poprawne dane !!\nSpróbuj jeszcze raz" );
            }
        }
    }

    public void registerMechanism(BufferedReader reader) {
        try {
            System.out.println ( "Witamy w procesie rejestracji !\nPodaj nazwę użytkownika:" );
            String chosenLogin = regLogin ( reader.readLine ( ) );
            System.out.println ( "Podaj swoje hasło: " );
            String chosenPassword = regPassword ( reader.readLine ( ) );
            User newUser1 = createUser ( chosenLogin, chosenPassword );
            addNewUser ( newUser1 );
        } catch (IOException e) {
            System.out.println ( "Niepoprawne dane !!" );
        }
    }

    public void loginAfterRegisterMechanism(BufferedReader reader) { // Wiem że ta metoda to praktycznie powtórzenie mechanizmu logowania i powinno się
        String chosenLogin, chosenPassword;                         // to skrócić do jedej ale z jakiegoś powodu nie chce wtedy działać i nigd nie znajduje użytkownika
        try {
            System.out.println ( "Teraz możesz się zalogować" );
            System.out.println ( "Podaj login: " );
            chosenLogin = reader.readLine ( );
            System.out.println ( "Podaj hasło: " );
            chosenPassword = reader.readLine ( );
            if (authenticateNewUser ( chosenLogin, DigestUtils.md5Hex ( chosenPassword ) )) {
                System.out.println ( "Logowanie poprawne, życzmy udanych zakupów !!" );
            } else {
                System.out.println ( "Nie poprawne dane !!" );
            }
        } catch (IOException e) {
            System.out.println ( "Nie poprawne dane !!" );
        }
    }

    public void writeDataToFile() {
        try {
            BufferedWriter writer = new BufferedWriter ( new FileWriter ( this.pathToDbFile ) );
            for (Watch watches : this.watches) {
                writer.append ( watches.convertToDbRecord ( ) );
                writer.newLine ( );

            }

            for (User users : this.users) {
                writer.append ( users.convertToDbRecord ( ) );
                writer.newLine ( );

            }

            writer.close ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }
 public void loadDataFromFile() {
        try {
            BufferedReader reader = new BufferedReader ( new FileReader ( this.pathToDbFile ) );

            String record;
            while ((record = reader.readLine ( )) != null) {
                String[] recordArray = record.split ( ";" );
                switch (recordArray[0]) {
                    case "ClassicWatch":
                        this.watches.add (
                                 new ClassicWatch ( recordArray[1], recordArray[2],
                                        recordArray[3], recordArray[4], recordArray[5],
                                        Boolean.parseBoolean ( recordArray[6] ),
                                        Double.parseDouble ( recordArray[7] ),
                                        Integer.parseInt ( recordArray[8] ),
                                        recordArray[9], recordArray[10] )
                        );
                        break;
                    case "Smartwatch":
                        this.watches.add(
                                new Smartwach ( recordArray[1], recordArray[2],
                                        recordArray[3], recordArray[4], recordArray[5],
                                        Boolean.parseBoolean ( recordArray[6] ),
                                        Double.parseDouble ( recordArray[7] ),
                                        Integer.parseInt ( recordArray[8] ),
                                        recordArray[9],recordArray[10],Double.parseDouble ( recordArray[11] ), recordArray[12] )
                        );
                        break;
                    case "LuxuryWatch":
                        this.watches.add (
                                new LuxuryWatch ( recordArray[1], recordArray[2],
                                        recordArray[3], recordArray[4], recordArray[5],
                                        Boolean.parseBoolean ( recordArray[6] ),
                                        Double.parseDouble ( recordArray[7] ),
                                        Integer.parseInt ( recordArray[8] ),
                                        recordArray[9], recordArray[10], recordArray[11], recordArray[12] )
                        );
                        break;
                    case "User":
                        this.users.add (new User ( recordArray[1], recordArray[2] ));
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace ( );
            System.out.println ("Wczytywanie danych nie udane !!" );
            System.exit ( 0 );
        }
    }
}




