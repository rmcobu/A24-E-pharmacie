package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Panier {
    private Map<Medicament, Integer> items = new HashMap<>();

    public void ajouterMedicament(Medicament med, int quantite) {
        items.merge(med, quantite, Integer::sum);
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