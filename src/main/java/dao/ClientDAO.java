package dao;
import model.Client;
import java.util.Optional;

public interface ClientDAO {

        void save(Client client);
        Optional<Client> findByEmail(String email);
}





