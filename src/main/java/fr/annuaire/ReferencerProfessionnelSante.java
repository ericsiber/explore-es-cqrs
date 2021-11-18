package fr.annuaire;

public class ReferencerProfessionnelSante {

    private final CompteIdentifier identifiant;

    public ReferencerProfessionnelSante(CompteIdentifier identifiant) {
        this.identifiant = identifiant;
    }

    public CompteIdentifier getIdentifiant() {
        return identifiant;
    }
}
