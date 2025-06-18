package service;

import model.Medicament;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicamentService {
    private List<Medicament> medicaments;

    public MedicamentService() {
        medicaments = new ArrayList<>();

        medicaments.add(new Medicament(1, "Tylenol", "Analgésique", 7.99, true, "Soulage la douleur et la fièvre", 50,"tylenol.jpg"));
        medicaments.add(new Medicament(2, "Advil", "Anti-inflammatoire", 8.49, true, "Réduit inflammation et douleurs", 40,"advil.jpg"));
        medicaments.add(new Medicament(3, "Aspirine", "Antipyrétique", 5.99, true, "Traitement douleurs légères", 35,"aspirin.png"));
        medicaments.add(new Medicament(4, "Reactine", "Antihistaminique", 11.99, true, "Allergies saisonnières", 30,"reactine.png"));
        medicaments.add(new Medicament(5, "Claritin", "Antihistaminique", 12.49, true, "Soulage les allergies", 25,"claritin.png"));
        medicaments.add(new Medicament(6, "Gravol", "Antinauséeux", 6.99, true, "Prévention nausées/vomissements", 45,"gravol.png"));
        medicaments.add(new Medicament(7, "Tums", "Antiacide", 4.99, true, "Soulage les brûlures d’estomac", 50,"tums.png"));
        medicaments.add(new Medicament(8, "Imodium", "Antidiarrhéique", 9.49, true, "Traitement de la diarrhée", 30,"imodium.png"));
        medicaments.add(new Medicament(9, "Crestor", "Cholestérol", 23.99, false, "Réduction du cholestérol", 0,"crestor.png"));
        medicaments.add(new Medicament(10, "Ventolin", "Bronchodilatateur", 14.99, true, "Soulage l'asthme", 20,"ventolin.png"));

        medicaments.add(new Medicament(11, "Zyrtec", "Antihistaminique", 10.49, true, "Soulage les éternuements", 20, "zyrtec.png"));
        medicaments.add(new Medicament(12, "Metformin", "Diabète", 18.99, true, "Contrôle la glycémie", 50,"metformina.png"));
        medicaments.add(new Medicament(13, "Synthroid", "Thyroïde", 21.49, true, "Hormone thyroïdienne", 15));
        medicaments.add(new Medicament(14, "Lyrica", "Neuropathie", 32.99, true, "Douleurs nerveuses", 10));
        medicaments.add(new Medicament(15, "Zoloft", "Antidépresseur", 27.99, true, "Traitement de la dépression", 20));
        medicaments.add(new Medicament(16, "Cipralex", "Antidépresseur", 25.49, true, "Anxiété/dépression", 18));
        medicaments.add(new Medicament(17, "Prozac", "Antidépresseur", 24.99, true, "Troubles dépressifs", 16));
        medicaments.add(new Medicament(18, "Ritalin", "TDAH", 29.49, false, "Améliore la concentration", 0));
        medicaments.add(new Medicament(19, "Ativan", "Anxiolytique", 22.99, true, "Réduit l’anxiété", 14));
        medicaments.add(new Medicament(20, "Xanax", "Anxiolytique", 26.99, false, "Traitement des crises d’angoisse", 0));

        medicaments.add(new Medicament(21, "Nasonex", "Corticostéroïde", 13.49, true, "Congestion nasale", 24,"nasonex.png"));
        medicaments.add(new Medicament(22, "Cymbalta", "Antidépresseur", 28.49, true, "Douleurs et dépression", 12,"cymbalta.png"));
        medicaments.add(new Medicament(23, "Tamiflu", "Antiviral", 34.99, true, "Traitement de la grippe", 10,"tamiflu.png"));
        medicaments.add(new Medicament(24, "Prevacid", "Anti-ulcéreux", 17.99, true, "Traitement des ulcères", 9,"prevacid.png"));
        medicaments.add(new Medicament(25, "Zantac", "Antiacide", 15.99, true, "Réduction de l’acidité", 22,"zantac.png"));
        medicaments.add(new Medicament(26, "Plavix", "Anticoagulant", 31.49, true, "Prévention AVC/crises", 11,"plavix.png"));
        medicaments.add(new Medicament(27, "Coumadin", "Anticoagulant", 33.49, true, "Fluidifiant sanguin", 8,"coumadin.png"));
        medicaments.add(new Medicament(28, "Aerius", "Antihistaminique", 9.99, true, "Allergies respiratoires", 30,"aerius.png"));
        medicaments.add(new Medicament(29, "Nicoderm", "Sevrage tabagique", 19.99, true, "Aide à arrêter de fumer", 17,"nicoderm.png"));
        medicaments.add(new Medicament(30, "Dulcolax", "Laxatif", 6.49, true, "Soulage la constipation", 36,"dulcolax.png"));
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

