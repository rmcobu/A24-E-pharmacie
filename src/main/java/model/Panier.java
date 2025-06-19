package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Panier {
    private Map<Medicament, Integer> items = new HashMap<>();

    public void ajouterMedicament(Medicament med, int quantite) {
        items.merge(med, quantite, Integer::sum);
    }

    public void enleverMedicament(Medicament med, int quantite) {
        items.computeIfPresent(med, (m, q) -> {
            int nouvelleQ = q - quantite;
            return (nouvelleQ > 0) ? nouvelleQ : null;
        });
    }


    public void setQuantite(Medicament med, int quantite) {
        if (quantite > 0) {
            items.put(med, quantite);
        } else {
            items.remove(med);
        }
    }


    public void supprimerMedicament(Medicament med) {
        items.remove(med);
    }


    public void clear() {
        items.clear();
    }


    public int getLinesCount() {
        return items.size();
    }


    public int getTotalItemsCount() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }


    public Map<Medicament, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public double getTotal() {
        return items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrix() * e.getValue())
                .sum();
    }
}