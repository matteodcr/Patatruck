# PLA : Patatruck — Journal de bord

## 4-7 juin

* Travail sur le diagramme UML
* Choix du concept et début conception

## 7 juin

Travail personnel :
* Réflexion sur le contenu du jeu
    * Cuisine
    * Hitboxes et collisions
* Ecriture de plusieurs automates pour prendre de l'avance et se familiariser avec la syntaxe
* Mise en place du dépôt
* Rédaction du README

En groupe: Election du chef d'équipe, Edgar

## 8 juin

* Changement de tout le système d'automates après avoir appris que les actions pouvaient renvoyer des valeurs booléennes

### Edgar

Dessin des sprites de la ville

### Aurélien + Elise + Mattéo

Reflexion et prototypage des automates du joueur, du cafard, et des différentes tables.

## 9 juin

### Général

Discussion avec l'encadrant

### Paul

Ajout de la possibilité d'utiliser plusieurs touches du clavier en même temps.
Ajout possibilité de déplacer 2 cowboys avec des commandes différentes sur l'écran.

### Aurélien + Mattéo + Loric

*  Package `info3.game.entity`
   Premières implémentations des classes `Direction`, `PositionF`, `Entity`, `CookEntity`.
   Ébauche d'un sprite de cafard.

* Package `info3.game.content`
  Modélisation & reflexion autour des différents items

### Elise

Première implémentation de `CafardEntity`

### Edgar

* Explication détaillée du diagramme UML au groupe
* Implémentation des fondations
    * Classes `Scene`, `CityScene`, `KitchenScene` basiques
    * Travail sur un système de graphismes qui encapsule et simplifie (via une interface `Graphics`) les usages qu'on peut avoir des graphismes. Comme notre jeu est basé sur du pixel-art, ce système gère notamment le zoom (1 pixel d'une sprite est en réalité affiché sur un carré de plusieurs pixels à l'écran pour accentuer l'aspect pixel-art et avoir une fenêtre de taille correcte). Un enum `Sprite` est utilisé pour représenter les sprites indépendamment de la bibliothèque de rendu (AWT, OpenGL ?, Android ?, etc.).
    * Je prévois d'avoir fini d'ici au lendemain

### Vincent & Mathis

Début d'implémentation du générateur d'automates, on se penche sur le fonctionnement du visiteur et notre implémentation des automates pour permettre des interactions efficaces et bien pensées entre automates et entités.

## 10 juin

### Mattéo + Aurélien + Elise

Redaction de contrat
Correction du placement des entités dans les fichiers du jeu
Ajout de PositionF aux entités
push sur master : version executable et compatible avec les autres équipes

### Elise

Création d'une liste d'entité dans scène et des fonctions addEntity et removeEntity
Implémentation de pop et wizz de CafardEntity

### Edgar + Loric

Rédaction du contrat

### Loric

Première implémentation de carEntity

### Paul

Adaptation du système de déplacement des personnages au nouveau système de graphique et séparation des 2 personnages dans 2 scènes différentes.

### Vincent + Mathis

Après avoir réussi à définir les classes et liens utilisés, nous allons implémenter tout le système pour pouvoir tester nos entités armées d'automates.
On a également revu le placement des fichiers liés aux automates dans l'arborescence du projet.

## 12 juin

### Vincent

Finit la première implémentation (fonctionnelle) du ssytème de génération d'automate utilisable par les entités, quelques points à améliorer :
- Les opérateurs sur les conditions (ex : cond & cond)
- Le générateur ne peut récupérer que les automates depuis un seul fichier (limitation de la fonction from_file générer par le JavaCC, à voir si c'est possible de récupérer depuis plusieurs fichiers )
- Gestion de la deuxième forme d'aléatoire, par exemple :

```haskell
PopOrWizz2(State1){
  * (State1): True ? Pop  :(\_)
  * (State2): True ? Wizz :(_)
}
```

## 13 juin

### Elise

modificaton de l'automate du cafard

### Aurélien

Première implémentation de `DeliveryTile`

### Mattéo

Première implémentation de `PanTile`

### Loric

Première implémentation de `FrieTile`, `SauceTable` et `BasicTable`

### Vincent & Mathis

Deuxième implémentation du générateur d'automates fonctionnelle réparant les problèmes cités dans la première implémentation (la lecture se fait pour un répertoire de fichiers GAL et on gère maintenant les opérateurs booléens sur les conditions).

### Paul

Finission de l'implémentation de l'utilisation du clavier pour déplacer des personnages sur 2 scènes différentes avec le nouveau system graphique.
Ajout du contenu `Content` faisant référence aux items et recettes.
Ajout de tests sur les recettes.

### Aurélien + Vincent + Mattéo

Travail en groupe pour associer l'automate du cuistot à `CookEntity` en implémentant les méthodes Wizz (déplacement du cuistot) et `Key` (condition)
L'objectif est de bien comprendre la pile d'appel et le flot d'exécution en partant du tick sur l'entité jusqu'à ce dernier arrive dans un (nouvel) état après la transition de l'automate.

### Edgar

Beaucoup de merges, aide à l'équipe et début du dessin d'une spritesheet contenant des lettres de l'alphabet en pixel art. Le but serait de créer le menu de seléction d'automates.

## 14 Juin : le renouveau du journal

Fin de la phase de prototypage. On a implémenté le générateur d'automate, les automates des entités, on a le squelette du modèle. On a également géré les entrées claviers multiples pour gérer les 2 joueurs sur le même clavier.

### Réflexions en cours

- Rendre le code de déplacement plus général pour pouvoir l'appliquer à d'autres automates (quelques éléments sont Hardcodés) (Mattéo)
- Problèmes : géré le fait que le déplacement de l'entit cuisinier avec l'automate associe mal les directions (gauche = bas par exemple)

### Plan de codage

#### Mattéo, Aurélien et Vincent

On va généraliser le code fait hier pour déplacer le cuisinier avec un automate. On va donc travailler sur `CookEntity` et sur le générateur d'automates pour impémenter d'autres actions et conditions. On va également essayer de gérer les collisions entre cette entité et un obstacle

#### Vincent

Implémenter quelques fonctionnalités manquantes sur le générateur d'automate notamment le second type d'aléatoire (voir journal 12 juin) et les transitions avec collisions mais sans actions.

#### Elise & Mathis

Implémenter les classes java pour chaque entité ainsi que leurs méthodes de bases.
Amélioration de leurs automates.

#### Loric & Paul

Associer les sprites des entités à la classe item (que l'on va implémenter).

Paul : travail sur les recettes (assemblages des items) et sur le graphe d'objets associés à ces dernières

#### Edgar

Implémentation d'un système de rendu de police d'écriture personalisée en pixel art, sous forme d'images. Je fais le choix de ne supporter qu'un sous-ensemble du jeu de caractères `ISO/CEI 8859-1`, qui ne contient pas unicode mais est relativement simple car il ne définit que 256 caractères courants en français.

### Tests à exécuter

- Graphisme + Modèle + CookEntity + Table + Automata : vérifier la collision avec la table `DONE`
- Assemblage des recettes (vérifie si marche si et seulement si on a les bons items pour la recette) `NOT_DONE`
- Tester les classes des nouvelles entités avec leurs automates si possible (en fonction de l'avancement des autres). `NOT_DONE`
- Tester le générateur d'automate pour des transitions sans actions `DONE` et les transitions vers un état aléatoire (avec `cafard.gal`) `NOT_DONE`
