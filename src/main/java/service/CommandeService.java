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
        commande.setEtat(false);
        commande.setModeRetrait(modeRetrait);
        commande.setModePaiement(modePaiement);

        return commande;
    }

}

