public class Inkomen {
    private double bedrag;
    private String bron;

    public Inkomen(double bedrag, String bron) {
        this.bedrag = bedrag;
        this.bron = bron;
    }

    public double getBedrag() {
        return bedrag;
    }

    public String getDetails() {
        return "Inkomen: " + bedrag + " EUR, Bron: " + bron;
    }
}