package com.akeb.TP2.exo3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;
    
    private UserService userService;
    
    @Before
    public void setUp() {
        userService = new UserService(utilisateurApiMock);
    }
    
    // Scénario 1: Lever une exception lors de la création de l'utilisateur
    @Test(expected = ServiceException.class)
    public void testCreerUtilisateurException() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        
        // Configuration du mock pour lancer une exception
        when(utilisateurApiMock.creerUtilisateur(utilisateur))
            .thenThrow(new ServiceException("Echec de la création de l'utilisateur"));
        
        // Appel de la méthode à tester - doit lancer une exception
        userService.creerUtilisateur(utilisateur);
        
        // Cette ligne ne doit pas être exécutée
        fail("Une exception aurait dû être lancée");
    }
    
    // Scénario 2: Tester le comportement en cas d'erreur de validation
    @Test(expected = ServiceException.class)
    public void testCreerUtilisateurValidationError() throws ServiceException {
        // Création d'un utilisateur invalide (sans @)
        Utilisateur utilisateurInvalide = new Utilisateur("Jean", "Dupont", "jeandupont-email.com");
        
        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateurInvalide);
        
        // Vérification que l'API n'a jamais été appelée
        verify(utilisateurApiMock, never()).creerUtilisateur(any(Utilisateur.class));
    }
    
    // Scénario 3: Tester le comportement avec retour d'ID
    @Test
    public void testCreerUtilisateurAvecId() throws ServiceException {
        // Création d'un nouvel utilisateur valide
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        
        // Définition d'un ID fictif
        int idUtilisateur = 123;
        
        // Configuration du mock pour renvoyer true et l'ID
        when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenReturn(true);
        when(utilisateurApiMock.getUtilisateurId(utilisateur)).thenReturn(idUtilisateur);
        
        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);
        
        // Vérification de l'ID de l'utilisateur
        assertEquals(idUtilisateur, utilisateur.getId());
        
        // Vérification des appels aux méthodes de l'API
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
        verify(utilisateurApiMock).getUtilisateurId(utilisateur);
    }
    
    // Scénario 4: Utiliser des arguments capturés
    @Test
    public void testCreerUtilisateurArgumentsCaptures() throws ServiceException {
        // Création du capteur d'arguments
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);
        
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        
        // Configuration du mock
        when(utilisateurApiMock.creerUtilisateur(any(Utilisateur.class))).thenReturn(true);
        
        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);
        
        // Capture des arguments
        verify(utilisateurApiMock).creerUtilisateur(argumentCaptor.capture());
        Utilisateur utilisateurCapture = argumentCaptor.getValue();
        
        // Vérification des arguments capturés
        assertEquals("Jean", utilisateurCapture.getPrenom());
        assertEquals("Dupont", utilisateurCapture.getNom());
        assertEquals("jeandupont@email.com", utilisateurCapture.getEmail());
    }
}
