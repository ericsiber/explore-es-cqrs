package fr.annuaire;

import java.util.List;

public class CompteAnnuaire {

    private CompteIdentifier identifiant;
    private DecisionProjection decision;

    public CompteAnnuaire(CompteIdentifier identifiant) {
        this.identifiant = identifiant;
        this.decision = new DecisionProjection();
    }

    public CompteAnnuaire(CompteIdentifier identifiant, List<EvenementProfessionnelSante> historique) {
        this(identifiant);
        historique.forEach(this::consommer);
    }

    private void consommer(EvenementProfessionnelSante evenementProfessionnelSante) {
        switch (evenementProfessionnelSante) {
            case ProfessionnelSanteReference event -> consommer(event);
            case ProfessionnelSanteActive event -> consommer(event);
            case ProfessionnelSanteDesactive event -> consommer(event);
            default -> {
            }
        }
    }

    private void consommer(ProfessionnelSanteReference evenementProfessionnelSante) {
        // nothing
    }

    private void consommer(ProfessionnelSanteDesactive evenementProfessionnelSante) {
        this.decision.setEtatCompte(EtatCompte.INACTIF);
    }

    private void consommer(ProfessionnelSanteActive evenementProfessionnelSante) {
        this.decision.setEtatCompte(EtatCompte.ACTIF);
    }

    public List<EvenementProfessionnelSante> traiter(ReferencerProfessionnelSante referencerProfessionnelSante) {
         return List.of(new ProfessionnelSanteReference(referencerProfessionnelSante.identifiant()));
    }

    public List<EvenementProfessionnelSante> traiter(ActiverProfessionnelSante activerProfessionnelSante) {
        return List.of(new ProfessionnelSanteActive(activerProfessionnelSante.identifiant()));
    }

    public List<EvenementProfessionnelSante> traiter(DesactiverProfessionnelSante desactiverProfessionnelSante) {
        if (this.decision.getEtatCompte() == EtatCompte.ACTIF)
            return List.of(new ProfessionnelSanteDesactive(desactiverProfessionnelSante.identifiant()));
        return List.of();
    }

}
