package pl.it.camp.watch.shop.gui;

import pl.it.camp.watch.shop.model.LuxuryWatch;
import pl.it.camp.watch.shop.model.Smartwach;
import pl.it.camp.watch.shop.model.Watch;

import java.util.List;


public class GUI {

    private static GUI instance = new GUI();

    private GUI() {
    }
    public static GUI getInstance() {
        if (instance == null) {
            GUI.instance = new GUI();
        }
        return GUI.instance;
    }
    public void showMainMenu() {
        System.out.println("Wybierz opcje z menu:");
        System.out.println("1. Zegarki.");
        System.out.println("2. Złóż zamówienie.");
        System.out.println("3. Wycofaj zamówienie.");
        System.out.println("4. Wyjdź.");
    }
    public void showAllWatches(List<Watch> watches) {
        for (Watch watch : watches) {
            StringBuilder sb = new StringBuilder();
            sb.append(watch.getBrand())
                    .append("| Model: ")
                    .append(watch.getModel())
                    .append("| Kraj produkcji: ")
                    .append(watch.getOrigin())
                    .append("| Tarcza: ")
                    .append(watch.getFace())
                    .append("| Mechanizm: ")
                    .append(watch.getMechanism())
                    .append("| Wodoodporność: ")
                    .append(watch.isWaterProof() ? "Tak" : "Nie")
                    .append("| Cena: ")
                    .append(watch.getPrice())
                    .append("| Numer artykułu: ")
                    .append(watch.getIdNumber());

            if (watch instanceof Smartwach) {
                Smartwach smartwach = (Smartwach) watch;
                sb.append("| Wyświetlacz: ")
                        .append(smartwach.getDisplay())
                        .append("| Pojmność bterii:")
                        .append(smartwach.getBatteryCapacity())
                        .append(" mAh");
            }
            if (watch instanceof LuxuryWatch) {
                LuxuryWatch luxuryWatch = (LuxuryWatch) watch;
                sb.append("| Wykonany z: ")
                        .append(luxuryWatch.getMaterial())
                        .append("| Szkło wykonane z: ")
                        .append(luxuryWatch.getFaceGlass());
            }
            sb.append("| Dostępność: ");
            if (watch.getQuantity() > 0) {
                sb.append("Dostepny");
            } else {
                sb.append("Niedostepny");
            }
            System.out.println(sb.toString());

        }
    }
}