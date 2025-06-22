package model;

import jakarta.persistence.*;

    @Entity
    @Table(name = "Client",
            uniqueConstraints = @UniqueConstraint(columnNames = "email"))
    public class Client {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_client")
        private Integer id_client;

        @Column(nullable = false)
        private String nom;

        @Column(nullable = false)
        private String prenom;

        @Column(nullable = false)
        private String telephone;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String password;

        @Column
        private String numero_assurance_maladie;

        public Client() {}

        // Getters et Setters


        public Integer getIdClient() {
            return id_client;
        }

        public void setIdClient(Integer idClient) {
            this.id_client = idClient;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNumeroAssuranceMaladie() {
            return numero_assurance_maladie;
        }

        public void setNumeroAssuranceMaladie(String numeroAssuranceMaladie) {
            this.numero_assurance_maladie = numeroAssuranceMaladie;
        }
    }

