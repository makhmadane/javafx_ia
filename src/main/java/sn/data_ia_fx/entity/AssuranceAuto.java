package sn.data_ia_fx.entity;

public class AssuranceAuto extends Assurance{

    private String immat;
    private int puissance;
    private int bonus;

    public AssuranceAuto(String nomClient, double montant, String immat, int puissance, int bonus) {
        super(nomClient, montant);
        this.immat = immat;
        this.puissance = puissance;
        this.bonus = bonus;
    }

    @Override
    public double calulPrime() {
        return montant * (1 + puissance*0.05) * (bonus/100.0);
    }

    @Override
    public String getTypeAssurance() {
        return "ASSURANCE AUTOMOBILE";
    }

    @Override
    public String toString() {
        return "entity.AssuranceAuto{" +
                "immat='" + immat + '\'' +
                ", puissance=" + puissance +
                ", bonus=" + bonus +
                ", nomClient='" + nomClient + '\'' +
                ", numero='" + numero + '\'' +
                ", montant=" + montant +
                '}';
    }

    public String getImmat() {
        return immat;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
