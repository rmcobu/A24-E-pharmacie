# E-Pharmacie

Cette application web permet de commander des produits d’une pharmacie en ligne, incluant les catégories suivantes : médicaments, produits d’hygiène et vitamines.
Les utilisateurs peuvent demander la préparation de leur commande et choisir de la récupérer en personne ou de se la faire livrer à domicile.
Le paiement peut être effectué soit par carte bancaire, soit en espèces au moment de la récupération ou de la livraison.

Une application de commande en ligne pour une pharmacie, développée en **Java EE** avec **Servlets**, **JSP**, **JPA/Hibernate**, **MariaDB**, et **Bootstrap**.

---

## Table des matières

- [Description](#description)  
- [Fonctionnalités](#fonctionnalités)  
- [Technologies & Bibliothèques](#technologies--bibliothèques)  
- [Architecture & Diagramme de paquets](#architecture--diagramme-de-paquets)  
- [Installation & Configuration](#installation--configuration)  
  - [Prérequis](#prérequis)  
  - [Base de données](#base-de-données)  
  - [Configuration JPA](#configuration-jpa)  
  - [Déploiement](#déploiement)  
- [Guide d’utilisation](#guide-dutilisation)  
- [Tests unitaires](#tests-unitaires)  


---

## Description

E-Pharmacie est une application web qui permet à un client de :

1. Parcourir un **catalogue** de médicaments.  
2. Gérer un **panier** : ajouter, retirer, vider.  
3. Passer une **commande** en choisissant :
   - un **mode de retrait** (livraison / ramassage)  
   - un **mode de paiement** (comptant / carte)  
4. Consulter une **page de confirmation** détaillée (date+heure, adresse, montant, produits).

---

## Fonctionnalités

- **Catalogue dynamique** (recherche, filtre, tri).  
- **Panier AJAX** : mise à jour sans rechargement (+, –, ❌, vider).  
- **Formulaire de commande** avec sections conditionnelles (JavaScript).  
- **Persistance JPA** : clients, médicaments, commandes (JSON pour les lignes).  
- **Interface responsive** avec Bootstrap 5.  

---

## Technologies & Bibliothèques

| Composant                | Version / Tech                           |
|--------------------------|------------------------------------------|
| Java EE                  | Jakarta Servlet API, JSP                 |
| ORM                      | JPA / Hibernate                          |
| Base de données          | MariaDB (hébergé sur AlwaysData)         |
| Front-end                | HTML5, CSS3, Bootstrap 5, JS (Fetch API) |
| Tests                    | JUnit 5                                  |

---

## Architecture & Diagramme de paquets

```text
controller    ←–– Servlets (Catalogue, Panier, Commande, Inscription, Connexion…)
  ↳ dépend de jakarta.servlet.http.HttpServlet

service       ←–– Logique métier (AuthService, MedicamentService, CommandeService)

dao           ←–– Accès aux données (ClientDAO, MedicamentDAO, CommandeDAO)
  ↳ façade DAO utilisée par `service`

model         ←–– Entités JPA (Client, Medicament, Commande) + DTO panier

dal           ←–– EM_Fournisseur : singleton EntityManagerFactory

webapp/       ←–– JSP + ressources statiques (Bootstrap, images, JS)

    Controller : points d’entrée HTTP.

    Service : logique métier, orchestration des DAO.

    DAO : persistance via JPA/Hibernate.

    Model : entités JPA + classe Panier en mémoire.

    DAL : fournisseur d’EntityManager.
	
	
PERSISTANCE: Les données pour acceder a la base de données se trouvent dans le document persistence.xml 
```

## Installation & Configuration
Prérequis

    Java 11+

    Maven

    Tomcat 10.1.39

    MariaDB

## Base de données

    Créer une base nommée e_pharmacie.

    Exécuter les scripts SQL fournis pour créer les tables :

        Client, Medicament, Commande

    Insérer des enregistrements de test dans Medicament.


Les données de persistance se trouvent dans le document persis

## Déploiement

Avec Maven :

mvn clean package


# Déployer le WAR généré

Guide d’utilisation

    Inscription/Connexion

    Catalogue → filtrer / trier

    Ajouter des médicaments au panier

    Panier (icône) → vérifier / modifier

    Commande → choisir retrait & paiement → confirmer

    Confirmation → résumé détaillé

Tests unitaires

    model/PanierTest : gestion du panier, calcul des totaux (BigDecimal).

    service/CommandeServiceTest : création et initialisation de Commande.

    dao :

        MedicamentDAOTest

        CommandeDAOTest

## Exécution :

mvn test

