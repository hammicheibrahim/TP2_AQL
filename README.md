exo 04 : \n
q1  :  \n
Quels objets dont dépend la classe Jeu sont forcément mockés dans un test  \n
pour automatiser jouer ? Pourquoi? \n
Les dés (De) : Ils doivent être mockés car leur lancer est aléatoire et doit être contrôlé lors du test. \n
Le joueur (Joueur) : Il doit être mocké pour simuler ses actions de mise, débit, et crédit. \n
La banque (Banque) : Elle doit être mockée pour vérifier si elle est encore solvable et pour simuler ses actions de crédit et débit.   \n
 \n
q2: \n
. Lister les scénarios (classes d’équivalence) que vous allez écrire pour tester  \n
jouer:
Scénario 1 : Le jeu est ouvert, les dés lancés donnent une somme différente de 7.
Scénario 2 : Le jeu est ouvert, les dés lancés donnent une somme égale à 7.
Scénario 3 : Le jeu est fermé.
Scénario 4 : Le joueur est insolvable
q4:
C'est un test d'état car nous vérifions si le jeu est fermé après avoir appelé la méthode fermer.
q5:
Pour tester que le jeu ne touche pas aux dés, nous devons vérifier que la méthode lancer des dés n'est pas appelée lors du test. 
C'est un test d'état car nous vérifions si le jeu reste ouvert lorsque le joueur est insolvable.

