# PLA : Patatruck --- Journal de bord

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

Reflexion et prototypage des automates du joueur, du cafard et des différentes tables.

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

### Vincent & Mathis

Après avoir réussi à définir les classes et liens utilisés, nous allons implémenter tout le système pour pouvoir tester nos entités armées d'automates.
On a également revu le placement des fichiers liés aux automates dans l'arborescence du projet.

## 12 juin

### Vincent

Finit la première implémentation (fonctionnelle) du sytème de génération d'automate utilisable par les entités, quelques points à améliorer :
- Les opérateurs sur les conditions (ex : <cond> & <cond>)
- Le générateur ne peut récupérer que les automates depuis un seul fichier (limitation de la fonction from_file générer par le JavaCC, à voir si c'est possible de récupérer depuis plusieurs fichiers )
- Gestion de la deuxième forme d'aléatoire, par exemple :
```haskell
PopOrWizz2(State1){
  * (State1): True ? Pop  :(_)
  * (State2): True ? Wizz :(_)
}
```
