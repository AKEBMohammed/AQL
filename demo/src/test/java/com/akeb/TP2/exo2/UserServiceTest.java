package com.akeb.TP2.exo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;
    
    @Test
    public void testCreerUtilisateur() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");
        
        // Configuration du comportement du mock
        // Comme la méthode creerUtilisateur est de type void, 
        // on utilise doNothing() pour configurer le comportement
        doNothing().when(utilisateurApiMock).creerUtilisateur(utilisateur);
        
        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);
        
        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);
        
        // Vérification de l'appel à l'API
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
    }
}
