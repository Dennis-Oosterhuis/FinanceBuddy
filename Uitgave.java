public class Uitgave {
    private double bedrag;
    private String categorie;

    public Uitgave(double bedrag, String categorie) {
        this.bedrag = bedrag;
        this.categorie = categorie;
    }

    public double getBedrag() {
        return bedrag;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDetails() {
        return "Uitgave: " + bedrag + " EUR, Categorie: " + categorie;
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
