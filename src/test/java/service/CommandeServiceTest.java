package service;

import model.Client;
import model.Commande;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommandeServiceTest {

    private CommandeService service;
    private Client client;

    @BeforeEach
    void setUp() {
        service = new CommandeService();
        client = new Client();
        client.setIdClient(42);
        client.setPrenom("Alice");
        client.setNom("Dupont");
        client.setEmail("alice@gmail.com");
        client.setPassword("alice");
        client.setTelephone("514 852 9876");
    }

    @Test
    void creerCommande_shouldPopulateAllFields() {
        String adresse       = "123 Rue de Test, 75000 Paris";
        Boolean modeRetrait  = true;   // livraison
        Boolean modePaiement = false;  // comptant

        // Capturer un intervalle de temps avant/après l'appel
        LocalDateTime before = LocalDateTime.now();
        Commande cmd = service.creerCommande(client, adresse, modeRetrait, modePaiement);
        LocalDateTime after  = LocalDateTime.now();

        assertNotNull(cmd, "La commande ne doit pas être nulle");
        assertSame(client, cmd.getClient(), "Le client doit être celui passé en paramètre");
        assertEquals(adresse, cmd.getAdresse(), "L'adresse doit être celle passée en paramètre");
        assertFalse(cmd.getEtat(), "L'état par défaut doit être false");
        assertEquals(modeRetrait, cmd.getModeRetrait(), "Le mode de retrait doit être celui passé");
        assertEquals(modePaiement, cmd.getModePaiement(), "Le mode de paiement doit être celui passé");

        assertNotNull(cmd.getDateCommande(), "La date de commande doit être initialisée");
        // Vérifier que dateCommande est bien dans l'intervalle [before, after]
        assertFalse(cmd.getDateCommande().isBefore(before),
                "La date de commande ne doit pas être avant l'appel");
        assertFalse(cmd.getDateCommande().isAfter(after),
                "La date de commande ne doit pas être après l'appel");
    }

    @Test
    void creerCommande_shouldLeaveIdPaiementNull() {
        Commande cmd = service.creerCommande(client, "addr", false, true);
        assertNull(cmd.getIdPaiement(), "IdPaiement ne doit pas être initialisé par creerCommande");
    }
}
