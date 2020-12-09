public class Planeta {
    private  String nom;
    private double habitants;

    public Planeta(String nom, int habitants) {
        this.nom = nom;
        this.habitants = habitants;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getHabitants() {
        return habitants;
    }

    public void setHabitants(double habitants) {
        this.habitants = habitants;
    }

    @Override
    public String toString() {
        return "Planeta: " + nom;
    }
}
