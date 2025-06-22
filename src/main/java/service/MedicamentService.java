package service;

import dao.MedicamentDAO;
import model.Medicament;
import java.util.List;

public class MedicamentService {
    private MedicamentDAO dao = new MedicamentDAO();

    /** Renvoie tout le catalogue. */
    public List<Medicament> getCatalogue() {
        return dao.findAll();
    }

    /** Filtre par nom et/ou catégorie en base. */
    public List<Medicament> filterBy(String nomFilter, String catFilter) {
        return dao.findByNameOrCategory(nomFilter, catFilter);
    }

    /** Recherche un produit par son ID. */
    public Medicament findById(int id) {
        return dao.findById(id);
    }
}
