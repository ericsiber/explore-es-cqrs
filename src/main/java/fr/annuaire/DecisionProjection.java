package fr.annuaire;

public class DecisionProjection {

    private EtatCompte etatCompte;

    public DecisionProjection(EtatCompte etatCompte) {
        this.etatCompte = etatCompte;
    }

    public EtatCompte getEtatCompte() {
        return this.etatCompte;
    }
}
