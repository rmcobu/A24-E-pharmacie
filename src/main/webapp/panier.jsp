<table class="table">
    <thead>
    <tr>
        <th>Médicament</th>
        <th>Prix unitaire</th>
        <th>Quantité</th>
        <th>Total</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${panier.items}" var="entry">
        <tr>
            <td>${entry.key.nom}</td>
            <td>${entry.key.prix} €</td>
            <td>${entry.value}</td>
            <td>${entry.key.prix * entry.value} $</td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr class="table-active">
        <td colspan="3">Total</td>
        <td>${panier.total} €</td>
    </tr>
    </tfoot>
</table>