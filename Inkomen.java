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

    public String getBron() {
        return bron;
    }

    public String getDetails() {
        return "Inkomen: " + bedrag + " EUR, Bron: " + bron;
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
