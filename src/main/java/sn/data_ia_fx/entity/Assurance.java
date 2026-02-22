package sn.data_ia_fx.entity;


import javax.persistence.*;

@Entity

public class Assurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto -incremement
    protected int id;

    @Column(length = 50, name = "nom")
    protected String nomClient;

    @Column
    protected String numero;

    @Column
    protected double montant;

    private static int compteur = 0;

    public Assurance() {
    }

    public Assurance(String nomClient, double montant) {
        this.nomClient = nomClient;
        this.montant = montant;
        numero = getGenerateNumero();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public static void setCompteur(int compteur) {
        Assurance.compteur = compteur;
    }

    public String getNomClient() {
        return nomClient;
    }

    public double getMontant() {
        return montant;
    }

    public String getNumero() {
        return numero;
    }

    private String getGenerateNumero() {
        return String.format("ASS%04d", compteur++);
    }

    public  double calulPrime(){
        return 0;
    }
    public  String getTypeAssurance(){
        return "entity.Assurance";
    }

    public double calculCoutTotal(int a){

        return calulPrime()*a;
    }

    public static  int getCompteur() {
        return compteur;
    }


    @Override
    public String toString() {
        return "entity.Assurance{" +
                "Id='" + id + '\'' +
                "nomClient='" + nomClient + '\'' +
                ", numero='" + numero + '\'' +
                ", montant=" + montant +
                '}';
    }
}
