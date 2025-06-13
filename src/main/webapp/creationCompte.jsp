<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Créer un compte - Pharmacy</n></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="d-flex flex-column min-vh-100">
<nav class="navbar navbar-light bg-white border-bottom">
    <div class="container">
        <a class="navbar-brand d-flex align-items-center" href="index.jsp">
            <img src="images/pharmacy_logo.png" height="60" alt="Logo">
            <span class="ms-2 h4 text-success mb-0">PHARMACY</span>
        </a>
        <div>
            <button class="btn btn-secondary" onclick="location.href='index.jsp'">Accueil</button>
        </div>
    </div>
</nav>

<div class="container flex-grow-1 d-flex align-items-center justify-content-center">
    <div class="w-50">
        <h2 class="mb-4 text-center">Créer un compte</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <form action="register" method="post">
            <div class="row mb-3 align-items-center">
                <div class="col-4">
                    <span class="d-block px-3 py-2 bg-success text-white rounded">Nom :</span>
                </div>
                <div class="col-8">
                    <input type="text" name="nom" class="form-control" required/>
                </div>
            </div>
            <div class="row mb-3 align-items-center">
                <div class="col-4">
                    <span class="d-block px-3 py-2 bg-success text-white rounded">Prénom :</span>
                </div>
                <div class="col-8">
                    <input type="text" name="prenom" class="form-control" required/>
                </div>
            </div>
            <div class="row mb-3 align-items-center">
                <div class="col-4">
                    <span class="d-block px-3 py-2 bg-success text-white rounded">Téléphone :</span>
                </div>
                <div class="col-8">
                    <input type="tel" name="telephone" class="form-control" required/>
                </div>
            </div>
            <div class="row mb-3 align-items-center">
                <div class="col-4">
                    <span class="d-block px-3 py-2 bg-success text-white rounded">Votre Email :</span>
                </div>
                <div class="col-8">
                    <input type="email" name="email" class="form-control" required/>
                </div>
            </div>
            <div class="row mb-3 align-items-center">
                <div class="col-4">
                    <span class="d-block px-3 py-2 bg-success text-white rounded">Mot de Passe :</span>
                </div>
                <div class="col-8">
                    <input type="password" name="password" class="form-control" required/>
                </div>
            </div>
            <div class="row mb-4 align-items-center">
                <div class="col-4">
                    <span class="d-block px-3 py-2 bg-success text-white rounded">Numéro Assurance Maladie :</span>
                </div>
                <div class="col-8">
                    <input type="text" name="numeroAssuranceMaladie" class="form-control"/>
                </div>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary px-5">S'inscrire</button>
            </div>
        </form>
    </div>
</div>

<footer class="bg-success text-white py-3 mt-auto">
    <div class="container d-flex justify-content-between">
        <span>Numéro contact</span>
        <span>Adresse</span>
    </div>
</footer>
</body>
</html>
