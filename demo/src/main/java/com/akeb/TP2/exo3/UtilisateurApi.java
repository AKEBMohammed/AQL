package com.akeb.TP2.exo3;

public interface UtilisateurApi {
    boolean creerUtilisateur(Utilisateur utilisateur) throws ServiceException;
    int getUtilisateurId(Utilisateur utilisateur);
}
