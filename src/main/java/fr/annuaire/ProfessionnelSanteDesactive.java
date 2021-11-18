package fr.annuaire;

public class ProfessionnelSanteDesactive implements  EvenementProfessionnelSante {

    public String identifiant;

    public ProfessionnelSanteDesactive(String identifiant) {
        this.identifiant = identifiant;
    }
}
