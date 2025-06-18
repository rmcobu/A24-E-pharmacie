package service;

import model.Commande;
import model.Client;
import java.time.LocalDateTime;

public class CommandeService {

    public Commande creerCommande(Client client, String adresse, Boolean modeRetrait, Boolean modePaiement) {
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setAdresse(adresse);
        commande.setDateCommande(LocalDateTime.now());
        commande.setEtat(false); // par défaut en cours
        commande.setModeRetrait(modeRetrait);
        commande.setModePaiement(modePaiement);
        // commande.setIdPaiement(...) // a définir

        return commande;
    }

    // methode pour enregistrer en base
}

