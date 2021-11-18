package fr.annuaire;

import java.util.Objects;

public class ProfessionnelSanteReference implements EvenementProfessionnelSante {
    private CompteIdentifier identifiant;

    public ProfessionnelSanteReference(CompteIdentifier identifiant) {
        this.identifiant = identifiant;
    }

    public CompteIdentifier getIdentifiant() {
        return identifiant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessionnelSanteReference that = (ProfessionnelSanteReference) o;
        return identifiant.equals(that.identifiant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiant);
    }
}
