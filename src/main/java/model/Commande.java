package model;
import javax.persistence.*;
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


    // Getters y Setters
    }




