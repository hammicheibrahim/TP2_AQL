import org.example.ServiceException;
import org.example.UserService;
import org.example.Utilisateur;
import org.example.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;
    @Captor
    private ArgumentCaptor<Utilisateur> utilisateurArgumentCaptor;
    @Test
    public void testCreerUtilisateur_exo2() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        // TODO : Configuration du comportement du mock, utiliser la
         //directive « when » avec sa méthode « thenReturn »
        doNothing().when(utilisateurApiMock).creerUtilisateur(utilisateur);
        // TODO : Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);
        // TODO : Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);
        // TODO : Vérification de l'appel à l'API
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
    }
    @Test
    public void testCreerUtilisateur_Exception_exo3_1() throws ServiceException {
        // Configuration du comportement du mock pour lever une exception ServiceException
        doThrow(new ServiceException("Echec de la création de l'utilisateur")).when(utilisateurApiMock).creerUtilisateur(any(Utilisateur.class));

        UserService userService = new UserService(utilisateurApiMock);
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        try {
            userService.creerUtilisateur(utilisateur);
        } catch (ServiceException e) {
            // Vérification que l'exception ServiceException est bien levée et que son message est correct
            assert e.getMessage() != null && e.getMessage().equals("Echec de la création de l'utilisateur");
        }
    }

    @Test
    public void testCreerUtilisateur_Validation_Error_exo3_2() throws ServiceException {
        // Configuration du comportement du mock pour lancer une exception ServiceException lors de la validation
        doThrow(new ServiceException("Erreur de validation")).when(utilisateurApiMock).creerUtilisateur(any(Utilisateur.class));

        UserService userService = new UserService(utilisateurApiMock);

        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        try {
            userService.creerUtilisateur(utilisateur);
        } catch (ServiceException e) {
            // Vérification que l'exception ValidationException est bien levée et que son message est correct
            assert e.getMessage() != null && e.getMessage().equals("Erreur de validation");
            // Vérification que la méthode creerUtilisateur n'a jamais été appelée
            verify(utilisateurApiMock, never()).creerUtilisateur(utilisateur);
        }
    }

    @Test
    public void testCreerUtilisateur_exo3_3() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        int idUtilisateur = 123;
        //  TODO: Configuration du mock pour renvoyer l'ID
        doAnswer(invocation -> {
            Utilisateur capturedUtilisateur = invocation.getArgument(0);
            capturedUtilisateur.setId(123); // Setting a fake ID
            return null;
        }).when(utilisateurApiMock).creerUtilisateur(utilisateur);
        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);
        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);
        // TODO: Vérification de l'ID de l'utilisateur
        assert utilisateur.getId() == idUtilisateur;
    }

    @Test
    public void testCreerUtilisateur_exo3_4() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        // Capture des arguments passés à la méthode creerUtilisateur du mock
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);

        // Mock the behavior of creerUtilisateur method to return true
        doNothing().when(utilisateurApiMock).creerUtilisateur(any(Utilisateur.class));

        // Appel de la méthode à tester
        UserService userService = new UserService(utilisateurApiMock);
        userService.creerUtilisateur(utilisateur);

        // Capture de l'argument
        verify(utilisateurApiMock).creerUtilisateur(argumentCaptor.capture());
        Utilisateur utilisateurCapture = argumentCaptor.getValue();

        //TODO : Vérification des arguments capturés, utiliser les getters de
        ////l’objet utilisateurCapture
        assert utilisateurCapture.getNom().equals(utilisateur.getNom());
        assert utilisateurCapture.getPrenom().equals(utilisateur.getPrenom());
        assert utilisateurCapture.getEmail().equals(utilisateur.getEmail());
    }

}