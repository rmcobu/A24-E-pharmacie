<%--
  Created by IntelliJ IDEA.
  User: karim
  Date: 2025-06-16
  Time: 1:12 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Commande - Pharmacy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="d-flex flex-column min-vh-100">

<!--  NAVBAR  -->
<nav class="navbar navbar-light bg-white border-bottom">
    <div class="container-fluid px-4 d-flex justify-content-between align-items-center">
        <a class="navbar-brand d-flex align-items-center" href="accueil.jsp">
            <img src="images/pharmacy_logo.png" height="60" alt="Logo">
            <span class="ms-2 h4 text-success mb-0">PHARMACY</span>
        </a>
        <div class="ms-auto">
            <button class="btn btn-secondary me-2" onclick="location.href='catalogue.jsp'">Catalogue médicament</button>
            <button class="btn btn-secondary me-2" onclick="location.href='connexion.jsp'">Connexion</button>
            <button class="btn btn-secondary me-2" onclick="location.href='creationCompte.jsp'">S'inscrire</button>
        </div>
    </div>
</nav>

<!--  CONTENU -->
<div class="container my-5">
    <h3 class="text-center mb-4">🛒 Panier</h3>

    <!-- Liste des produits -->
    <table class="table table-bordered">
        <thead class="table-light text-center">
        <tr>
            <th>Produit</th>
            <th>Prix</th>
            <th>Quantité</th>
            <th>Supprimer</th>
        </tr>
        </thead>
        <tbody>
        <!-- Exemple statique, a remplacer par une itération sur les produits -->
        <tr>
            <td><img src="images/advil.png" width="70"/> ADVIL Tablets</td>
            <td class="text-danger">7.99 $</td>
            <td class="text-center">
                <button class="btn btn-sm btn-outline-secondary">+</button>
                1
                <button class="btn btn-sm btn-outline-secondary">-</button>
            </td>
            <td class="text-center"><button class="btn btn-outline-danger btn-sm">❌</button></td>
        </tr>
        </tbody>
    </table>

    <!-- Totaux -->
    <div class="row my-4">
        <div class="col-md-6 offset-md-6">
            <table class="table">
                <tr><th>Sous-total :</th><td>16.08 $</td></tr>
                <tr><th>TPS :</th><td>0.80 $</td></tr>
                <tr><th>TVQ :</th><td>1.60 $</td></tr>
                <tr><th>Total :</th><td><strong>18.48 $</strong></td></tr>
            </table>
        </div>
    </div>

    <!-- Formulaire commande -->
    <form action="commande" method="post">
        <div class="mb-4">
            <h5>Mode de retrait :</h5>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="modeRetrait" value="livraison" required>
                <label class="form-check-label">Livraison</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="modeRetrait" value="ramassage">
                <label class="form-check-label">Ramassage</label>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-4"><input type="text" name="adresse" class="form-control" placeholder="Adresse" required></div>
            <div class="col-md-4"><input type="text" name="ville" class="form-control" placeholder="Ville" required></div>
            <div class="col-md-4"><input type="text" name="codePostal" class="form-control" placeholder="Code postal" required></div>
        </div>

        <div class="mb-4">
            <h5>Mode de paiement :</h5>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="modePaiement" value="comptant" required>
                <label class="form-check-label">Comptant</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="modePaiement" value="carte">
                <label class="form-check-label">Par carte</label>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-6"><input type="text" class="form-control" name="carte" placeholder="Numéro carte de crédit"></div>
            <div class="col-md-3"><input type="text" class="form-control" name="expiration" placeholder="MM / YY"></div>
            <div class="col-md-3"><input type="text" class="form-control" name="cvv" placeholder="CVV"></div>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-success px-5">Passer commande</button>
        </div>
    </form>
</div>

<!-- FOOTER -->
<footer class="bg-success text-white py-3 mt-auto">
    <div class="container d-flex justify-content-between">
        <span>Numéro contact</span>
        <span>Adresse</span>
    </div>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
