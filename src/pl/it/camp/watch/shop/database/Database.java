package pl.it.camp.watch.shop.database;

import org.apache.commons.codec.digest.DigestUtils;
import pl.it.camp.watch.shop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Watch> watches = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public Database() {
        this.watches.add(new ClassicWatch("Tissot", "Chronograph PR100", "Switzerland", "Analog", "Quartz", true, 1936.0, 0, "TCH1245"));
        this.watches.add(new ClassicWatch("Tissot", "Pathfinder GMT P68", "Switzerland", "Analog", "Quartz", true, 2200.0, 8, "TPF1475"));
        this.watches.add(new ClassicWatch("Atlantic", "Classic 68", "Switzerland", "Analog", "Quartz", false, 1315.0, 1, "ACL6879"));
        this.watches.add(new ClassicWatch("Casio", "G-Shock Mudmaster", "Japan", "Analog - Digital", "Quartz", true, 1316.0, 6, "CGS3598"));
        this.watches.add(new ClassicWatch("Orient", "Classic TX", "Japan", "Analog", "Quartz", true, 2899.0, 2, "OCL9898"));
        this.watches.add(new ClassicWatch("Lorus", "Quartz Classic", "Japan", "Analog", "Quartz", false, 499.0, 6, "LQC4569"));
        this.watches.add(new ClassicWatch("Fossil", "Neutra Chrono", "USA", "Analog", "Quartz", true, 985.0, 12, "FNC6578"));
        this.watches.add(new ClassicWatch("Timex", "Allied", "USA", "Analog", "Quartz", true, 499.0, 2, "TAL4526"));
        this.watches.add(new ClassicWatch("Nautica", "Star World", "USA", "Analog", "Quartz", false, 999.0, 18, "NSW3581"));

        this.watches.add(new Smartwach("Apple", "Watch Series 6", "USA", "Digital", "Electronic", true, 1689.0, 9, "AWS6564", "Colourful", 500.0));
        this.watches.add(new Smartwach("Huaweii", "Watch GT 2.0", "China", "Digital", "Electronic", true, 1299.0, 8, "HWG7878", "Colourful", 650.0));
        this.watches.add(new Smartwach("Samsung", "Galaxy Watch", "Korea", "Digital", "Electronic", true, 1499.0, 4, "SGW7652", "Colourful", 700.0));

        this.watches.add(new LuxuryWatch("Zenith","Pilot Tribute", "Switzerland","Analog", "Mechanic",true, 25000.0,1,"ZPT5231","Gold","Sapphire"));
        this.watches.add(new LuxuryWatch("Rolex","Daytona", "Switzerland","Analog", "Mechanic",true, 57971.0,1,"RLD6973","Gold","Diamond"));
        this.watches.add(new LuxuryWatch("Patek Philippe","5531R Rose", "Switzerland","Analog", "Mechanic",true, 38500.0,1,"PPR5531","Silver","Ruby"));

        this.users.add(new User("admin", DigestUtils.md5Hex("admin")));
        this.users.add(new User("waldek", DigestUtils.md5Hex("waldek")));
        this.users.add(new User("janusz", DigestUtils.md5Hex("janusz")));
    }
    public List<Watch> getWatches() {
        return watches;
    }
    public boolean availabilityChec(String idNumber) {
        for (Watch watch : this.watches) {
            if (watch.getIdNumber().equals(idNumber) && watch.getQuantity() >= 0) {
                return true;
            }
        }
        return false;
    }
    public boolean sellWatch(int orderNumber, String idNumber) {
        for (Watch watch : this.watches) {
            if (orderNumber <= watch.getQuantity() && watch.getIdNumber().equals(idNumber)) {
                watch.sell(orderNumber);
                System.out.println("Kwota do zapłaty: " + watch.price(orderNumber, watch.getPrice())+ " zł"); // czy to wyliczanie kwoty można jakoś sprytnie przenieść do GUI ?
                return true;                                                                                  // chodzi o to że bez Listy zegarków nie wczytuj mi ceny zegarka do wyliczenia kwoty
            }
        }
        return false;
    }
    public boolean quantityReturn(int returnNumber, String idNumber1) {
        for (Watch watch : this.watches) {
            if (returnNumber > 0 && watch.getIdNumber().equals(idNumber1)) {
                watch.giveBack(returnNumber);
                return true;
            }
        }
        return false;
}
    public boolean authenticate(String login, String password) {
        for (User currentUser : this.users) {
            if (currentUser.getLogin().equals(login) && currentUser.getPassword().equals(DigestUtils.md5Hex(password))) {
                return true;
            }
        }
        return false;
    }
}


