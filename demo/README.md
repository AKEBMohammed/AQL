# TP1 - Testing and Bug Fixes

## Exercise 1: Palindrome Class

### Bug Identified
The `Palindrome.isPalindrome()` method contains a critical bug in the while loop index manipulation:

```java
while (i < j) {
    if (s.charAt(i) != s.charAt(j)) {
        return false;
    }
    j++;  // BUG: j should be decremented, not incremented
    i--;  // BUG: i should be incremented, not decremented
}
```

The indices `i` and `j` are supposed to move towards each other to check if characters at opposite ends match. However, the current implementation does the opposite:
- `j++` increments j (making it go beyond the string length)
- `i--` decrements i (making it go below 0)

This would cause an `ArrayIndexOutOfBoundsException` once executed.

### Coverage Tests
The three coverage criteria tests (Line, Branch, and Condition) would all encounter this bug, but the tests themselves wouldn't run successfully due to the ArrayIndexOutOfBoundsException.

The corrected implementation is available in `Exo1Correction.java`.

## Exercise 2: Anagram Class

### Bug Identified
The `Anagram.isAnagram()` method contains a critical bug in the loop that counts characters:

```java
for (int i = 0; i <= s1.length(); i++) {
    count[s1.charAt(i) - 'a']++;
    count[s2.charAt(i) - 'a']--;
}
```

The loop condition is `i <= s1.length()`, which will cause an `ArrayIndexOutOfBoundsException` on the last iteration because:
1. Array indices in Java are 0-based, so the valid range is 0 to length-1
2. On the last iteration, i = s1.length(), which is out of bounds
3. Accessing s1.charAt(s1.length()) or s2.charAt(s2.length()) throws an exception

The corrected implementation is available in `Exo2Correction.java`.

## Exercise 3: BinarySearch Class

### Bug Identified
The `BinarySearch.binarySearch()` method contains two bugs:

1. The loop condition `while (low < high)` is incorrect. It should be `while (low <= high)` to handle the case where the element might be at the last position (when low equals high).

2. The condition `array[mid] <= element` in the else-if statement is incorrect. It should be `array[mid] < element`. The current implementation could skip the target element if it's equal to the middle element but not at the middle index.

These bugs cause the method to:
- Miss elements at the position where low = high
- Potentially skip the target element due to incorrect comparison

The corrected implementation is available in `Exo3Correction.java`.

## Exercise 4: QuadraticEquation Class

### Bug Analysis
After thorough testing, no bugs were found in the `QuadraticEquation.solve()` method. The method correctly:

1. Checks if the coefficient 'a' is zero and throws an appropriate exception
2. Calculates the discriminant (delta) correctly
3. Returns null for negative discriminants (no real roots)
4. Returns a single root array for zero discriminant
5. Returns two-root array for positive discriminant
6. Uses the correct formula for calculating roots: (-b ± √delta)/(2a)

The implementation follows standard mathematical procedures for solving quadratic equations and handles all edge cases properly.

### Coverage Tests
All three coverage criteria (Line, Branch, and Condition) lead to similar test cases for this class, as the branching structure is simple and matches the conditions directly.

## Exercise 5: RomanNumeral Class

### Bug Identified
The `RomanNumeral.toRoman()` method contains two critical bugs:

1. The loop condition in the for loop is incorrect: `for (int i = 0; i <= symbols.length; i++)`. This will cause an `ArrayIndexOutOfBoundsException` on the last iteration because array indices are 0-based, and the valid range is from 0 to `symbols.length - 1`.

2. The condition in the while loop is `n > values[i]`, which should be `n >= values[i]`. This means that when `n` equals a value in the array, it won't be processed. For example, if `n = 5`, it would never add the symbol "V" to the result.

These bugs cause the method to:
- Throw an exception when accessing `symbols[symbols.length]`
- Incorrectly convert some numbers (e.g., exact values like 5, 10, etc. would be skipped and processed via smaller denominations)

The corrected implementation is available in `Exo5Correction.java`.

## Exercise 6: FizzBuzz Class

### Bug Analysis
After thorough testing, a minor inconsistency was found in the `FizzBuzz.fizzBuzz()` method:

The error message when `n <= 1` states "n must be positive", which is not entirely accurate because:
1. The number 1 is actually positive, but still gets rejected
2. A more precise message would be "n must be greater than 1"

This is a semantic issue rather than a logical bug, as the code behavior is correct - it throws an exception for values less than or equal to 1. However, the error message could be improved for clarity.

The corrected implementation is available in `Exo6Correction.java` with a more accurate error message.

### Coverage Tests
All three coverage criteria (Line, Branch, and Condition) yield similar test cases for this class due to its simple structure. Each test verifies:
1. The validation of input values (n > 1)
2. "FizzBuzz" output for numbers divisible by both 3 and 5
3. "Fizz" output for numbers divisible by 3 only
4. "Buzz" output for numbers divisible by 5 only
5. String representation of the number for all other cases

# TP2 - Mockups

# Exercice 4 : Jeu de dés - Réponses aux questions

## Question 1
**Quels objets dont dépend la classe Jeu sont forcément mockés dans un test pour automatiser jouer ? Pourquoi?**

Les objets suivants doivent être mockés dans un test unitaire pour isoler la classe `Jeu` :
- `Joueur` : Car il fournit la mise et on doit pouvoir simuler différents comportements (solvable/insolvable).
- `De` (2 instances) : Car ils produisent des valeurs aléatoires, mais pour les tests nous avons besoin de contrôler ces valeurs.
- `Banque` : Car elle gère l'argent et on doit pouvoir simuler différents états (solvable/insolvable).

Ces objets doivent être mockés car :
1. Ils ont leur propre comportement complexe qui ne fait pas partie de la classe testée
2. Ils peuvent produire des résultats non déterministes (les dés)
3. Nous voulons tester la logique du jeu indépendamment de l'implémentation de ces dépendances

## Question 2
**Lister les scénarios (classes d'équivalence) que vous allez écrire pour tester jouer.**

1. **Jeu fermé** : Le jeu est fermé, une exception `JeuFermeException` devrait être lancée.
2. **Joueur insolvable** : Le joueur n'a pas assez d'argent pour sa mise, le jeu s'arrête après le débit qui échoue.
3. **Joueur perd** : La somme des dés n'est pas 7, le joueur perd sa mise et le jeu continue.
4. **Joueur gagne et banque solvable** : La somme des dés est 7, le joueur gagne et reçoit 2 fois sa mise, la banque reste solvable.
5. **Joueur gagne et banque insolvable** : La somme des dés est 7, le joueur gagne mais la banque devient insolvable, le jeu ferme.

## Question 4
**Commencer par écrire le test le plus simple : le cas où le jeu est fermé. Est-ce un test d'état ou un test des interactions ?**

C'est un test d'état. Nous vérifions que l'état de l'objet `Jeu` (sa propriété `ouvert` à `false`) entraîne bien le comportement attendu (lancer une exception). Nous ne testons pas les interactions avec d'autres objets mais plutôt le comportement basé sur l'état interne.

## Question 5
**Tester le cas où le joueur est insolvable. Comment tester que le jeu ne touche pas aux dés ? Est-ce un test d'état ou un test des interactions ?**

Pour tester que le jeu ne touche pas aux dés lorsque le joueur est insolvable, j'utilise `verifyZeroInteractions(de1Mock, de2Mock)`, ce qui vérifie qu'aucune méthode n'a été appelée sur ces objets.

C'est un test d'interactions car nous vérifions :
1. L'interaction avec le joueur pour obtenir sa mise et tenter de le débiter
2. L'absence d'interaction avec les dés et la banque

## Différence entre les deux approches de test (Question 7)

La différence principale entre les deux approches (avec mock de la banque versus une implémentation réelle) :

1. **Test avec mock** :
   - Teste uniquement la logique du jeu en isolation
   - Utilise des assertions d'interactions (verify) pour s'assurer que la banque est appelée correctement
   - Ne teste pas le comportement réel de la banque

2. **Test avec implémentation réelle** :
   - Teste l'intégration entre le jeu et la banque
   - Permet de vérifier l'état réel de la banque après les opérations
   - Plus proche d'un scénario réel d'utilisation

L'avantage du test avec mock est qu'il est plus ciblé et isolé, ce qui facilite l'identification des problèmes dans la classe Jeu spécifiquement. Le test avec implémentation réelle est plus complet mais introduit une dépendance à l'implémentation de la Banque, ce qui peut rendre le diagnostic plus complexe en cas d'échec du test.

