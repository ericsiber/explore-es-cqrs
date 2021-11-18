package fr.annuaire;

public class ActiverProfessionnelSante {
    private final CompteIdentifier identifiant;

    public ActiverProfessionnelSante(CompteIdentifier identifiant) {
        this.identifiant = identifiant;
    }

    public CompteIdentifier getIdentifiant() {
        return identifiant;
    }
}
