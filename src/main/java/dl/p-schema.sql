CREATE TABLE Commande (
                          id_commande   INT            NOT NULL AUTO_INCREMENT,
                          id_client     INT            NOT NULL,
                          id_paiement   INT            DEFAULT NULL,
                          etat          BOOLEAN        NOT NULL COMMENT 'false=à payer, true=vendu',
                          date_commande DATETIME       NOT NULL,
                          mode_paiement BOOLEAN        NOT NULL COMMENT 'false=comptant, true=carte',
                          mode_retrait  BOOLEAN        NOT NULL COMMENT 'false=ramassage, true=livraison',
                          adresse       VARCHAR(255)   NOT NULL,
                          items         JSON           NOT NULL COMMENT 'liste des {id,nom,prix,quantite}’s',
                          sous_total    DECIMAL(10,2)  NOT NULL,
                          tps           DECIMAL(10,2)  NOT NULL,
                          tvq           DECIMAL(10,2)  NOT NULL,
                          total         DECIMAL(10,2)  NOT NULL,
                          PRIMARY KEY (`id_commande`),
                          CONSTRAINT `FK_Commande_Client`
                          FOREIGN KEY (`id_client`)
                          REFERENCES `Client`(`id_client`)
                          ON UPDATE CASCADE
                          ON DELETE RESTRICT
);


CREATE TABLE Client (
                      id_client INT PRIMARY KEY AUTO_INCREMENT,
                      nom VARCHAR(255) NOT NULL,
                      prenom VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      telephone VARCHAR(255) NOT NULL,
                      numero_assurance_maladie VARCHAR(255)
);

