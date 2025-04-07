# TP1 AQL - Bugs et Corrections

## Exercice 1 (Palindrome)

### Bug identifié
La classe `Palindrome` contient un bug critique dans la méthode `isPalindrome()`. Dans la boucle while, les indices sont mis à jour de manière incorrecte :
- `j++` : l'indice j est incrémenté au lieu d'être décrémenté
- `i--` : l'indice i est décrémenté au lieu d'être incrémenté

Cette erreur fait que les indices s'éloignent des extrémités de la chaîne au lieu de se rapprocher du centre. Cela provoquerait rapidement une exception `ArrayIndexOutOfBoundsException` car j dépasserait la longueur de la chaîne et i deviendrait négatif.

### Solution
La correction a été implémentée dans la classe `Exo1Correction`. Les indices sont maintenant correctement mis à jour :
- `i++` : l'indice i s'incrémente pour avancer vers la droite
- `j--` : l'indice j se décrémente pour avancer vers la gauche

Cette correction permet à l'algorithme de fonctionner comme prévu en comparant les caractères symétriques de la chaîne.
