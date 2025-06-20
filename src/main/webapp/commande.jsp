<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Commande - Pharmacie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        .cart-item-image { width: 60px; height: 60px; object-fit: contain; }
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
            <button class="btn btn-secondary me-2" onclick="location.href='catalogue'">Catalogue</button>
            <button class="btn btn-secondary me-2" onclick="location.href='connexion.jsp'">Connexion</button>
            <button class="btn btn-secondary" onclick="location.href='creationCompte.jsp'">S'inscrire</button>
        </div>
    </div>
</nav>

<!-- Contenu principal -->
<section class="container my-5 flex-grow-1">
    <h3 class="text-center mb-4">🛒 Panier </h3>

    <!-- PANIER -->
    <div class="table-responsive mb-4">
        <table class="table align-middle text-center">
            <thead class="table-light">
            <tr>
                <th>Produit</th>
                <th>Prix unitaire</th>
                <th>Quantité</th>
                <th>Supprimer</th>
            </tr>
            </thead>
            <tbody id="cartTbody">
            <c:forEach var="entry" items="${panier.items.entrySet()}">
                <tr>
                    <!-- Image + qte -->
                    <td class="text-start">
                        <div class="d-flex align-items-center">
                            <img src="images/${entry.key.image}"
                                 class="cart-item-image me-3"
                                 alt="${entry.key.nom}">
                            <span>${entry.key.nom}</span>
                        </div>
                    </td>
                    <!-- Prix -->
                    <td class="text-danger">${entry.key.prix} $</td>
                    <!-- Boutons + / – -->
                    <td>
                        <button type="button"
                                class="btn btn-sm btn-outline-secondary cart-btn"
                                data-action="supprimer"
                                data-id="${entry.key.id}">−</button>
                        <span class="mx-2">${entry.value}</span>
                        <button type="button"
                                class="btn btn-sm btn-outline-success cart-btn"
                                data-action="ajouter"
                                data-id="${entry.key.id}">+</button>
                    </td>
                    <!-- Bouton effacer ligne -->
                    <td>
                        <button type="button"
                                class="btn btn-sm btn-outline-danger cart-btn"
                                data-action="supprimerTout"
                                data-id="${entry.key.id}">❌</button>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty panier.items}">
                <tr>
                    <td colspan="4" class="text-center">Votre panier est vide.</td>
                </tr>
            </c:if>
            </tbody>
            <tfoot>
            <!-- Sous-total -->
            <tr>
                <th colspan="3" class="text-end">Sous-total :</th>
                <td id="sousTotalCell">${panier.total} $</td>
            </tr>
            <!-- TPS 5% -->
            <tr>
                <th colspan="3" class="text-end">TPS (5 %) :</th>
                <td id="tpsCell">
                    <fmt:formatNumber
                            value="${panier.total * 0.05}"
                            type="number"
                            minFractionDigits="2"
                            maxFractionDigits="2"/> $
                </td>
            </tr>
            <!-- TVQ 9.975% -->
            <tr>
                <th colspan="3" class="text-end">TVQ (9.975 %) :</th>
                <td id="tvqCell">
                    <fmt:formatNumber
                            value="${panier.total * 0.09975}"
                            type="number"
                            minFractionDigits="2"
                            maxFractionDigits="2"/> $
                </td>
            </tr>
            <!-- TOTAL -->
            <tr class="table-active">
                <th colspan="3" class="text-end">TOTAL :</th>
                <td id="totalCell">
                    <fmt:formatNumber
                            value="${panier.total * 1.14975}"
                            type="number"
                            minFractionDigits="2"
                            maxFractionDigits="2"/> $
                </td>
            </tr>
            </tfoot>
        </table>
    </div>

    <!-- Formulaire de finalisation -->
    <form action="${pageContext.request.contextPath}/commande" method="post" id="commandeForm">
        <input type="hidden" name="modeRetraitBool" id="modeRetraitBool" value="">
        <input type="hidden" name="modePaiementBool" id="modePaiementBool" value="">
        <input type="hidden" name="idPaiementTemp"  id="idPaiementTemp"  value="">

        <div class="row g-4">

            <!-- MODE DE RETRAIT -->
            <div class="col-12">
                <label class="form-label">Mode de retrait</label><br>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="modeRetrait" id="optLivraison"  value="livraison" required>
                    <label class="form-check-label" for="optLivraison">Livraison</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="modeRetrait" id="optRamassage"  value="ramassage">
                    <label class="form-check-label" for="optRamassage">Ramassage</label>
                </div>
            </div>

            <!-- LIVRAISON BLOCK -->
            <div class="col-md-6" id="blocLivraison" style="display:none;">
                <label for="adresse" class="form-label">Adresse</label>
                <input type="text" id="adresse" name="adresse" class="form-control">
            </div>
            <div class="col-md-3" id="blocVille" style="display:none;">
                <label for="ville" class="form-label">Ville</label>
                <input type="text" id="ville" name="ville" class="form-control">
            </div>
            <div class="col-md-3" id="blocCP" style="display:none;">
                <label for="codePostal" class="form-label">Code Postal</label>
                <input type="text" id="codePostal" name="codePostal" class="form-control">
            </div>

            <!-- RAMASSAGE BLOCK -->
            <div class="col-12 alert alert-info" id="blocRamassage" style="display:none;">
                <strong>Adresse de la pharmacie :</strong> 123 Rue de la Pharmacie, 75000 Paris<br>
                <strong>Heures d'ouverture :</strong> 9h00 – 19h00
            </div>

            <!-- MODE DE PAIEMENT -->
            <div class="col-12">
                <label class="form-label">Mode de paiement</label><br>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="modePaiement" id="optComptant" value="comptant" required>
                    <label class="form-check-label" for="optComptant">Comptant</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="modePaiement" id="optCarte"    value="carte">
                    <label class="form-check-label" for="optCarte">Par carte</label>
                </div>
            </div>

            <!-- COMPTANT MESSAGE -->
            <div class="col-12 alert alert-warning" id="msgComptant" style="display:none;">
                Vous paierez à la livraison ou en boutique.
            </div>

            <!-- CARTE BLOCK -->
            <div class="col-md-6" id="blocCarte" style="display:none;">
                <label for="numCarte" class="form-label">Numéro de carte</label>
                <input type="text" id="numCarte" name="numCarte" class="form-control" placeholder="XXXX XXXX XXXX XXXX">
            </div>
            <div class="col-md-3" id="blocExp" style="display:none;">
                <label for="expiration" class="form-label">MM/AA</label>
                <input type="text" id="expiration" name="expiration" class="form-control">
            </div>
            <div class="col-md-3" id="blocCVV" style="display:none;">
                <label for="cvv" class="form-label">CVV</label>
                <input type="text" id="cvv" name="cvv" class="form-control">
            </div>
            <div class="col-12" id="blocAjoutCarte" style="display:none;">
                <button type="button" class="btn btn-outline-primary" id="btnAjouterCarte">Ajouter carte</button>
                <span id="carteAjoutee" class="ms-3 text-success"></span>
            </div>

        </div>

        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary px-5">Passer commande</button>
        </div>
    </form>
</section>

<!-- Footer  -->
<footer class="bg-success text-white py-3 mt-auto">
    <div class="container d-flex justify-content-between">
        <span>Numéro contact</span>
        <span>Adresse</span>
    </div>
</footer>


<script>
    const ctx = '${pageContext.request.contextPath}';
    function updateCart(action, id) {
        fetch(`${ctx}/panier`, {
            method:'POST',
            headers:{ 'X-Requested-With':'XMLHttpRequest',
                'Content-Type':'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ action, id })
        })
            .then(r=>r.ok? r.json(): Promise.reject(r.statusText))
            .then(json=>{
                // mettre à jour les rows
                const tbody = document.getElementById('cartTbody');
                tbody.innerHTML = '';
                json.items.forEach(item=>{
                    const tr = document.createElement('tr');
                    tr.innerHTML =
                        '<td class="text-start">' +
                        '<div class="d-flex align-items-center">' +
                        '<img src="images/' + item.image + '" class="cart-item-image me-3" alt="' + item.nom + '">' +
                        item.nom +
                        '</div>' +
                        '</td>' +

                        '<td class="text-danger">' +
                        item.prix + ' $' +
                        '</td>' +

                        '<td>' +
                        '<button type="button" class="btn btn-sm btn-outline-secondary cart-btn" ' +
                        'data-action="supprimer" data-id="' + item.id + '">−</button> ' +
                        '<span class="mx-2">' + item.quantite + '</span> ' +
                        '<button type="button" class="btn btn-sm btn-outline-success cart-btn" ' +
                        'data-action="ajouter" data-id="' + item.id + '">+</button>' +
                        '</td>' +

                        '<td>' +
                        '<button type="button" class="btn btn-sm btn-outline-danger cart-btn" ' +
                        'data-action="supprimerTout" data-id="' + item.id + '">❌</button>' +
                        '</td>';

                    tbody.appendChild(tr);
                });

                const sousTotal = json.total;
                const tps       = sousTotal * 0.05;
                const tvq       = sousTotal * 0.09975;
                const totalAll  = sousTotal + tps + tvq;


                document.getElementById('sousTotalCell').textContent = sousTotal.toFixed(2) + ' $';
                document.getElementById('tpsCell').textContent       = tps.toFixed(2)       + ' $';
                document.getElementById('tvqCell').textContent       = tvq.toFixed(2)       + ' $';
                document.getElementById('totalCell').textContent     = totalAll.toFixed(2)  + ' $';


            })
            .catch(console.error);
    }
    document.addEventListener('DOMContentLoaded', ()=>{
        document.getElementById('cartTbody')
            .addEventListener('click', e=>{
                const btn = e.target.closest('.cart-btn');
                if (!btn) return;
                e.preventDefault();
                updateCart(btn.dataset.action, btn.dataset.id);
            });
    });

    const form = document.getElementById('commandeForm');

    // Helper pour montrer/cacher les blocs
    function show(el, yes) { el.style.display = yes ? '' : 'none'; }

    // Mode Retrait
    document.querySelectorAll('input[name=modeRetrait]').forEach(radio => {
        radio.addEventListener('change', () => {
            const isLiv = document.getElementById('optLivraison').checked;
            show(document.getElementById('blocLivraison'), isLiv);
            show(document.getElementById('blocVille'),    isLiv);
            show(document.getElementById('blocCP'),       isLiv);
            show(document.getElementById('blocRamassage'),!isLiv);

            document.getElementById('modeRetraitBool').value = isLiv;
        });
    });

    // Mode Paiement
    document.querySelectorAll('input[name=modePaiement]').forEach(radio => {
        radio.addEventListener('change', () => {
            const isCarte = document.getElementById('optCarte').checked;
            show(document.getElementById('blocCarte'),      isCarte);
            show(document.getElementById('blocExp'),        isCarte);
            show(document.getElementById('blocCVV'),        isCarte);
            show(document.getElementById('blocAjoutCarte'), isCarte);
            show(document.getElementById('msgComptant'),   !isCarte);
            document.getElementById('modePaiementBool').value = isCarte;
        });
    });

    // Ajouter carte
    document.getElementById('btnAjouterCarte').addEventListener('click', () => {
        const num  = document.getElementById('numCarte').value;
        const exp  = document.getElementById('expiration').value;
        const cvv  = document.getElementById('cvv').value;

        const tempId = 'tmp_' + Date.now();
        sessionStorage.setItem('paie_' + tempId, JSON.stringify({ num, exp, cvv }));
        document.getElementById('idPaiementTemp').value = tempId;
        document.getElementById('carteAjoutee').textContent = 'Carte ajoutée ✔️';
    });


    form.addEventListener('submit', e => {
        if (document.getElementById('optCarte').checked &&
            !document.getElementById('idPaiementTemp').value) {
            e.preventDefault();
            alert('Veuillez d’abord ajouter votre carte.');
        }
    });

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
