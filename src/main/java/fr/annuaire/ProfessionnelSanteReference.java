package fr.annuaire;

public class ProfessionnelSanteReference implements EvenementProfessionnelSante {
    private CompteIdentifier identifiant;

    public ProfessionnelSanteReference(CompteIdentifier identifiant) {
        this.identifiant = identifiant;
    }

    public CompteIdentifier getIdentifiant() {
        return identifiant;
    }
}
