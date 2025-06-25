package model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

    // Liste JSON des produits {id, nom, prix, quantite}
    @Column(name = "items", nullable = false, columnDefinition = "JSON")
    private String itemsJson;

    // Montants calculés
    @Column(name = "sous_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal sousTotal;

    @Column(name = "tps", nullable = false, precision = 10, scale = 2)
    private BigDecimal tps;

    @Column(name = "tvq", nullable = false, precision = 10, scale = 2)
    private BigDecimal tvq;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    // ─── Getters et Setters ───────────────────────────────────────────────────

    public Integer getIdCommande() { return idCommande; }
    public void setIdCommande(Integer idCommande) { this.idCommande = idCommande; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Integer getIdPaiement() { return idPaiement; }
    public void setIdPaiement(Integer idPaiement) { this.idPaiement = idPaiement; }

    public Boolean getEtat() { return etat; }
    public void setEtat(Boolean etat) { this.etat = etat; }

    public LocalDateTime getDateCommande() { return dateCommande; }
    public void setDateCommande(LocalDateTime dateCommande) { this.dateCommande = dateCommande; }

    public Boolean getModePaiement() { return modePaiement; }
    public void setModePaiement(Boolean modePaiement) { this.modePaiement = modePaiement; }

    public Boolean getModeRetrait() { return modeRetrait; }
    public void setModeRetrait(Boolean modeRetrait) { this.modeRetrait = modeRetrait; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getItemsJson() { return itemsJson; }
    public void setItemsJson(String itemsJson) { this.itemsJson = itemsJson; }

    public BigDecimal getSousTotal() { return sousTotal; }
    public void setSousTotal(BigDecimal sousTotal) { this.sousTotal = sousTotal; }

    public BigDecimal getTps() { return tps; }
    public void setTps(BigDecimal tps) { this.tps = tps; }

    public BigDecimal getTvq() { return tvq; }
    public void setTvq(BigDecimal tvq) { this.tvq = tvq; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public Date getDateCommandeAsDate() {
        return Date.from(this.dateCommande
               .atZone(ZoneId.systemDefault())
              .toInstant());
    }
}
