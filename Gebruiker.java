import java.util.ArrayList;
import java.util.List;

public class Gebruiker {
    private String naam;
    private String email;
    private List<Inkomen> inkomens;
    private List<Uitgave> uitgaven;

    public Gebruiker(String naam, String email) {
        this.naam = naam;
        this.email = email;
        this.inkomens = new ArrayList<>();
        this.uitgaven = new ArrayList<>();
    }

    public void voegInkomenToe(Inkomen inkomen) {
        inkomens.add(inkomen);
    }

    public void voegUitgaveToe(Uitgave uitgave) {
        uitgaven.add(uitgave);
    }

    public String toonOverzicht() {
        double totaalInkomen = 0;
        double totaalUitgaven = 0;

        for (Inkomen inkomen : inkomens) {
            totaalInkomen += inkomen.getBedrag();
        }

        for (Uitgave uitgave : uitgaven) {
            totaalUitgaven += uitgave.getBedrag();
        }

        return "Totaal inkomen: " + totaalInkomen + " EUR\n" +
                "Totaal uitgaven: " + totaalUitgaven + " EUR\n" +
                "Besparing potentieel: " + (totaalInkomen - totaalUitgaven) + " EUR\n";
    }
}
