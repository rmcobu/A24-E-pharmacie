package service;

import dao.MedicamentDAO;
import model.Medicament;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentServiceTest {
    private MedicamentService service;
    private MedicamentDAO fakeDao;

    private Medicament m1;
    private Medicament m2;

    @BeforeEach
    void setUp() throws Exception {
        // 1) Créer le service et la fausse DAO
        service = new MedicamentService();
        fakeDao = new MedicamentDAO() {
            @Override
            public List<Medicament> findAll() {
                return Arrays.asList(m1, m2);
            }
            @Override
            public Medicament findById(int id) {
                return id == 1 ? m1 : (id == 2 ? m2 : null);
            }
            @Override
            public List<Medicament> findByNameOrCategory(String nomFilter, String catFilter) {
                // si filtre sur "asp" ou catégorie "Médicaments", renvoyer m1, sinon vide
                boolean keep1 = (nomFilter != null && m1.getNom().toLowerCase().contains(nomFilter.toLowerCase()))
                        || (catFilter != null && m1.getCategorie().toLowerCase().contains(catFilter.toLowerCase()));
                return keep1 ? Collections.singletonList(m1) : Collections.emptyList();
            }
        };
        // 2) Préparer deux objets Medicament
        m1 = new Medicament();
        m1.setId(1);
        m1.setNom("Aspirine");
        m1.setCategorie("Médicaments");
        m1.setPrix(BigDecimal.valueOf(5.99));
        m1.setDisponibilite(true);
        m1.setDescription("Douleur");
        m1.setQuantiteEnStock(50);
        m1.setImage("aspirin.png");

        m2 = new Medicament();
        m2.setId(2);
        m2.setNom("Vitamine C");
        m2.setCategorie("Vitamine");
        m2.setPrix(BigDecimal.valueOf(9.99));
        m2.setDisponibilite(true);
        m2.setDescription("Immunité");
        m2.setQuantiteEnStock(30);
        m2.setImage("vitc.png");

        // 3) Injecter fakeDao dans service via réflexion
        Field daoField = MedicamentService.class.getDeclaredField("dao");
        daoField.setAccessible(true);
        daoField.set(service, fakeDao);
    }

    @Test
    void getCatalogue_shouldReturnAllFromDao() {
        List<Medicament> all = service.getCatalogue();
        assertEquals(2, all.size(), "Le service doit renvoyer les 2 éléments");
        assertTrue(all.containsAll(Arrays.asList(m1, m2)));
    }

    @Test
    void filterBy_withMatchingName_shouldReturnFiltered() {
        List<Medicament> filtered = service.filterBy("asp", null);
        assertEquals(1, filtered.size(), "Un seul résultat attendu pour filtre 'asp'");
        assertSame(m1, filtered.get(0));
    }

    @Test
    void filterBy_withNonMatching_shouldReturnEmpty() {
        List<Medicament> filtered = service.filterBy("zzz", null);
        assertTrue(filtered.isEmpty(), "Aucun résultat attendu pour filtre 'zzz'");
    }

    @Test
    void findById_existingId_shouldReturnMedicament() {
        Medicament found = service.findById(2);
        assertSame(m2, found, "Le service doit retrouver m2 pour id=2");
    }

    @Test
    void findById_unknownId_shouldReturnNull() {
        Medicament found = service.findById(99);
        assertNull(found, "Le service doit renvoyer null pour id inconnu");
    }
}