<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Accueil - Pharmacy</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>
<body class="d-flex flex-column min-vh-100">

<!-- Navbar commune -->
<nav class="navbar navbar-light bg-white border-bottom">
    <div class="container-fluid px-4 d-flex justify-content-between align-items-center">
        <a class="navbar-brand d-flex align-items-center" href="accueil.jsp">
            <img src="images/pharmacy_logo.png" height="60" alt="Logo">
            <span class="ms-2 h4 text-success mb-0">PHARMACY</span>
        </a>
        <div class="ms-auto">
            <button class="btn btn-secondary me-2" onclick="location.href='/catalogue'">Catalogue médicament</button>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <form action="logout" method="get" class="d-inline">
                    <button type="submit" class="btn btn-secondary me-2">Déconnexion</button>
                </form>
            </c:when>
            <c:otherwise>
            <button class="btn btn-secondary me-2" onclick="location.href='connexion.jsp'">Connexion</button>
            <button class="btn btn-secondary me-2" onclick="location.href='creationCompte.jsp'">S'inscrire</button>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
</nav>

<!-- Message de bienvenue -->
<c:if test="${not empty sessionScope.user}">
    <div class="container mt-4">
        <h2>Bonjour, <c:out value="${sessionScope.user.prenom}" /> !</h2>
    </div>
</c:if>

<!-- Contenu principal -->
<section class="container my-5 flex-grow-1">


    <!-- Message de succès ou d'erreur (optionnel) -->
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- Produit en vedette -->
    <h2 class="text-center mb-4">Produits en vedette</h2>

    <div class="row justify-content-center g-4">
        <!-- Produit 1 -->
        <div class="col-12 col-sm-6 col-md-4 col-lg-3">
            <div class="card h-100 text-center">
                <img src="images/advil.jpg" class="card-img-top" alt="Advil">
                <div class="card-body d-flex flex-column">
                    <h5 class="card-title">Advil</h5>
                    <p class="card-text text-danger fw-bold mb-3">7.99 $</p>
                    <form action="/panier" method="post" class="d-inline">
                        <input type="hidden" name="action" value="ajouter">
                        <input type="hidden" name="id" value="2">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-cart-plus"></i> Ajouter au panier
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <!-- Produit 2 -->
        <div class="col-12 col-sm-6 col-md-4 col-lg-3">
            <div class="card h-100 text-center">
                <img src="images/tylenol.jpg" class="card-img-top" alt="Tylenol">
                <div class="card-body d-flex flex-column">
                    <h5 class="card-title">Tylenol</h5>
                    <p class="card-text text-danger fw-bold mb-3">8.09 $</p>
                    <form action="/panier" method="post" class="d-inline">
                        <input type="hidden" name="action" value="ajouter">
                        <input type="hidden" name="id" value="1">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-cart-plus"></i> Ajouter au panier
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer commun -->
<footer class="bg-success text-white py-3 mt-auto">
    <div class="container d-flex justify-content-between">
        <span><i class="bi bi-telephone"></i> 514 123 4567</span>
        <span><i class="bi bi-geo-alt"></i> 123 Rue de la Pharmacie, 75000 Montréal</span>
    </div>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>