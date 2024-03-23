package org.example;

public class Jeu {
    private Banque banque;
    private boolean ouvert;



    public Jeu(Banque banque) {
        this.banque = banque;
        this.ouvert = true;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if (!ouvert) {
            throw new JeuFermeException("Le jeu est fermé.");
        }

        if (!banque.est_solvable()) {
            ouvert = false;
            throw new JeuFermeException("La banque n'est plus solvable.");
        }

        int sommeDes = de1.lancer() + de2.lancer();

        if (sommeDes == 7) {
            joueur.crediter(2 * joueur.mise());
            banque.debiter(2 * joueur.mise());
            if (!banque.est_solvable()) {
                ouvert = false;
                throw new JeuFermeException("La banque n'est plus solvable.");
            }
        } else {
            joueur.debiter(joueur.mise());
        }
    }

    public void fermer() {
        ouvert = false;
    }

    public boolean estOuvert() {
        return ouvert;
    }
}