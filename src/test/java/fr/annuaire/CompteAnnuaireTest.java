package fr.annuaire;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CompteAnnuaireTest {

    @Test
    void referencerProfessionnelSante_doit_produire_professionnelSante() {
        //Given
        CompteIdentifier identifiant = CompteIdentifier.of("ID12");
        ReferencerProfessionnelSante referencerProfessionnelSante = new ReferencerProfessionnelSante(identifiant);

        // When
        ProfessionnelSanteReference professionelSanteReference = CompteAnnuaire.traiter(referencerProfessionnelSante);

        // Then
        Assertions.assertEquals(professionelSanteReference.getIdentifiant(), identifiant);
    }

    @Test
    void activerProfessionnelSante_doit_produire_professionnelSanteActive() {
        // Given
        CompteIdentifier identifiant = CompteIdentifier.of("ID013");
        CompteAnnuaire compte = CompteAnnuaire.of(
                buildProfessionnelSanteReference(identifiant)
        );

        ActiverProfessionnelSante activerProfessionnelSante = new ActiverProfessionnelSante(identifiant);

        // When
        ProfessionnelSanteActive professionnelSanteActive = compte.traiter(activerProfessionnelSante);

        // Then
        Assertions.assertEquals(professionnelSanteActive.getIdentifiant(), identifiant);
    }

    @Test
    void desactiverProfessionnelSante_doit_produire_professionnelSanteDesactive() {
        // Given
        CompteIdentifier identifiant = CompteIdentifier.of("ID14");
        var evenements = List.of(
                buildProfessionnelSanteReference(identifiant),
                buildProfessionnelSanteActive(identifiant)
        );
        CompteAnnuaire compte = CompteAnnuaire.of(evenements);

        DesactiverProfessionnelSante desactiverProfessionnelSante = new DesactiverProfessionnelSante(identifiant);

        // When
        ProfessionnelSanteDesactive professionnelSanteDesactive = compte.traiter(desactiverProfessionnelSante);

        // Then
        Assertions.assertEquals(professionnelSanteDesactive.getIdentifiant(), identifiant);
    }

    @Test
    void desactiverProfessionnelSante_desactive_ne_doit_rien_produire() {
        // Given
        CompteIdentifier identifiant = CompteIdentifier.of("ID14");
        var evenements = List.of(
                buildProfessionnelSanteReference(identifiant),
                buildProfessionnelSanteActive(identifiant),
                buildProfessionnelSanteDesactive(identifiant)
        );
        CompteAnnuaire compte = CompteAnnuaire.of(evenements);

        DesactiverProfessionnelSante desactiverProfessionnelSante = new DesactiverProfessionnelSante(identifiant);

        // When
        var evenement = compte.traiter(desactiverProfessionnelSante);

        // Then
        Assertions.assertNull(evenement);
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
