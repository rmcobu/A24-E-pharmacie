package model;

import jakarta.persistence.*;

@Entity
@Table(name = "medicament")
public class Medicament {

    @Id
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String categorie;

    @Column(nullable = false)
    private double prix;

    @Column(nullable = false)
    private boolean disponibilite;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "quantite_en_stock", nullable = false)
    private int quantiteEnStock;

    @Column
    private String image;

    public Medicament() {
    }

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

    // Setters (optionnel si nécessaire pour Hibernate)
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setDisponibilite(boolean disponibilite) { this.disponibilite = disponibilite; }
    public void setDescription(String description) { this.description = description; }
    public void setQuantiteEnStock(int quantiteEnStock) { this.quantiteEnStock = quantiteEnStock; }
    public void setImage(String image) { this.image = image; }
}
