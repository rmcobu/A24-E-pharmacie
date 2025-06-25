package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PanierTest {
    private Panier panier;
    private Medicament med;
    private Map<Medicament, Integer> items = new HashMap<>();

    @BeforeEach
    void setUp() {
        panier = new Panier();
        med = new Medicament();
        med.setId(1);
        med.setNom("TestMed");
        med.setCategorie("Catégorie");
        med.setPrix(new BigDecimal("5.0"));
        med.setDisponibilite(true);
        med.setDescription("Desc");
        med.setQuantiteEnStock(10);
        med.setImage("test.png");
    }

    @AfterEach
    void tearDown() {
        panier = null;
        med = null;
    }

    @Test
    void ajouterMedicament() {
        /// 1) J’ajoute 2 unités
        panier.ajouterMedicament(med, 2);
        assertEquals(2, panier.getItems().get(med).intValue(),
                "Il doit y avoir 2 unités");
        assertEquals(5.0 * 2,
                panier.getTotal().doubleValue(),
                1e-6,
                "Total doit être 2×prix");

        // 2) J’ajoute encore 2 unités → total 4 unités
        panier.ajouterMedicament(med, 2);
        assertEquals(4, panier.getItems().get(med).intValue(),
                "Il doit y avoir 4 unités après cumul");
        assertEquals(5.0 * 4,
                panier.getTotal().doubleValue(),
                1e-6,
                "Total doit être 4×prix");
    }

    @Test
    void enleverMedicament() {
        panier.ajouterMedicament(med, 3);
        panier.enleverMedicament(med, 1);
        assertEquals(2, panier.getItems().get(med), "Il doit rester 2 unités");

        panier.enleverMedicament(med, 2);
        assertFalse(panier.getItems().containsKey(med), "L'entrée doit être supprimée");
    }

    @Test
    void supprimerMedicament() {
        panier.ajouterMedicament(med, 5);
        panier.supprimerMedicament(med);
        assertTrue(panier.getItems().isEmpty(), "Le panier doit être vide après suppression");
    }

    @Test
    void getTotalItemsCount() {
        // panier vide
        assertEquals(0, panier.getTotalItemsCount(), "Panier vide count = 0");

        // ajouter 3 du même médicament
        panier.ajouterMedicament(med, 3);
        assertEquals(3, panier.getTotalItemsCount(), "Count doit être 3");

        // ajouter un deuxième médicament
        Medicament med2 = new Medicament();
        med2.setId(2);
        med2.setNom("AutreMed");
        med2.setPrix(BigDecimal.valueOf(7.5));
        panier.ajouterMedicament(med2, 2);

        // total des quantités = 3 + 2 = 5
        assertEquals(5, panier.getTotalItemsCount(), "Count doit être 5 après ajout du second");
    }

    @Test
    void getTotal() {
        panier.ajouterMedicament(med, 2);

        // cálculo el expected con escala 2
        BigDecimal expected = new BigDecimal("5.00")
                .multiply(new BigDecimal("2"))
                .setScale(2, RoundingMode.HALF_UP);

        // comparo BigDecimal esperado vs BigDecimal obtenido, ambos con escala 2
        assertEquals(expected,
                panier.getTotal().setScale(2, RoundingMode.HALF_UP),
                "Total doit être prix×quantité avec 2 décimales");
    }

}