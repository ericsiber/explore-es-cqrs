package fr.annuaire;

public class ProfessionnelSanteActive implements  EvenementProfessionnelSante {

    private CompteIdentifier identifiant;

    public ProfessionnelSanteActive(CompteIdentifier identifiant) {
        this.identifiant = identifiant;
    }

    public CompteIdentifier getIdentifiant() {
        return identifiant;
    }
}
