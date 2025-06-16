package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.ClientDAO;
import model.Client;
import java.util.Optional;


public class AuthService {

    private final ClientDAO clientDao;

    public AuthService(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    public Optional<Client> authenticate(String email, String plainPassword) {
        return clientDao.findByEmail(email)
                .filter(client -> BCrypt.verifyer()
                        .verify(plainPassword.toCharArray(), client.getPassword())
                        .verified);
    }

}
