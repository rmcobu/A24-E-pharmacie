package dao;

import model.Medicament;
import service.MedicamentService;

import java.util.List;

public class MedicamentDAOImpl {

    private MedicamentService medicamentService = new MedicamentService();

    public List<Medicament> getCatalogue() {
        return medicamentService.getCatalogue();
    }
}
