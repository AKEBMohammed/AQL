package com.akeb.TP2.exo4;

public class Jeu {
    private Banque banque;
    private boolean ouvert;
    
    public Jeu(Banque laBanque) {
        this.banque = laBanque;
        this.ouvert = true;
    }
    
    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        // Vérifier si le jeu est ouvert
        if (!estOuvert()) {
            throw new JeuFermeException("Le jeu est fermé");
        }
        
        // Récupérer la mise du joueur
        int mise = joueur.mise();
        
        try {
            // Débiter le joueur de sa mise
            joueur.debiter(mise);
            
            // Créditer la banque de la mise
            banque.crediter(mise);
            
            // Lancer les deux dés
            int valeurDe1 = de1.lancer();
            int valeurDe2 = de2.lancer();
            int somme = valeurDe1 + valeurDe2;
            
            // Vérifier si le joueur a gagné (somme = 7)
            if (somme == 7) {
                // Le joueur gagne : la banque paie deux fois la mise
                int gain = 2 * mise;
                banque.debiter(gain);
                joueur.crediter(gain);
                
                // Vérifier si la banque est encore solvable
                if (!banque.est_solvable()) {
                    fermer();
                }
            }
            // Si somme != 7, le joueur a perdu sa mise et le jeu continue
            
        } catch (DebitImpossibleException e) {
            // Le joueur est insolvable, pas besoin de faire d'autres opérations
            // Le jeu continue
        }
    }
    
    public void fermer() {
        this.ouvert = false;
    }
    
    public boolean estOuvert() {
        return ouvert;
    }
}
