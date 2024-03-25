import org.example.*;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class JeuTest {

    @Test
    public void testJeuFerme_q4() {
        // Arrange
        Banque banqueMock = mock(Banque.class);
        InterfaceJeu jeu = new Jeu(banqueMock);
        Joueur joueurMock = mock(Joueur.class);
        De DeMock1 = mock(De.class);
        De DeMock2 = mock(De.class);
        // Act
        jeu.fermer();
        // Assert
        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueurMock, DeMock1, DeMock2));
    }
    @Test
    public void testJoueurInsolvable_q5() throws JeuFermeException, DebitImpossibleException {
        // Arrange
        Banque banqueMock = mock(Banque.class);
        when(banqueMock.est_solvable()).thenReturn(true); // La banque est solvable

        Joueur joueurMock = mock(Joueur.class);
        when(joueurMock.mise()).thenReturn(10); // Mise du joueur
        doThrow(DebitImpossibleException.class).when(joueurMock).debiter(anyInt()); // Simulation du joueur insolvable

        InterfaceJeu jeu = new Jeu(banqueMock);
        De de1Mock = mock(De.class);
        De de2Mock = mock(De.class);
        // Act
        try {
            jeu.jouer(joueurMock, de1Mock, de2Mock);
        } catch (DebitImpossibleException e) {
            // Expected exception, do nothing
        }
        // Assert
        verify(de1Mock, never()).lancer();
        verify(de2Mock, never()).lancer();
        // Vérifie que les dés ne sont pas utilisés pour calculer la somme lorsque le joueur est insolvable
    }

    @Test
    public void testBanqueInsolvableApresGain() throws JeuFermeException, DebitImpossibleException {
        // Arrange
        Banque banque = new BanqueImpl(20, 30); // Banque avec un fond de 20 et un fond minimum de 30
        Joueur joueur = mock(Joueur.class);
        when(joueur.mise()).thenReturn(10); // Mise du joueur

        InterfaceJeu jeu = new Jeu(banque);
        De de1 = mock(De.class);
        De de2 = mock(De.class);
        when(de1.lancer()).thenReturn(3); // Simulation du lancer de dé 1
        when(de2.lancer()).thenReturn(4); // Simulation du lancer de dé 2

        // Act & Assert
        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueur, de1, de2));
    }


}
