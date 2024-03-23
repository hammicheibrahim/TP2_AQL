package org.example;

public class Banque {
    private int solde;

    public Banque(int soldeInitial) {
        this.solde = soldeInitial;
    }

    public void debiter(int montant) {
        solde -= montant;
    }

    public void crediter(int montant) {
        solde += montant;
    }

    public boolean est_solvable() {
        return solde > 0;
    }
}