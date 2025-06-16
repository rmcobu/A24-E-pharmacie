package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.ClientDAO;
import model.Client;


public class ClientService {
    private final ClientDAO clientDao;

    public ClientService(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    public void register(Client client) {
        String hash = BCrypt.withDefaults()
                .hashToString(12, client.getPassword().toCharArray());
        client.setPassword(hash);
        clientDao.save(client);
    }
}
