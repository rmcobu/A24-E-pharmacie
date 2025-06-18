package model;

public class Medicament {
    private int id;
    private String nom;
    private String categorie;
    private double prix;
    private boolean disponibilite;
    private String description;
    private int quantiteEnStock;
    private String image;

    public Medicament(int id, String nom, String categorie, double prix, boolean disponibilite, String description, int quantiteEnStock, String image) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.disponibilite = disponibilite;
        this.description = description;
        this.quantiteEnStock = quantiteEnStock;
        this.image = image;
    }
    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getCategorie() { return categorie; }
    public double getPrix() { return prix; }
    public boolean isDisponibilite() { return disponibilite; }
    public String getDescription() { return description; }
    public int getQuantiteEnStock() { return quantiteEnStock; }
    public String getImage() { return image; }
}