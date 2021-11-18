package fr.annuaire;

public class DesactiverProfessionnelSante {
    private final CompteIdentifier identifiant;

    public DesactiverProfessionnelSante(CompteIdentifier identifiant) {
        this.identifiant = identifiant;
    }

    public CompteIdentifier getIdentifiant() {
        return identifiant;
    }
}
