package service;

import model.Medicament;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class MedicamentService {

    private EntityManagerFactory emf;

    public MedicamentService() {
        // Le nom ici doit correspondre à celui dans ton fichier persistence.xml
        emf = Persistence.createEntityManagerFactory("E-pharmacie");
    }

    public List<Medicament> getCatalogue() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Medicament m", Medicament.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Medicament> filterBy(String nomFilter, String catFilter) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT m FROM Medicament m WHERE " +
                    "(:nom IS NULL OR LOWER(m.nom) LIKE LOWER(CONCAT('%', :nom, '%'))) AND " +
                    "(:cat IS NULL OR LOWER(m.categorie) LIKE LOWER(CONCAT('%', :cat, '%')))";
            TypedQuery<Medicament> query = em.createQuery(jpql, Medicament.class);
            query.setParameter("nom", nomFilter == null || nomFilter.isBlank() ? null : nomFilter);
            query.setParameter("cat", catFilter == null || catFilter.isBlank() ? null : catFilter);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Medicament findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Medicament.class, id);
        } finally {
            em.close();
        }
    }
}
