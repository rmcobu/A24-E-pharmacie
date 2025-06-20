package dao;

import model.Medicament;
import service.MedicamentService;

import java.util.List;

public class MedicamentDAOImpl {

    private MedicamentService medicamentService = new MedicamentService();

    public List<Medicament> getCatalogue() {
        return medicamentService.getCatalogue();
    }

    public List<Medicament> filterBy(String nom, String categorie) {
        return medicamentService.filterBy(nom, categorie);
    }

    public Medicament findById(int id) {
        return medicamentService.findById(id);
    }
}
