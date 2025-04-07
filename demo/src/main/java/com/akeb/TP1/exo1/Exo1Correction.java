package com.akeb.TP1.exo1;

public class Exo1Correction {
    /**
     * Vérifie si une chaîne de caractères est un palindrome.
     * La méthode ignore les espaces et la casse des lettres.
     * 
     * @param s la chaîne à vérifier
     * @return true si la chaîne est un palindrome, false sinon
     * @throws NullPointerException si la chaîne est null
     */
    public static boolean isPalindrome(String s) {
        if (s == null) {
            throw new NullPointerException("String must not be null");
        }
        s = s.toLowerCase().replaceAll("\\s+", "");
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++; // Correction: i s'incrémente (avance vers la droite)
            j--; // Correction: j se décrémente (avance vers la gauche)
        }
        return true;
    }
}
