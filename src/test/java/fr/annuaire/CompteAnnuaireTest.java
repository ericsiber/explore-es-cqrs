package fr.annuaire;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CompteAnnuaireTest {

    @Test
    void referencerProfessionnelSante_doit_produire_professionnelSanteReference() {
        //Given
        CompteIdentifier identifiant = new CompteIdentifier("ID12");
        ReferencerProfessionnelSante referencerProfessionnelSante = new ReferencerProfessionnelSante(identifiant);
        CompteAnnuaire compte = new CompteAnnuaire(identifiant);

        // When
        var evenements = compte.traiter(referencerProfessionnelSante);

        // Then
        assertThat(evenements).containsExactly(
          new ProfessionnelSanteReference(identifiant)
        );
    }

    @Test
    void activerProfessionnelSante_doit_produire_professionnelSanteActive() {
        // Given
        CompteIdentifier identifiant = new CompteIdentifier("ID013");
        CompteAnnuaire compte = new CompteAnnuaire(identifiant, List.of(buildProfessionnelSanteReference(identifiant)));

        ActiverProfessionnelSante activerProfessionnelSante = new ActiverProfessionnelSante(identifiant);

        // When
        var evenements = compte.traiter(activerProfessionnelSante);

        // Then
        assertThat(evenements).containsExactly(
                new ProfessionnelSanteActive(identifiant)
        );
    }

    @Test
    void desactiverProfessionnelSante_doit_produire_professionnelSanteDesactive() {
        // Given
        CompteIdentifier identifiant = new CompteIdentifier("ID14");
        var historique = List.of(
                buildProfessionnelSanteReference(identifiant),
                buildProfessionnelSanteActive(identifiant)
        );
        CompteAnnuaire compte = new CompteAnnuaire(identifiant, historique);

        DesactiverProfessionnelSante desactiverProfessionnelSante = new DesactiverProfessionnelSante(identifiant);

        // When
        var evenements = compte.traiter(desactiverProfessionnelSante);

        // Then
        assertThat(evenements).containsExactly(
                new ProfessionnelSanteDesactive(identifiant)
        );
    }

    @Test
    void desactiverProfessionnelSante_desactive_ne_doit_rien_produire() {
        // Given
        CompteIdentifier identifiant = new CompteIdentifier("ID14");
        var historique = List.of(
                buildProfessionnelSanteReference(identifiant),
                buildProfessionnelSanteActive(identifiant),
                buildProfessionnelSanteDesactive(identifiant)
        );
        CompteAnnuaire compte = new CompteAnnuaire(identifiant, historique);

        DesactiverProfessionnelSante desactiverProfessionnelSante = new DesactiverProfessionnelSante(identifiant);

        // When
        var evenements = compte.traiter(desactiverProfessionnelSante);

        // Then
        assertThat(evenements).isEmpty();
    }

    private ProfessionnelSanteReference buildProfessionnelSanteReference(CompteIdentifier identifiant) {
        return new ProfessionnelSanteReference(identifiant);
    }

    private EvenementProfessionnelSante buildProfessionnelSanteActive(CompteIdentifier identifiant) {
        return new ProfessionnelSanteActive(identifiant);
    }

    private EvenementProfessionnelSante buildProfessionnelSanteDesactive(CompteIdentifier identifiant) {
        return new ProfessionnelSanteDesactive(identifiant);
    }
}
