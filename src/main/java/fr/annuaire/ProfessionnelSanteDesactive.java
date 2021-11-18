package fr.annuaire;

public class ProfessionnelSanteDesactive implements  EvenementProfessionnelSante {

    private CompteIdentifier identifiant;

    public ProfessionnelSanteDesactive(CompteIdentifier identifiant) {
        this.identifiant = identifiant;
    }

    public CompteIdentifier getIdentifiant() {
        return identifiant;
    }
}
