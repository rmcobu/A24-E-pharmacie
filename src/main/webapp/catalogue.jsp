<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Catalogue - Pharmacy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <style>
        .search-filter-bar {
            background-color: #e9ecef;
            padding: 12px 20px;
            border-radius: 6px;
            display: flex;
            align-items: center;
            gap: 15px;
            margin-bottom: 25px;
        }
        .search-box {
            flex: 1;
            position: relative;
        }
        .search-box input {
            padding-left: 35px;
            border-radius: 4px;
            border: 1px solid #ced4da;
            height: 38px;
            width: 100%;
        }
        .search-box i {
            position: absolute;
            left: 10px;
            top: 10px;
            color: #6c757d;
        }
        .filter-box {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .filter-box .form-select {
            border-radius: 4px;
            height: 38px;
            min-width: 160px;
            font-size: 14px;
        }
        .cursor-pointer {
            cursor: pointer;
        }
        .card:hover {
            transform: translateY(-5px);
            transition: transform 0.3s ease;
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
        }
        .cart-item-image {
            width: 60px;
            height: 60px;
            object-fit: contain;
        }
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
            <button type="button" class="btn btn-outline-success position-relative me-2"
                    data-bs-toggle="modal" data-bs-target="#panierModal">
                <i class="bi bi-cart3"></i>
                <span id="cartBadge" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                    ${not empty sessionScope.panier ? sessionScope.panier.totalItemsCount : 0}
                </span>
            </button>
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <form action="logout" method="get" class="d-inline">
                        <button type="submit" class="btn btn-secondary me-2">Déconnexion</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <a href="connexion.jsp" class="btn btn-secondary me-2">Connexion</a>
                    <a href="creationCompte.jsp" class="btn btn-secondary me-2">S'inscrire</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<!-- Contenu principal -->
<section class="container my-5 flex-grow-1">
    <h2 class="text-center mb-4">Catalogue des Médicaments</h2>

    <!-- Barre de recherche/filtre -->
    <form method="get" action="catalogue" class="search-filter-bar">
        <div class="search-box">
            <i class="bi bi-search"></i>
            <input type="text" class="form-control" name="filtreNom"
                   placeholder="Rechercher un produit..."
                   value="${param.filtreNom}">
        </div>
        <div class="filter-box">
            <select class="form-select" name="filtreCategorie">
                <option value="">Toutes catégories</option>
                <option value="Médicaments" ${param.filtreCategorie eq 'Médicaments' ? 'selected' : ''}>Médicaments</option>
                <option value="Hygiène" ${param.filtreCategorie eq 'Hygiène' ? 'selected' : ''}>Hygiène</option>
                <option value="Vitamine" ${param.filtreCategorie eq 'Vitamine' ? 'selected' : ''}>Vitamines</option>
            </select>
            <select class="form-select" name="tri">
                <option value="">Trier par</option>
                <option value="prix-asc" ${param.tri eq 'prix-asc' ? 'selected' : ''}>Prix croissant</option>
                <option value="prix-desc" ${param.tri eq 'prix-desc' ? 'selected' : ''}>Prix décroissant</option>
            </select>
            <button type="submit" class="btn btn-outline-success">Filtrer</button>
        </div>
    </form>

    <!-- Message -->
    <c:if test="${not empty requestScope.message}">
        <div class="alert alert-${requestScope.typeMessage} alert-dismissible fade show" role="alert">
                ${requestScope.message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <!-- Liste des produits -->
    <div class="row justify-content-center g-4">
        <c:forEach var="medicament" items="${medicaments}">
            <div class="col-12 col-sm-6 col-md-4 col-lg-3">
                <div class="card h-100 text-center cursor-pointer" data-bs-toggle="modal" data-bs-target="#medicamentModal"
                     onclick="showMedicamentDetails('${medicament.id}', '${medicament.nom}', '${medicament.description}', '${medicament.categorie}', '${medicament.prix}', '${medicament.image}')">
                    <img src="images/${medicament.image}" class="card-img-top p-3" alt="${medicament.nom}" style="height: 200px; object-fit: contain;">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${medicament.nom}</h5>
                        <p class="card-text text-danger fw-bold mb-3">${medicament.prix} $</p>
                        <form action="panier" method="post" class="mt-auto">
                            <input type="hidden" name="action" value="ajouter">
                            <input type="hidden" name="id" value="${medicament.id}">
                            <button type="submit" class="btn btn-primary w-100">
                                <i class="bi bi-cart-plus"></i> Ajouter
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
        <c:if test="${empty medicaments and (not empty param.filtreNom or not empty param.filtreCategorie)}">
            <div class="col-12">
                <div class="alert alert-warning text-center" role="alert">
                    <!-- Si buscas por nombre, muéstralo -->
                    <c:choose>
                        <c:when test="${not empty param.filtreNom}">
                            Aucun médicament trouvé pour « ${param.filtreNom} ».
                        </c:when>
                        <c:otherwise>
                            Aucun médicament trouvé pour la catégorie « ${param.filtreCategorie} ».
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:if>
    </div>
</section>

<!-- Modal Panier -->
<div class="modal fade" id="panierModal" tabindex="-1" aria-labelledby="panierModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title">Contenu de votre panier</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <c:choose>
                    <c:when test="${not empty sessionScope.panier and not empty sessionScope.panier.items}">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Produit</th><th>Prix unitaire</th><th>Quantité</th><th>Total</th><th>Actions</th>
                                </tr>
                                </thead>
                                <!-- Hemos añadido un ID al tbody para seleccionarlo más fácilmente -->
                                <tbody id="cartTbody">
                                <c:forEach var="entry" items="${sessionScope.panier.items.entrySet()}">
                                    <tr>
                                        <td>${entry.key.nom}</td>
                                        <td>${entry.key.prix} $</td>
                                        <td>${entry.value}</td>
                                        <td>${entry.key.prix * entry.value} $</td>
                                        <td>
                                            <!-- Botones con la clase común cart-btn y data-action -->
                                            <button type="button" class="btn btn-sm btn-outline-secondary cart-btn"
                                                    data-action="supprimer" data-id="${entry.key.id}">−</button>
                                            <button type="button" class="btn btn-sm btn-outline-success cart-btn"
                                                    data-action="ajouter" data-id="${entry.key.id}">+</button>
                                            <button type="button" class="btn btn-sm btn-outline-danger cart-btn"
                                                    data-action="supprimerTout" data-id="${entry.key.id}">🗑</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr class="table-active">
                                    <td colspan="3" class="text-end fw-bold">Total</td>
                                    <td colspan="2" class="fw-bold">${sessionScope.panier.total} $</td>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <div class="text-end">
                            <button class="btn btn-outline-danger cart-btn" data-action="vider" data-id="0">
                                Vider le panier
                            </button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="text-center py-4">
                            <i class="bi bi-cart-x" style="font-size:3rem;color:#6c757d;"></i>
                            <h5 class="mt-3">Votre panier est vide</h5>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    Continuer mes achats
                </button>
                <c:if test="${not empty sessionScope.panier and not empty sessionScope.panier.items}">
                    <a href="commande" class="btn btn-success">Commander</a>
                </c:if>
            </div>
        </div>
    </div>
</div>


<!-- Modal Médicament -->
<div class="modal fade" id="medicamentModal" tabindex="-1" aria-labelledby="medicamentModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="medicamentModalLabel">Détails du médicament</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fermer"></button>
            </div>
            <div class="modal-body">
                <div class="text-center mb-4">
                    <img id="modalMedicamentImage" src="" class="img-fluid rounded" alt="Médicament" style="max-height: 200px;">
                </div>
                <h4 id="modalMedicamentName" class="text-center mb-3"></h4>
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>Catégorie:</strong> <span id="modalMedicamentCategory"></span></p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Prix:</strong> <span id="modalMedicamentPrice" class="text-danger fw-bold"></span></p>
                    </div>
                </div>
                <h5>Description:</h5>
                <p id="modalMedicamentDescription" class="text-muted"></p>
            </div>
            <div class="modal-footer">
                <form action="panier" method="post" class="d-inline">
                    <input type="hidden" name="action" value="ajouter">
                    <input type="hidden" id="modalMedicamentId" name="id" value="">
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-cart-plus"></i> Ajouter au panier
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- Footer -->
<footer class="bg-success text-white py-3 mt-auto">
    <div class="container d-flex justify-content-between">
        <span><i class="bi bi-telephone"></i> 01 23 45 67 89</span>
        <span><i class="bi bi-geo-alt"></i> 123 Rue de la Pharmacie, 75000 Paris</span>
    </div>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


<script>

    const ctx = '${pageContext.request.contextPath}';

    function updateCart(action, id) {
        console.log('updateCart', action, id);
        fetch(`${ctx}/panier`, {
            method: 'POST',
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({ action, id })
        })
            .then(r => {
                console.log('Fetch status:', r.status);
                if (!r.ok) throw new Error(r.statusText);
                return r.json();
            })
            .then(renderCart)
            .catch(err => console.error('Erreur panier:', err));
    }


    function renderCart(json) {
        document.getElementById('cartBadge').textContent = json.totalItemsCount;
        const tbody = document.getElementById('cartTbody');
        tbody.innerHTML = '';
        json.items.forEach(item => {
            const tr = document.createElement('tr');
            tr.innerHTML =
                '<td>' + item.nom + '</td>' +
                '<td>' + item.prix + ' $</td>' +
                '<td>' + item.quantite + '</td>' +
                '<td>' + (item.prix * item.quantite) + ' $</td>' +
                '<td>' +
                '<button type="button" class="btn btn-sm btn-outline-secondary cart-btn" ' +
                'data-action="supprimer" data-id="' + item.id + '">−</button> ' +
                '<button type="button" class="btn btn-sm btn-outline-success   cart-btn" ' +
                'data-action="ajouter"  data-id="' + item.id + '">+</button> ' +
                '<button type="button" class="btn btn-sm btn-outline-danger    cart-btn" ' +
                'data-action="supprimerTout" data-id="' + item.id + '">🗑</button>' +
                '</td>';
            tbody.appendChild(tr);
        });
        const totalCell = document.querySelector('#panierModal tfoot .fw-bold:last-child');
        if (totalCell) totalCell.textContent = json.total + ' $';
    }

    document.addEventListener('DOMContentLoaded', () => {
        console.log('Listener ready');
        const modal = document.getElementById('panierModal');
        modal.addEventListener('click', e => {
            const btn = e.target.closest('.cart-btn');
            if (!btn) return;
            e.preventDefault();
            updateCart(btn.dataset.action, btn.dataset.id);
        });
    });


    function showMedicamentDetails(id, name, description, category, price, image) {
        document.getElementById('modalMedicamentName').textContent        = name;
        document.getElementById('modalMedicamentDescription').textContent = description;
        document.getElementById('modalMedicamentCategory').textContent    = category;
        document.getElementById('modalMedicamentPrice').textContent       = price + ' $';
        document.getElementById('modalMedicamentImage').src               = 'images/' + image;
        document.getElementById('modalMedicamentId').value               = id;
    }
</script>

</body>
</html>
