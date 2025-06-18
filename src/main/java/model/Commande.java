package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande")
    private Integer idCommande;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client client;

    @Column(name = "id_paiement")
    private Integer idPaiement;

    @Column(name = "etat", nullable = false)
    private Boolean etat;

    @Column(name = "date_commande", nullable = false)
    private LocalDateTime dateCommande;

    @Column(name = "mode_paiement", nullable = false)
    private Boolean modePaiement;

    @Column(name = "mode_retrait", nullable = false)
    private Boolean modeRetrait;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    // Getters et Setters

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(Integer idPaiement) {
        this.idPaiement = idPaiement;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Boolean getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(Boolean modePaiement) {
        this.modePaiement = modePaiement;
    }

    public Boolean getModeRetrait() {
        return modeRetrait;
    }

    public void setModeRetrait(Boolean modeRetrait) {
        this.modeRetrait = modeRetrait;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
