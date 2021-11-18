package fr.annuaire;

import java.util.ArrayList;
import java.util.List;

public class ProjectionProfessionnelsSanteNonActive {

    private static List<String> identifiants = new ArrayList<>();

    private static void ajouterProfessionnel(String id) {
        identifiants.add(id);
    }

    private static void retirerProfessionnel(String id) {
        identifiants.remove(id);
    }


    public static void initialiserIdentifiantsProfessionnelsSante(List<String> identifiants) {
        identifiants = new ArrayList<>(identifiants);
    }

    public static void consommer(EvenementProfessionnelSante evenementProfessionnelSante) {
        if (evenementProfessionnelSante.getClass() == ProfessionnelSanteReference.class) {
            consommer((ProfessionnelSanteReference) evenementProfessionnelSante);
        }
        if (evenementProfessionnelSante.getClass() == ProfessionnelSanteActive.class) {
            consommer((ProfessionnelSanteActive) evenementProfessionnelSante);
        }
    }

    private static void consommer(ProfessionnelSanteReference professionnelSanteReference) {
        ajouterProfessionnel(professionnelSanteReference.identifiant);
    }

    private static void consommer(ProfessionnelSanteActive professionnelSanteActive) {
        retirerProfessionnel(professionnelSanteActive.identifiant);
    }

    public static List<String> getIdentifiantsProfessionnelsSante() {
        return identifiants;
    }

}
