package org.example;

public class Joueur {
    private int solde;
    private int mise;

    public Joueur(int soldeInitial, int mise) {
        this.solde = soldeInitial;
        this.mise = mise;
    }

    public void crediter(int montant) {
        solde += montant;
    }

    public void debiter(int montant) {
        solde -= montant;
    }

    public int mise() {
        return mise;
    }
}
