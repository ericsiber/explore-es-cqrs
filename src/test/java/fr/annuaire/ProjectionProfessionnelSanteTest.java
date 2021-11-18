package fr.annuaire;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectionProfessionnelSanteTest {

    @Test
    void professionnelDeSanteReference_doit_ajouter_id_dans_listeProfessionnelsSanteNonActives() {
        // Given
        String id = "ID15";
        ProjectionProfessionnelsSanteNonActive.initialiserIdentifiantsProfessionnelsSante(List.of("ID00"));

        // When
        ProjectionProfessionnelsSanteNonActive.consommer(buildProfessionnelSanteReference(id));

        // Then
        assertThat(ProjectionProfessionnelsSanteNonActive.getIdentifiantsProfessionnelsSante()).contains(id);
    }

    @Test
    void professionnelDeSanteActive_doit_retirer_id_de_listeProfessionnelsSanteNonActives() {
        // Given
        String id = "ID20";
        ProjectionProfessionnelsSanteNonActive.initialiserIdentifiantsProfessionnelsSante(List.of(id));

        // When
        ProjectionProfessionnelsSanteNonActive.consommer(buildProfessionnelSanteActive(id));

        // Then
        assertThat(
                ProjectionProfessionnelsSanteNonActive.getIdentifiantsProfessionnelsSante()
        ).doesNotContain(id);
    }

    private ProfessionnelSanteReference buildProfessionnelSanteReference(String identifiant) {
        return new ProfessionnelSanteReference(identifiant);
    }

    private EvenementProfessionnelSante buildProfessionnelSanteActive(String identifiant) {
        return new ProfessionnelSanteActive(identifiant);
    }

}
