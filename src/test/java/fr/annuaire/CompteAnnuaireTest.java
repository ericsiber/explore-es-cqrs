package fr.annuaire;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CompteAnnuaireTest {

    @Test
    void referencerProfessionnelSante_doit_produire_professionnelSante() {
        //Given
        ReferencerProfessionnelSante referencerProfessionnelSante = new ReferencerProfessionnelSante("ID12");

        // When
        ProfessionnelSanteReference professionelSanteReference = CompteAnnuaire.traiter(referencerProfessionnelSante);

        // Then
        Assertions.assertEquals(professionelSanteReference.identifiant, "ID12");
    }

    @Test
    void activerProfessionnelSante_doit_produire_professionnelSanteActive() {
        // Given
        CompteAnnuaire compte = CompteAnnuaire.of(
                buildProfessionnelSanteReference("ID013")
        );

        ActiverProfessionnelSante activerProfessionnelSante = new ActiverProfessionnelSante("ID13");

        // When
        ProfessionnelSanteActive professionnelSanteActive = compte.traiter(activerProfessionnelSante);

        // Then
        Assertions.assertEquals(professionnelSanteActive.identifiant, "ID13");
    }

    @Test
    void desactiverProfessionnelSante_doit_produire_professionnelSanteDesactive() {
        // Given
        String identifiant = "ID14";
        var evenements = List.of(
                buildProfessionnelSanteReference(identifiant),
                buildProfessionnelSanteActive(identifiant)
        );
        CompteAnnuaire compte = CompteAnnuaire.of(evenements);

        DesactiverProfessionnelSante desactiverProfessionnelSante = new DesactiverProfessionnelSante(identifiant);

        // When
        ProfessionnelSanteDesactive professionnelSanteDesactive = compte.traiter(desactiverProfessionnelSante);

        // Then
        Assertions.assertEquals(professionnelSanteDesactive.identifiant, identifiant);
    }

    @Test
    void desactiverProfessionnelSante_desactive_ne_doit_rien_produire() {
        // Given
        String identifiant = "ID14";
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

    private ProfessionnelSanteReference buildProfessionnelSanteReference(String identifiant) {
        return new ProfessionnelSanteReference(identifiant);
    }

    private EvenementProfessionnelSante buildProfessionnelSanteActive(String identifiant) {
        return new ProfessionnelSanteActive(identifiant);
    }

    private EvenementProfessionnelSante buildProfessionnelSanteDesactive(String identifiant) {
        return new ProfessionnelSanteDesactive(identifiant);
    }
}
