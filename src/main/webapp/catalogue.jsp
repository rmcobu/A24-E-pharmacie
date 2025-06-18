<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Catalogue - Pharmacy</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="d-flex flex-column min-vh-100">

<!-- Navbar commune -->
<nav class="navbar navbar-light bg-white border-bottom">
    <div class="container-fluid px-4 d-flex justify-content-between align-items-center">
        <a class="navbar-brand d-flex align-items-center" href="index.jsp">
            <img src="images/pharmacy_logo.png" height="60" alt="Logo">
            <span class="ms-2 h4 text-success mb-0">PHARMACY</span>
        </a>
        <div class="ms-auto">
            <button class="btn btn-secondary me-2" href="${pageContext.request.contextPath}/catalogue">Catalogue médicament</button>
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

<!-- Contenu principal -->
<section class="container my-5 flex-grow-1">
    <h2 class="text-center mb-4">Catalogue des Médicaments</h2>

    <!-- Barre de filtre -->
    <form method="get" action="catalogue" class="row g-3 mb-4">
        <div class="col-md-5">
            <input type="text" class="form-control" name="filtreNom" placeholder="Nom du médicament" value="${param.filtreNom}" />
        </div>
        <div class="col-md-5">
            <input type="text" class="form-control" name="filtreCategorie" placeholder="Catégorie" value="${param.filtreCategorie}" />
        </div>
        <div class="col-md-2 d-grid">
            <button type="submit" class="btn btn-outline-success">Filtrer</button>
        </div>
    </form>

    <!-- Tableau des médicaments -->
    <div class="row justify-content-center g-4">
        <c:forEach var="medicament" items="${medicaments}">
            <div class="col-12 col-sm-6 col-md-4 col-lg-3">
                <div class="card h-100 text-center">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${medicament.nom}</h5>
                        <p class="card-text text-danger fw-bold mb-3">
                                ${medicament.prix} €
                        </p>
                        <button class="btn btn-primary mt-auto">
                            Ajouter au panier
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>
        <c:if test="${ empty medicaments
             and (not empty param.filtreNom or not empty param.filtreCategorie) }">
            <div class="col-12">
                <p class="text-center text-muted">
                    Aucun médicament trouvé pour ces filtres.
                </p>
            </div>
        </c:if>
    </div>
</section>

<!-- Footer commun -->
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
