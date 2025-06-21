package dao;

import java.util.List;
import model.Medicament;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import dal.EM_Fournisseur;

public class MedicamentDAO {
    public List<Medicament> findAll() {
        EntityManager em = EM_Fournisseur.getInstance().getEntityManager();
        TypedQuery<Medicament> q = em.createQuery("SELECT m FROM Medicament m", Medicament.class);
        List<Medicament> result = q.getResultList();
        em.close();
        return result;
    }

    public Medicament findById(int id) {
        EntityManager em = EM_Fournisseur.getInstance().getEntityManager();
        Medicament m = em.find(Medicament.class, id);
        em.close();
        return m;
    }

    public List<Medicament> findByNameOrCategory(String nomFilter, String catFilter) {
        EntityManager em = EM_Fournisseur.getInstance().getEntityManager();
        String jpql = "SELECT m FROM Medicament m WHERE "
                + "(:nom IS NULL OR LOWER(m.nom) LIKE LOWER(CONCAT('%',:nom,'%'))) "
                + "AND (:cat IS NULL OR LOWER(m.categorie) LIKE LOWER(CONCAT('%',:cat,'%')))";
        TypedQuery<Medicament> q = em.createQuery(jpql, Medicament.class);
        q.setParameter("nom", nomFilter != null && !nomFilter.isBlank() ? nomFilter : null);
        q.setParameter("cat", catFilter != null && !catFilter.isBlank() ? catFilter : null);
        List<Medicament> result = q.getResultList();
        em.close();
        return result;
    }
}

