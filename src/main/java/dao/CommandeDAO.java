package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Commande;

public class CommandeDAO {

    private EntityManager entityManager;

    public CommandeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean save(Commande commande) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(commande);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // à remplacer par logger en prod
            return false;
        }
    }

    // Autres méthodes utiles (optionnelles)
    public Commande findById(Integer id) {
        return entityManager.find(Commande.class, id);
    }

    public void delete(Commande commande) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(commande) ? commande : entityManager.merge(commande));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }
}
