package com.akeb.TP2.exo4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JeuTest {
    @Mock
    private Banque banqueMock;
    
    @Mock
    private Joueur joueurMock;
    
    @Mock
    private De de1Mock;
    
    @Mock
    private De de2Mock;
    
    private Jeu jeu;
    
    @Before
    public void setUp() {
        jeu = new Jeu(banqueMock);
    }
    
    // Test 1: Le cas où le jeu est fermé
    @Test(expected = JeuFermeException.class)
    public void testJouerJeuFerme() throws JeuFermeException {
        // Arrange
        jeu.fermer();
        
        // Act & Assert
        jeu.jouer(joueurMock, de1Mock, de2Mock);
    }
    
    // Test 2: Le cas où le joueur est insolvable
    @Test
    public void testJouerJoueurInsolvable() throws JeuFermeException, DebitImpossibleException {
        // Arrange
        int mise = 100;
        when(joueurMock.mise()).thenReturn(mise);
        doThrow(new DebitImpossibleException("Joueur insolvable")).when(joueurMock).debiter(mise);
        
        // Act
        jeu.jouer(joueurMock, de1Mock, de2Mock);
        
        // Assert
        verify(joueurMock).mise();
        verify(joueurMock).debiter(mise);
        // Vérifier que le jeu ne touche pas aux dés
        verifyNoInteractions(de1Mock, de2Mock);
        // Vérifier que la banque n'a pas été créditée
        verifyNoInteractions(banqueMock);
    }
    
    // Test 3: Le cas où le joueur perd (somme != 7)
    @Test
    public void testJouerJoueurPerd() throws JeuFermeException, DebitImpossibleException {
        // Arrange
        int mise = 100;
        when(joueurMock.mise()).thenReturn(mise);
        when(de1Mock.lancer()).thenReturn(2);
        when(de2Mock.lancer()).thenReturn(2); // Somme = 4 != 7
        
        // Act
        jeu.jouer(joueurMock, de1Mock, de2Mock);
        
        // Assert
        verify(joueurMock).mise();
        verify(joueurMock).debiter(mise);
        verify(banqueMock).crediter(mise);
        verify(de1Mock).lancer();
        verify(de2Mock).lancer();
        // Vérifier que le joueur n'a pas été crédité
        verify(joueurMock, never()).crediter(anyInt());
        // Vérifier que la banque n'a pas été débitée
        verify(banqueMock, never()).debiter(anyInt());
        // Vérifier que le jeu est toujours ouvert
        assertTrue(jeu.estOuvert());
    }
    
    // Test 4: Le cas où le joueur gagne (somme = 7) et la banque reste solvable
    @Test
    public void testJouerJoueurGagneBanqueSolvable() throws JeuFermeException, DebitImpossibleException {
        // Arrange
        int mise = 100;
        when(joueurMock.mise()).thenReturn(mise);
        when(de1Mock.lancer()).thenReturn(3);
        when(de2Mock.lancer()).thenReturn(4); // Somme = 7
        when(banqueMock.est_solvable()).thenReturn(true);
        
        // Act
        jeu.jouer(joueurMock, de1Mock, de2Mock);
        
        // Assert
        verify(joueurMock).mise();
        verify(joueurMock).debiter(mise);
        verify(banqueMock).crediter(mise);
        verify(de1Mock).lancer();
        verify(de2Mock).lancer();
        verify(banqueMock).debiter(2 * mise);
        verify(joueurMock).crediter(2 * mise);
        verify(banqueMock).est_solvable();
        // Vérifier que le jeu est toujours ouvert
        assertTrue(jeu.estOuvert());
    }
    
    // Test 5: Le cas où le joueur gagne (somme = 7) et la banque devient insolvable
    @Test
    public void testJouerJoueurGagneBanqueInsolvable() throws JeuFermeException, DebitImpossibleException {
        // Arrange
        int mise = 100;
        when(joueurMock.mise()).thenReturn(mise);
        when(de1Mock.lancer()).thenReturn(3);
        when(de2Mock.lancer()).thenReturn(4); // Somme = 7
        when(banqueMock.est_solvable()).thenReturn(false);
        
        // Act
        jeu.jouer(joueurMock, de1Mock, de2Mock);
        
        // Assert
        verify(joueurMock).mise();
        verify(joueurMock).debiter(mise);
        verify(banqueMock).crediter(mise);
        verify(de1Mock).lancer();
        verify(de2Mock).lancer();
        verify(banqueMock).debiter(2 * mise);
        verify(joueurMock).crediter(2 * mise);
        verify(banqueMock).est_solvable();
        // Vérifier que le jeu est fermé
        assertFalse(jeu.estOuvert());
    }
    
    // Test 7: Test impliquant la banque réelle (BanqueImpl) pour comparer avec le mock
    @Test
    public void testJouerAvecBanqueReelle() throws JeuFermeException, DebitImpossibleException {
        // Arrange
        BanqueImpl banqueReelle = new BanqueImpl(500, 100);
        Jeu jeuAvecBanqueReelle = new Jeu(banqueReelle);
        
        int mise = 100;
        when(joueurMock.mise()).thenReturn(mise);
        when(de1Mock.lancer()).thenReturn(3);
        when(de2Mock.lancer()).thenReturn(4); // Somme = 7
        
        // Act
        jeuAvecBanqueReelle.jouer(joueurMock, de1Mock, de2Mock);
        
        // Assert
        verify(joueurMock).mise();
        verify(joueurMock).debiter(mise);
        verify(joueurMock).crediter(2 * mise);
        
        // Vérification de l'état de la banque réelle
        assertEquals(300, banqueReelle.getFonds()); // 500 + 100 - 200 = 300
        assertTrue(jeuAvecBanqueReelle.estOuvert()); // La banque est encore solvable
        
        // Test avec une mise qui rendrait la banque insolvable
        when(joueurMock.mise()).thenReturn(250);
        jeuAvecBanqueReelle.jouer(joueurMock, de1Mock, de2Mock);
        
        // Vérification que le jeu est fermé car la banque est insolvable
        assertEquals(-200, banqueReelle.getFonds()); // 300 + 250 - 500 = 50
        assertFalse(jeuAvecBanqueReelle.estOuvert());
    }
}
