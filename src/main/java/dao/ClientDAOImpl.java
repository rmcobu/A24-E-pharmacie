package dao;
import model.Client;
import dal.EM_Fournisseur;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import java.util.Optional;

@Transactional
public class ClientDAOImpl implements ClientDAO {

    @Override
    public void save(Client client) {
        EntityManager em = EM_Fournisseur.getInstance().getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(client);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        EntityManager em = EM_Fournisseur.getInstance().getEntityManager();
        try {
            Client c = em.createQuery(
                            "SELECT c FROM Client c WHERE c.email = :email", Client.class)
                    .setParameter("email", email)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
            return Optional.ofNullable(c);
        } finally {
            em.close();
        }
    }


}
