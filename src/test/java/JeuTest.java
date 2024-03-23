import org.example.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JeuTest {

    @Mock
    private Joueur joueurMock;



    @Mock
    private De deMock1;



    @Mock
    private De deMock2;

    @Mock
    private Banque banqueMock;

    @Test
    public void testJouer_JeuFerme() {
        Jeu jeu = new Jeu(banqueMock);
        jeu.fermer();
        try {
            jeu.jouer(joueurMock, deMock1, deMock2);
        } catch (JeuFermeException e) {
            // Vérification que le jeu reste fermé
            assert !jeu.estOuvert();
        }
    }

    @Test
    public void testJouer_JoueurInsolvable() throws JeuFermeException, DebitImpossibleException {
        when(joueurMock.mise()).thenReturn(10); // Mise du joueur
        when(deMock1.lancer()).thenReturn(1); // Simulation de lancer de dé
        when(deMock2.lancer()).thenReturn(2);

        Jeu jeu = new Jeu(banqueMock);
        when(banqueMock.est_solvable()).thenReturn(true); // Simulation de solvabilité de la banque

        // Simuler le joueur insolvable (débit impossible)
        doThrow(DebitImpossibleException.class).when(joueurMock).debiter(anyInt());
        jeu.jouer(joueurMock, deMock1, deMock2);

        // Vérification que les dés ne sont pas lancés
        verify(deMock1, never()).lancer();
        verify(deMock2, never()).lancer();
    }

}
