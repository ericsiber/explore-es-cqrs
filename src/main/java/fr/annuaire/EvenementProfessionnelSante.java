package fr.annuaire;

import java.util.Objects;

public sealed class EvenementProfessionnelSante permits ProfessionnelSanteActive, ProfessionnelSanteDesactive, ProfessionnelSanteReference {
    private CompteIdentifier identifiant;

    public EvenementProfessionnelSante(CompteIdentifier identifiant) {
        this.identifiant = identifiant;
    }

    public CompteIdentifier getIdentifiant() {
        return identifiant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvenementProfessionnelSante that = (EvenementProfessionnelSante) o;
        return identifiant.equals(that.identifiant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiant);
    }
}
