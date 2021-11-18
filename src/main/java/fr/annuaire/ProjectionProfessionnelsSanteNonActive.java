package fr.annuaire;

import java.util.ArrayList;
import java.util.List;

public class ProjectionProfessionnelsSanteNonActive {

    private static List<CompteIdentifier> identifiants = new ArrayList<>();

    private static void ajouterProfessionnel(CompteIdentifier id) {
        identifiants.add(id);
    }

    private static void retirerProfessionnel(CompteIdentifier id) {
        identifiants.remove(id);
    }


    public static void initialiserIdentifiantsProfessionnelsSante(List<CompteIdentifier> identifiants) {
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
        ajouterProfessionnel(professionnelSanteReference.getIdentifiant());
    }

    private static void consommer(ProfessionnelSanteActive professionnelSanteActive) {
        retirerProfessionnel(professionnelSanteActive.getIdentifiant());
    }

    public static List<CompteIdentifier> getIdentifiantsProfessionnelsSante() {
        return identifiants;
    }

}
