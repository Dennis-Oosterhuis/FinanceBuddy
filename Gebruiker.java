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

    public List<Inkomen> getInkomens() {
        return inkomens;
    }

    public List<Uitgave> getUitgaven() {
        return uitgaven;
    }

    public String toonOverzicht() {
        double totaalInkomen = 0;
        double totaalUitgaven = 0;
        StringBuilder sb = new StringBuilder();

        sb.append("Inkomens:\n");
        for (Inkomen i : inkomens) {
            sb.append(" - ").append(i).append("\n");
            totaalInkomen += i.getBedrag();
        }

        sb.append("\nUitgaven:\n");
        for (Uitgave u : uitgaven) {
            sb.append(" - ").append(u).append("\n");
            totaalUitgaven += u.getBedrag();
        }

        sb.append("\nTotaal inkomen: ").append(totaalInkomen).append(" EUR\n");
        sb.append("Totaal uitgaven: ").append(totaalUitgaven).append(" EUR\n");
        sb.append("Besparing potentieel: ").append(totaalInkomen - totaalUitgaven).append(" EUR\n");

        return sb.toString();
    }
}
