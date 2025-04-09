import java.util.Scanner;

public class Systeem {
    private Scanner scanner;
    private Gebruiker gebruiker;

    public Systeem() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welkom! Vul je naam en email in.");
        System.out.print("Naam: ");
        String naam = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        gebruiker = new Gebruiker(naam, email);
        invoerGegevens();
    }

    public void invoerGegevens() {
        boolean invoeren = true;
        while (invoeren) {
            System.out.println("Voer je inkomen of uitgave in:");
            System.out.println("1. Inkomen toevoegen");
            System.out.println("2. Uitgave toevoegen");
            System.out.println("3. Toon overzicht");
            System.out.println("4. Stop");
            int keuze = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (keuze) {
                case 1:
                    System.out.print("Bedrag inkomen: ");
                    double bedragInkomen = scanner.nextDouble();
                    scanner.nextLine(); // consume newline character
                    System.out.print("Bron inkomen: ");
                    String bron = scanner.nextLine();
                    Inkomen inkomen = new Inkomen(bedragInkomen, bron);
                    gebruiker.voegInkomenToe(inkomen);
                    break;

                case 2:
                    System.out.print("Bedrag uitgave: ");
                    double bedragUitgave = scanner.nextDouble();
                    scanner.nextLine(); // consume newline character
                    System.out.print("Categorie uitgave: ");
                    String categorie = scanner.nextLine();
                    Uitgave uitgave = new Uitgave(bedragUitgave, categorie);
                    gebruiker.voegUitgaveToe(uitgave);
                    break;

                case 3:
                    Overzicht overzicht = new Overzicht();
                    overzicht.toonOverzicht(gebruiker);
                    break;

                case 4:
                    invoeren = false;
                    break;

                default:
                    System.out.println("Ongeldige keuze.");
            }
        }
    }

    public void toonResultaten() {
        Overzicht overzicht = new Overzicht();
        overzicht.toonOverzicht(gebruiker);
    }
}