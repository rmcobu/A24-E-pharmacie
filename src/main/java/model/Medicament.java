package model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Medicament")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nom", length = 100, nullable = false)
    private String nom;

    @Column(name = "categorie", length = 50)
    private String categorie;

    @Column(name = "prix", precision = 10, scale = 2, nullable = false)
    private BigDecimal prix;

    @Column(name = "disponibilite")
    private Boolean disponibilite;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "quantite_en_stock")
    private Integer quantiteEnStock;

    @Column(name = "image", length = 255)
    private String image;


    // == Constructor vacío para JPA ==
    protected Medicament() {}

    // == equals/hashCode basados en id ==
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicament)) return false;
        Medicament m = (Medicament) o;
        return id != null && id.equals(m.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(Integer quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}