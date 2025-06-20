<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Confirmation de commande - Pharmacy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <style>
        .cart-item-image { width: 80px; height: 80px; object-fit: contain; }
        .order-summary { margin-top: 2rem; }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">

<!-- Navbar -->
<nav class="navbar navbar-light bg-white border-bottom">
    <div class="container-fluid px-4 d-flex justify-content-between align-items-center">
        <a class="navbar-brand d-flex align-items-center" href="accueil.jsp">
            <img src="images/pharmacy_logo.png" height="60" alt="Logo">
            <span class="ms-2 h4 text-success mb-0">PHARMACY</span>
        </a>
        <div class="ms-auto">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <form action="logout" method="get" class="d-inline">
                        <button type="submit" class="btn btn-secondary me-2">Déconnexion</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <a href="connexion.jsp" class="btn btn-secondary me-2">Connexion</a>
                    <a href="creationCompte.jsp" class="btn btn-secondary">S'inscrire</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<!-- Contenu principal -->
<section class="container my-5 flex-grow-1">
    <h2 class="text-center mb-4">Merci pour votre commande</h2>

    <c:set var="panier" value="${sessionScope.confirmationPanier}"/>
    <c:set var="commande" value="${sessionScope.confirmationCommande}"/>

    <p><strong>Total :</strong>
        <fmt:formatNumber value="${commande.total}"
                          type="number" minFractionDigits="2" maxFractionDigits="2"/> $
    </p>
    <p><strong>Date :</strong>
        <fmt:formatDate value="${commande.dateCommandeAsDate}"
                        pattern="dd MMM yyyy 'à' HH:mm"/>
    </p>

    <c:choose>
        <c:when test="${commande.modeRetrait}">
            <p><strong>Livraison à :</strong> ${commande.adresse}</p>
        </c:when>
        <c:otherwise>
            <p><strong>Ramassage à :</strong> 123 Rue de la Pharmacie, Montreal</p>
        </c:otherwise>
    </c:choose>

    <h4>Produits :</h4>
    <div class="list-group">
        <c:forEach var="entry" items="${sessionScope.confirmationPanier.items.entrySet()}">
            <div class="list-group-item d-flex align-items-center">
                <img src="images/${entry.key.image}"
                     class="me-3" style="width:80px;height:80px;object-fit:contain"
                     alt="${entry.key.nom}" />
                <div class="flex-grow-1">
                        ${entry.key.nom} &times; ${entry.value}
                </div>
                <div class="text-danger ms-auto">
                    <fmt:formatNumber
                            value="${entry.key.prix * entry.value}"
                            type="number"
                            minFractionDigits="2"
                            maxFractionDigits="2"/> $
                </div>
            </div>
        </c:forEach>
        <c:if test="${empty sessionScope.confirmationPanier.items}">
            <div class="list-group-item text-center text-muted">
                Aucun produit.
            </div>
        </c:if>
    </div>
</section>

<!-- Footer -->
<footer class="bg-success text-white py-3 mt-auto">
    <div class="container d-flex justify-content-between">
        <span><i class="bi bi-telephone"></i> 01 23 45 67 89</span>
        <span><i class="bi bi-geo-alt"></i> 123 Rue de la Pharmacie, Montreal</span>
    </div>
</footer>

<!-- Panier Modal (identique à catalogue.jsp) -->
<div class="modal fade" id="panierModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <!-- … contenu identique à ton Modal Panier … -->
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
