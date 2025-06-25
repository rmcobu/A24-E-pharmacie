package dao;

import model.Medicament;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentDAOTest {

    private MedicamentDAO dao;

    @BeforeEach
    void setUp() {
        // Note : EM_Fournisseur initialise son EntityManagerFactory à la première utilisation
        dao = new MedicamentDAO();
    }

    @Test
    void findAll_shouldReturnNonEmptyList() {
        List<Medicament> all = dao.findAll();
        assertNotNull(all, "findAll ne doit pas retourner null");
        assertFalse(all.isEmpty(), "Le catalogue ne doit pas être vide");
    }

    @Test
    void findById_existingId_returnsMedicament() {
        // Suppose qu'il existe bien un médicament avec id=1 dans la BDD de test
        Medicament m = dao.findById(1);
        assertNotNull(m, "findById(1) doit renvoyer un Medicament existant");
        assertEquals(1, m.getId(), "L'ID du Medicament doit être 1");
        assertNotNull(m.getNom(), "Le nom du Medicament ne doit pas être null");
    }

    @Test
    void findById_nonExisting_returnsNull() {
        Medicament m = dao.findById(-999);
        assertNull(m, "findById(-999) doit renvoyer null pour un id inexistant");
    }

    @Test
    void findByNameOrCategory_nullFilters_returnsAll() {
        List<Medicament> filtered = dao.findByNameOrCategory(null, null);
        List<Medicament> all      = dao.findAll();
        assertEquals(all.size(), filtered.size(),
                "Avec filtres null, findByNameOrCategory doit renvoyer tout le catalogue");
    }

    @Test
    void findByNameOrCategory_nameFilter_returnsMatching() {
        String nomFilter = "Tylenol";
        List<Medicament> result = dao.findByNameOrCategory(nomFilter, null);
        assertFalse(result.isEmpty(), "Le filtre sur le nom doit retourner au moins un résultat");
        assertTrue(result.stream()
                        .allMatch(m -> m.getNom().toLowerCase().contains(nomFilter.toLowerCase())),
                "Tous les médicaments retournés doivent contenir 'Tylenol' dans leur nom");
    }

    @Test
    void findByNameOrCategory_categoryFilter_returnsMatching() {
        String catFilter = "Médicaments";
        List<Medicament> result = dao.findByNameOrCategory(null, catFilter);
        assertFalse(result.isEmpty(), "Le filtre sur la catégorie doit retourner au moins un résultat");
        assertTrue(result.stream()
                        .allMatch(m -> m.getCategorie().toLowerCase().contains(catFilter.toLowerCase())),
                "Tous les médicaments retournés doivent appartenir à la catégorie 'Médicaments'");
    }

    @Test
    void findByNameOrCategory_bothFilters_returnsIntersection() {
        String nomFilter = "Aspi";
        String catFilter = "Médicaments";
        List<Medicament> result = dao.findByNameOrCategory(nomFilter, catFilter);
        assertFalse(result.isEmpty(), "Le filtre combiné doit retourner au moins un résultat");
        result.forEach(m -> {
            assertTrue(m.getNom().toLowerCase().contains(nomFilter.toLowerCase()),
                    "Le nom doit contenir 'Aspi'");
            assertTrue(m.getCategorie().toLowerCase().contains(catFilter.toLowerCase()),
                    "La catégorie doit contenir 'Médicaments'");
        });
    }
}