package com.akeb.TP2.exo3;

public class UserService {
    private final UtilisateurApi utilisateurApi;
    
    public UserService(UtilisateurApi utilisateurApi) {
        this.utilisateurApi = utilisateurApi;
    }
    
    public void creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
        if (utilisateur == null || estInvalide(utilisateur)) {
            throw new ServiceException("Validation de l'utilisateur échouée");
        }
        
        boolean resultat = utilisateurApi.creerUtilisateur(utilisateur);
        
        if (resultat) {
            int id = utilisateurApi.getUtilisateurId(utilisateur);
            utilisateur.setId(id);
        }
    }
    
    private boolean estInvalide(Utilisateur utilisateur) {
        return utilisateur.getEmail() == null || 
               utilisateur.getEmail().isEmpty() || 
               !utilisateur.getEmail().contains("@");
    }
}
