package fr.annuaire;

import java.util.List;

public class CompteAnnuaire {

    private String identifiant;
    private DecisionProjection decision;

    public CompteAnnuaire(String identifiant) {
        this.identifiant = identifiant;
    }

    public static ProfessionnelSanteReference traiter(ReferencerProfessionnelSante referencerProfessionnelSante) {
         return new ProfessionnelSanteReference(referencerProfessionnelSante.identifiant);
    }

    public static CompteAnnuaire of(ProfessionnelSanteReference professionnelSanteReference) {
        return new CompteAnnuaire(professionnelSanteReference.identifiant);
    }

    public static CompteAnnuaire of(List<EvenementProfessionnelSante> evenements) {
        CompteAnnuaire compte = new CompteAnnuaire("");
        for (EvenementProfessionnelSante evenement : evenements) {
            compte = compte.enrichir(evenement);
        }
        return compte;
    }

    private CompteAnnuaire enrichir(EvenementProfessionnelSante evenementProfessionnelSante) {
       if (evenementProfessionnelSante.getClass() == ProfessionnelSanteReference.class) {
           return of((ProfessionnelSanteReference) evenementProfessionnelSante);
        }
       if (evenementProfessionnelSante.getClass() == ProfessionnelSanteActive.class) {
           return of(this, (ProfessionnelSanteActive) evenementProfessionnelSante);
       }
       if (evenementProfessionnelSante.getClass() == ProfessionnelSanteDesactive.class) {
           return of(this, (ProfessionnelSanteDesactive) evenementProfessionnelSante);
       }
       return this;
    }

    private static CompteAnnuaire of(CompteAnnuaire compteExistant, ProfessionnelSanteActive professionnelSanteActive) {
        CompteAnnuaire compte = new CompteAnnuaire(compteExistant.getIdentifiant());
        compte.setDecision(new DecisionProjection(EtatCompte.ACTIF));
        return compte;
    }

    private static CompteAnnuaire of(CompteAnnuaire compteExistant, ProfessionnelSanteDesactive professionnelSanteDesactive) {
        CompteAnnuaire compte = new CompteAnnuaire(compteExistant.getIdentifiant());
        compte.setDecision(new DecisionProjection(EtatCompte.INACTIF));
        return compte;
    }

    public ProfessionnelSanteActive traiter(ActiverProfessionnelSante activerProfessionnelSante) {
        return new ProfessionnelSanteActive(activerProfessionnelSante.getIdentifiant());
    }

    public ProfessionnelSanteDesactive traiter(DesactiverProfessionnelSante desactiverProfessionnelSante) {
        if (this.decision.getEtatCompte() == EtatCompte.ACTIF)
            return new ProfessionnelSanteDesactive(desactiverProfessionnelSante.getIdentifiant());
        return null;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    private void setDecision(DecisionProjection decision) {
        this.decision = decision;
    }


}
