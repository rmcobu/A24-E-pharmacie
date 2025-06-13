CREATE TABLE Commande (
                      id_commande INT PRIMARY KEY AUTO_INCREMENT,
                      id_client INT NOT NULL,
                      id_paiement INT DEFAULT NULL,
                      etat BOOLEAN NOT NULL,
                      date_commande DATETIME NOT NULL,
                      mode_paiement BOOLEAN NOT NULL,
                      mode_retrait BOOL NOT NULL,
                      adresse VARCHAR(255) NOT NULL
);


CREATE TABLE Client (
                      id_client INT PRIMARY KEY AUTO_INCREMENT,
                      nom VARCHAR(255) NOT NULL,
                      prenom VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      telephone VARCHAR(255) NOT NULL,
                      numero_assurance_maladie VARCHAR(255)
);