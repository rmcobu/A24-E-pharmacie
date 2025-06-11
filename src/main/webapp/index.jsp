<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil - Pharmacy</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
        }
        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px 30px;
            background-color: #fff;
            border-bottom: 1px solid #ccc;
        }
        .logo {
            display: flex;
            align-items: center;
        }
        .logo img {
            height: 60px;
        }
        .logo span {
            font-size: 28px;
            font-weight: bold;
            color: green;
            margin-left: 10px;
        }
        .nav {
            display: flex;
            gap: 10px;
        }
        .nav button {
            padding: 10px 15px;
            border-radius: 10px;
            border: none;
            background-color: #eee;
            cursor: pointer;
        }
        .search-bar {
            display: flex;
            justify-content: center;
            margin: 30px;
        }
        .search-bar input {
            width: 400px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .featured {
            text-align: center;
            font-weight: bold;
            margin-top: -20px;
        }
        .products {
            display: flex;
            justify-content: center;
            gap: 50px;
            margin-top: 20px;
        }
        .product {
            text-align: center;
        }
        .product img {
            height: 120px;
        }
        .product .price {
            color: red;
            margin: 10px 0;
            font-size: 18px;
        }
        .product button {
            background-color: #2196F3;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
        }
        .footer {
            background-color: green;
            color: white;
            display: flex;
            justify-content: space-around;
            padding: 20px 0;
            margin-top: 50px;
        }
    </style>
</head>
<body>

<div class="header">
    <div class="logo">
        <img src="images/pharmacy_logo.png" alt="Logo">
        <span>PHARMACY</span>
    </div>
    <div class="nav">
        <button onclick="location.href='catalogue.jsp'">Catalogue médicament</button>
        <button onclick="location.href='connexion.jsp'">Connexion</button>
        <button onclick="location.href='inscription.jsp'">S’inscrire</button>
    </div>
</div>

<div class="search-bar">
    <input type="text" placeholder="Search">
</div>
<div class="featured">
    Produit en vedette
</div>

<div class="products">
    <div class="product">
        <img src="images/advil.jpg" alt="Advil">
        <div class="price">7.99 $</div>
        <button>Ajouter au panier</button>
    </div>
    <div class="product">
        <img src="images/tylenol.jpg" alt="Tylenol">
        <div class="price">8.09 $</div>
        <button>Ajouter au panier</button>
    </div>
</div>

<div class="footer">
    <div>Numéro contact</div>
    <div>Adresse</div>
</div>

</body>
</html>
