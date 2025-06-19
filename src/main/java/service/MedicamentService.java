package service;

import model.Medicament;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicamentService {
    private List<Medicament> medicaments;

    public MedicamentService() {
        medicaments = new ArrayList<>();

        medicaments.add(new Medicament(1, "Tylenol", "Médicaments", 7.99, true, "Soulage la douleur et la fièvre", 50, "tylenol.jpg"));
        medicaments.add(new Medicament(2, "Advil", "Médicaments", 8.49, true, "Réduit inflammation et douleurs", 40, "advil.jpg"));
        medicaments.add(new Medicament(3, "Aspirine", "Médicaments", 5.99, true, "Traitement douleurs légères", 35, "aspirin.png"));
        medicaments.add(new Medicament(4, "Reactine", "Médicaments", 11.99, true, "Allergies saisonnières", 30, "reactine.png"));
        medicaments.add(new Medicament(5, "Claritin", "Médicaments", 12.49, true, "Soulage les allergies", 25, "claritin.png"));
        medicaments.add(new Medicament(6, "Gravol", "Médicaments", 6.99, true, "Prévention nausées/vomissements", 45, "gravol.png"));
        medicaments.add(new Medicament(7, "Tums", "Médicaments", 4.99, true, "Soulage les brûlures d'estomac", 50, "tums.png"));
        medicaments.add(new Medicament(8, "Imodium", "Médicaments", 9.49, true, "Traitement de la diarrhée", 30, "imodium.png"));
        medicaments.add(new Medicament(9, "Crestor", "Médicaments", 23.99, false, "Réduction du cholestérol", 0, "crestor.png"));
        medicaments.add(new Medicament(10, "Ventolin", "Médicaments", 14.99, true, "Soulage l'asthme", 20, "ventolin.png"));

        medicaments.add(new Medicament(11, "Zyrtec", "Médicaments", 10.49, true, "Soulage les éternuements", 20, "zyrtec.png"));
        medicaments.add(new Medicament(12, "Metformin", "Médicaments", 18.99, true, "Contrôle la glycémie", 50, "metformina.png"));
        medicaments.add(new Medicament(13, "Synthroid", "Médicaments", 21.49, true, "Hormone thyroïdienne", 15, "synthroid.png"));
        medicaments.add(new Medicament(14, "Lyrica", "Médicaments", 32.99, true, "Douleurs nerveuses", 10, "lyrica.png"));
        medicaments.add(new Medicament(15, "Zoloft", "Médicaments", 27.99, true, "Traitement de la dépression", 20, "zoloft.png"));
        medicaments.add(new Medicament(16, "Cipralex", "Médicaments", 25.49, true, "Anxiété/dépression", 18, "cipralex.png"));
        medicaments.add(new Medicament(17, "Prozac", "Médicaments", 24.99, true, "Troubles dépressifs", 16, "prozac.png"));
        medicaments.add(new Medicament(18, "Ritalin", "Médicaments", 29.49, false, "Améliore la concentration", 0, "ritalin.png"));
        medicaments.add(new Medicament(19, "Ativan", "Médicaments", 22.99, true, "Réduit l'anxiété", 14, "ativan.png"));
        medicaments.add(new Medicament(20, "Xanax", "Médicaments", 26.99, false, "Traitement des crises d'angoisse", 0, "xanax.png"));

        medicaments.add(new Medicament(21, "Nasonex", "Médicaments", 13.49, true, "Congestion nasale", 24, "nasonex.png"));
        medicaments.add(new Medicament(22, "Cymbalta", "Médicaments", 28.49, true, "Douleurs et dépression", 12, "cymbalta.png"));
        medicaments.add(new Medicament(23, "Tamiflu", "Médicaments", 34.99, true, "Traitement de la grippe", 10, "tamiflu.png"));
        medicaments.add(new Medicament(24, "Prevacid", "Médicaments", 17.99, true, "Traitement des ulcères", 9, "prevacid.png"));
        medicaments.add(new Medicament(25, "Zantac", "Médicaments", 15.99, true, "Réduction de l'acidité", 22, "zantac.png"));
        medicaments.add(new Medicament(26, "Plavix", "Médicaments", 31.49, true, "Prévention AVC/crises", 11, "plavix.png"));
        medicaments.add(new Medicament(27, "Coumadin", "Médicaments", 33.49, true, "Fluidifiant sanguin", 8, "coumadin.png"));
        medicaments.add(new Medicament(28, "Aerius", "Médicaments", 9.99, true, "Allergies respiratoires", 30, "aerius.png"));

        //Hygiène
        medicaments.add(new Medicament(29, "Nicoderm", "Hygiéniques", 19.99, true, "Aide à arrêter de fumer", 17, "nicoderm.png"));
        medicaments.add(new Medicament(30, "Dulcolax", "Hygiéniques", 6.49, true, "Soulage la constipation", 36, "dulcolax.png"));
        medicaments.add(new Medicament(2, "Dove Savon", "Hygiène", 3.99, true, "Savon doux pour le corps", 30, "dove.png"));
        medicaments.add(new Medicament(3, "Oral-B Brosses à dents", "Hygiène", 4.49, true, "Brosses à dents souples", 40, "oral_b.png"));
        medicaments.add(new Medicament(4, "Head & Shoulders Shampoo", "Hygiène", 8.99, true, "Shampoing anti-pelliculaire", 25, "headshoulders.png"));
        medicaments.add(new Medicament(5, "Gillette Rasoir", "Hygiène", 12.99, true, "Rasoir jetable", 20, "gillette.webp"));
        //Vitamine
        medicaments.add(new Medicament(6, "Vitamine C", "Vitamine", 9.99, true, "Complément en vitamine C", 60, "Vitamine_C.webp"));
        medicaments.add(new Medicament(7, "Vitamine D3", "Vitamine", 12.49, true, "Supplément en vitamine D", 45, "vitamine_D.webp"));
        medicaments.add(new Medicament(8, "Multivitamines", "Vitamine", 15.99, true, "Complexe vitaminique complet", 50, "Multivitamines.jpg"));
        medicaments.add(new Medicament(9, "Fer", "Vitamine", 11.29, true, "Complément en fer", 35, "Fer.webp"));
    }

    public List<Medicament> getCatalogue() {
        return medicaments;
    }

    public List<Medicament> filterBy(String nomFilter, String catFilter) {
        return medicaments.stream()
                .filter(m -> {
                    boolean okNom = true, okCat = true;
                    if (nomFilter != null && !nomFilter.isBlank()) {
                        okNom = m.getNom().toLowerCase()
                                .contains(nomFilter.toLowerCase());
                    }
                    if (catFilter != null && !catFilter.isBlank()) {
                        okCat = m.getCategorie().toLowerCase()
                                .contains(catFilter.toLowerCase());
                    }
                    return okNom && okCat;
                })
                .collect(Collectors.toList());
    }
}

