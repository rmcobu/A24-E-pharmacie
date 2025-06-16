package dal;
import jakarta.persistence.*;

public class EM_Fournisseur {

    private static EM_Fournisseur instance;
    private EntityManagerFactory factory;

    private EM_Fournisseur() {
        this.factory = Persistence.createEntityManagerFactory("E-pharmacie");
    }

    public static synchronized EM_Fournisseur getInstance() {
        if (instance == null) {
            instance = new EM_Fournisseur();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }


}
