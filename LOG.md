# PLA : Patatruck -- Journal de bord

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

Finit la première implémentation (fonctionnelle) du sytème de génération d'automate utilisable par les entités, quelques points à améliorer :
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

Implémenter quelques fonctionnalités manquantes sur le générateur d'automate notamment le second type d'aléatoire (voir journal 12 juin) et les transitions avec conditions mais sans action.

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

## 15 juin

La scène de la cuisine avance, on arrive à afficher les entités des différents équipements de la cuisine, ainsi que le cuistot et on gère les collisions de ce dernier (de façon primitive). Edgar avance sur la fonctionnalité de sélection d'automates. Du côté des ingrédients et des recettes, on a prototypé les méthodes de découpe, de cuisson et de friture ainsi que l'assemblage des ingrédients.

### Réflexions en cours

- Déplacer le code de collision de l'action `Move` et utiliser la condition `Cell` pour vérifier qu'un automate puisse se déplacer. Par exemple pour le déplacement du joueur : `Key(z) & !Cell(N,O) ? Wizz(N) : etat` (Mattéo, Aurélien et Vincent)
- Est-ce nécessaire de séparer les classes des recettes et des ingrédients de base ? -> Réponse = Non (Paul & Elise)
- Comment associer les automates configurés aux entités ? Nous allons probablement avoir une `Map<String, Automaton>` dans la classe `game`, que chaque entité pourra accéder. Les entités devront dans ce cas surcharger une méthode abstraites `getDisplayName`. C'est pas idéal d'utiliser un nom d'affichage pour ça mais c'est peut être acceptable pour nous. On pourrait aussi utiliser de la reflexion pour obtenir le nom de la dernière classe dans la hiérarchie. (Edgar)
- Il faut un moyen de définir des associations par défaut d'automate/entité. On pourrait pour cela definir une seconde méthode abstraite à implémenter qui renvoie l'automate par défaut, mais cela requiert d'avoir une instance de chaque entité dès le lancement. On peut aussi utiliser de la reflection ou définir une `Map<>` constante par défaut. (Edgar)
- Gérer la simulation si on détruit des entités sur le même tick où on veut interagir avec elle. Pour cela on peut soit implémenter une liste d'entité "buffer" sur laquelle on fait les modifs et qu'on récupère quand on a fait le tick sur toutes les entités. (Vincent)

### Plan de codage

#### Aurélien, Mattéo & Vincent

Déplacement du code de collision comme présenté dans la partie réflexion. Ajout des entités dans la scène pour pouvoir gérer la simulation avec les ticks. On a également associé les automates aux entités correctement.
Nous avons également pu impémenter les méthodes `Move` et `Egg` de l'entité `Cockroach`.
On va également faire une première implémentation d'une liste buffer sur laquelle on itère pour le tick de chaque entité, pour éviter les problèmes au cas où elle serait amené à changer lors du tick (egg ou destruction).

#### Elise

Implémentation des entity de la ville

#### Edgar

Travail sur le système de rendu de police personalisée (pixel art). Au passage, réécriture de l'implémentation de `Graphics` `AwtGraphics` afin d'ajouter un cache de textures déjà redimensionnées à la bonne taille (en tenant compte du `scaleFactor` qui donne la taille en pixel d'écran d'un "pixel" de sprite).

Le soir: travail sur la génération aléatoire de la ville. Système de chunk et affichage de batiments sur les côtés des routes.

#### Paul

Création d'une nouvelle version de content plus orientée objet qu'avant mais sans réussite.
Création d'une nouvelle version de content mais où l'on pourra ajouter les ingrédients aux recettes 1 par 1 (pas convaincu du résultat final car pas très POO mais fonctionne).
Mise à jour des tests des recettes pour supporter la nouvelle version de content.
Ajout d'un dossier pour les versions non fonctionnelles de content.

#### Mathis

Début du travail sur la scène de la ville. Vérification et correction des automates, implémentation des entités de la ville. Définition claire des fonctionnements des entités.

### Tests à réaliser

- Assemblage des recettes (voir jour précédent) `NOT_DONE`
- Vérifier interaction 2 entités lors du même tick au niveau du modèle `DONE` (+graphisme) `DONE`
- Vérifier les collisions niveau graphisme et modèle avec la nouvelle façon de faire. `DONE`
- Vérifier la méthode Egg de l'entité cafard (pas de problème de création multiple d'entités / superposition) et la bonne destruction de cette entité par le joueur (suppression visuelle + dans la liste d'entités)`DONE`

## 16 Juin

Automates de la ville finis. Apparition et destruction des cafards fonctionnels (reste à générer les cafards aléatoirement au fil de la partie et à implémenter la fonctionnalité qui permet aux cafards de voler des ingrédients).

### Réflexions en cours

- sortir les équipements de la cuisine de l'entity_list et modifier la fonction cell pour vérifier les entités dans la liste (plus petite qu'avant) et dans la scène (qui contient les tuiles de la cuisine)
- bug : carré vert (BasicTableDelivery) à l'origine du repère à enlever (n'est pas vu comme une entité)

### Plan de codage

#### Mattéo, Aurélien & Vincent

Revoir les collisions (voir réflexions). Travail sur la récupération et l'affichage d'items. Dans l'attente du travail sur les items, réalisation d'implémentation "annexes" :
- Limitation du nombre de cafards
- Affichage des sprites en fonction de la direction
- Réduction de la "vitesse" du joueur et des cafards via l'ajout d'un `timeElapsed` qui n'est pas une boucle wait().

#### Mathis

Implémentation de l'entité marché et début de travail sur le joueur camion. On commence à soulever des questions importantes sur le fonctionnement de la  ville et on trouve des réponses.
En attendant que les Item soient fonctionnels et que des tests puissent être lancés, je fais des sprites.

#### Loric

Travail sur les entités associés aux automates de la ville.

#### Loric & Vincent

Travail sur l'affichage dans la ville d'une voiture et gestion de son IA (gérer collisions avec les batiments)

+Vincent  : Réécrit les champs `gridX` et `gridY` dans `Tile` et les enlever de `Entity` et `CityTile`. Les entités n'auront accès qu'à la position en pixels mais pourront appeler des méthodes pour connaître leur grille.

#### Edgar

* Aider le reste du groupe.
* Fin du système de rendu de police
* Implémentation quasi-fonctionnelle d'un menu de selection d'automates
* Petit fix d'un rendu pas très joli

#### Elise

Travail sur content, restucturation du code

#### Elise & Paul

Travail sur les recettes et ajout de la possibilité de shuffle les éléments du camion.

### Tests à réaliser

- Re tester les collisions dans la cuisine `DONE`
- Tester incréments d'aliments dans les entités de stockage (depuis marché) `NOT_DONE`
- Orientation sur les sprites de cafard et de cook `DONE`

## 17 juin

Il nous manque toujours une classe fonctionnelle pour les items et les assemblages. On a réussi à implanter l'entité `Car` dans la ville ainsi que lier son automate, mais pour l'instant on a pas de collision dans cette scène (millimétrique et non plus par case). On a implanté une fonction pour shuffle les équipements de la cuisine.

### Réflexions en cours

- Comment gérer les collisions dans la ville ? Réimplanter `Cell` et comparer la `PositionF`  de l'entité avec la catégorie de l'entité à la `PositionF` d'à côté (quelques pixels)

### Plan de codage

#### Loric & Vincent

Ajout des collisions avec les bâtiments dans la ville.

#### Paul

Lors du shuffle, réorienter les tables dans le bon sens.

#### Matthéo et Aurélien

Nouveaux sprites et ajouts des sprites dans la scène + méthodes pour orienter facilement les sprites

#### Paul, Aurélien & Mattéo

Ajout de l'interaction entre la `StockTable` et le joueur (implémentation des méthodes de celle-ci).

#### Elise

Fin d'item et des assemblages.

#### Mathis

Attendre les items types

#### Edgar

Merge dans le master de l'écran de sélection d'automate.

### Tests à réaliser

- Vérifier visuellement que les voitures ne rentre pas en collision avec les bâtiments `REPORRTED`
- Assemblage des recettes (fichier test) `DONE`
- Vérifiez l'interaction entre la `StockTable` et le `Cook`

## 18 & 19 Juin

Les branches git sont un peu désordonnés et tout le monde n'a pas la version la plus récente du master. Le but durant le week-end est de réussir à tout récupérer sur le master pour repartir sur de bonnes bases lundi. On a également pas pu finir l'implémentation des collisions dans la ville, donc on va essayer de terminer ça pendant le week-end.

### Réflexions en cours

Voilà une liste de tout ce qu'il reste à faire pour avoir un jeu agréable :
* Système de collision millimétrique
* Physique de déplacement des véhicules dans la ville
* Implémentation de la génération aléatoire (en cours)
* Placer les ralentisseurs et les marchés dans la ville
* Gérer l'apparition et la disparition des véhicules
* Implémentation de la mécanique de transfert entre 2 camions
* Implémentation de la mécanique de doublure avec les erreurs de cuisson
* Gérer la mécanique de viewport
* Implémentation des timers (un de décompte et un pour le score)
* Implémentation du compteur de vitesse (lié à la physique)
* Créer et afficher l'UI sur l'écran (vitesse, temps et recette en cours)
* Gérer l'interaction entre les différents équipements de cuisine et le joueur
* Ecran d'accueil (logo, best score, start, select automata, quit + credits dans un coin en cours)
* Ecran de fin (logo, score de la partie, best score, replay, quit)
* Sauvegarde et relecture du/des meilleur(s) score(s)
* (Système de sons/bruitages)
* (Système d'animations)

### Plan de codage

#### Vincent

Terminer le travail sur les collisions millimétrique de la voiture. Récupérer le travail de tout le monde et le fusionner sur une nouvelle branche. Laisser à tout le monde vérifier que ça comprend/ correspond à ce qu'ils ont codé. Une fois le consensus atteint, merge sur master après vérification par Edgar.

Implémenter la base du système de commandes (recettes à réaliser)
Implémenter l'action qui permet aux cafards de voler des ingrédients dans la `StockTable`. + Gérer les interactions dans les coins du camion en fonction de l'orientation de l'entité qui va interagir.


### Tests à réaliser

- Pour la voiture, vérifier que celle-ci ne rentre pas dans des batiments et qu'elle les détecte bien `DONE`
- Pour le merge : refaire la plupart des tests précédents et vérifier que rien n'est cassé. `KINDA_DONE` `DONE le 20 juin`
- Vérifier que les cafards peuvent voler un ingrédient et que le joueur ne peut interagir que si il regarde l'équipement. `DONE`

## 20 Juin

On attend le merge sur master dans la matinée après avoir fait les dernières modifications pour se conformer aux conventions (Vincent, Mattéo, Mathis, Loric, Paul). Ensuite on va se répartir sur les différentes tâches dont on parle juste au-dessus.

### Réflexions en cours

- Comment organiser l'équipe divisée en 2 groupes (distanciel & présentiel) ? On passe par Discord pour communiquer entre tout le monde. Il faut éviter de se marcher dessus et de travailler sur les mêmes parties du code.

### Plan de codage

#### Paul

Travailler sur la physique du système de déplacement des voitures dans la ville.

#### Loric

Création et affichage du timer de jeu.

#### Mattéo

Gestion de toutes les interactions dans la cuisine.
Première version d'une cuisine permettant de réaliser toutes les recettes.
- SauceTable
- CutTile
- PanTile
- FryTile
- BasicTableTile

Réimplémentation de CookEntity avec un Assembly : modification des KitchenTile avec cette nouvelle implémentation

#### Mathis

Gérer l'apparition et la disparition des entités voitures et camions adversaires.

#### Vincent

Gérer la condition de fin de jeu, l'affichage de l'écran de fin , du score et de sa sauvegarde dans un fichier de meilleurs scores.

#### Elise

Aide sur la cuisine.
Tentative de codage de l'écran d'acceuil.

#### Edgar

🤒 _Maladie, impossibilité de travailler_

#### Aurélien

Mon objectif est de faire la préparation du scénario de la démo. Malheureusement aujourd'hui la COVID aura eu raison de moi. Je prévois de le faire demain.

### Tests à réaliser

- Vérifier que les voitures accélèrent et ralentissent correctement
- Vérifier que le timer s'affiche bien et qu'il est centré `DONE`
- Vérifier que lorsque le timer arrive à 0 on bascule sur l'écran de fin, que le score affiché est bon et que si l'on rappuie sur espace on arrive à nouveau sur le jeu (bien réinitialisé) `DONE`

## 21 Juin

La cuisine est quasi fonctionnelle et le système de physique a bien avancée. La partie se lance avec un timer et s'arrête quand il arrive à 0 en nous affichant le score (temps écoulé depuis le début) et en nous proposant de rejouer depuis un écran de fin.

### Réflexion en cours

Nouvelles tâches :
* Formattage du code (anglicisation)
* Rassembler les sprites en spritesheets
* Réorganiser les fichiers (à la toute fin)

### Plan de codage

#### Aurélien

Préparation du scénario de la démo.
Recherche et création des bruitages du jeu.

#### Vincent

Fin du travail pour sauvegarder le score. Implémentation des premiers bruitages dans le jeu avec un système qui empêche un deuxième son identique de se lancer si le premier n'est pas fini.

#### Mathis

Lister tous les sons à trouver/créer

#### Loric, Élise et Mathis

Implémentation de l'écran de début de jeu et des crédits. Ajout de la navigation entre les différents écrans.

#### Paul

Implémentation d'une nouvelle version de la physique pour l'utiliser avec les differents avatars, fix du bug du shuffle.

#### Edgar

Génération du monde aléatoire.

#### Elise
Debug de la cuisine

#### Mattéo

- DeliveryTile
- Affichage Sauce
- Debug Global
- Pouvoir livrer une recette entierement

### Tests à réaliser

- Vérifier qu'on démarre bien sur l'écran d'accueil, qu'on peut naviguer dans tous les écrans et lancer une partie, qu'à la fin de la partie on arrive sur l'écran de fin et qu'on puisse relancer une partie. `DONE`
- Vérifier qu'on peut réaliser une recette de A à Z et qu'on peut interagir avec les différents éléments de la cuisine. `DONE` (sauf la livraison)
- Vérifier que chaque physique du camion l'impacte bien `DONE`

## 22 Juin

Cuisine et physique fonctionnelle, premiers sons dans le jeu. Transitions entre tous les écrans pour démarrer, finir et relancer une partie avec sauvegarde du meilleur score.

### Réflexions en cours



### Plan de codage

#### Edgar

Génération du monde aléatoire : disposition des bâtiments et calcul des boîtes de collision

#### Vincent

Travail sur les collisions dans la ville. Lorsque le joueur contrôle le camion, il faut le faire rebondir sur les murs car sinon il a des chances de les traverser avec l'inertie.
J'ai également travaillé sur les collisions entre les différents véhicules dans la ville.

#### Paul

Mise à jour du system de physique pour répondre aux collisions et légers réglages dans les équations de mouvements. Ajout de fonctions à la physique pour pouvoir intervertir parmi les avatars plus facilement. Mise à jour de cityScene pour pouvoir utiliser un automate sur un carEntity pour déplacer le joueur dans la ville. Mise à jour du canvasListener pour pouvoir comprendre lorsqu'un automate veut détecter les flèches du clavier.

### Elise & Aurélien

Generation de recette et de sauce aléatoire.
Debug de l'ajout de sauce à une recette.

#### Loric

Affichage des recettes à réaliser. Gestion de l'échange d'automate lorsque le joueur veut changer de camion.

#### Matteo

Discussion avec Elise sur l'implementation de assembly, détection de multiples bugs liées à cette classe et correction
Merge request sur master
Equilibrage du jeu
Fix du cafard
Fix de certains automates
Play Testing
Discussion avec Mathis sur l'interaction entre les deux scènes : signaux à envoeyr/recevoir.

### Tests à réaliser

- La ville se génère d'une façon cohérente `DONE`
- Les collisions des bâtiments sont correctes dans la ville procédurale `NOT_DONE` (c'est à l'équipe véhicule de faire ça)
- Vérifier que on détecte bien les collisions entre 2 véhicules `DONE`
- Le camion contrôlé par le joueur ne doit pas passer à travers les batiments `DONE`
- Utilisation d'un automate pour déplacer le camion dans la ville et vérifier les collisions `DONE`


## 23 Juin

On a maintenant le code de génération aléatoire.

### Réflexions en cours

Il faut modifier un peu le code des collisions et gérer le viewport dans la ville procédurale.

### Plan de codage / des tâches

#### Elise & Aurélien

Ajout des frites cuites en Item final que l'on peut avoir dans les recettes.
Fix issue 14 avec le code de Graphics.
Ajout d'un bouton quitter dans le menu principal.

#### Mathis

Implémentation des DeliveryTile, et de l'interaction entre les deux scènes.

#### Paul

Création du visuel avec les recettes pour les afficher en cours de jeu.
Tentative de création des spritesheets pour regrouper les sprites mais abandon à la fin car ils n'utilisaient pas la bonne palette de couleurs et donc perte du travail.

#### Vincent

Fix pour que les entités s'affichent toujours en 0,0 de leur propre sous-graphique (qu'on place donc au bon endroit lors du render de la scène).
Récupération du code de génération aléatoire d'Edgar pour l'intégrer avec le système de déplacement et de collisions des véhicules.
Intégration du viewport qui suit le camion du joueur.
Fix des noms de variables dans la partie automate

### Mattéo

J'ai refactor le code lié à la cuisine en entier:
* code commenté
* code simplifié
* variable renommées

Résolution de certains problèmes liés aux sauces.
J'ai découvert un grand problème : je n'utilisais pas assez la puissance des automates. Il y avait trop de code dans les fonctions associées aux automates et on faisait tres peu de transisitions différentes

#### Loric

Ajout de la possibilité de changer de camion avec un échange d'automate entre le camion que l'on controle et un gérer automatiquement. Gestion de la transition de l'écran de fin vers l'écran de début. Ajout de l'arret du jeu en cas d'exception.

#### Edgar

Implémentation de collisions sur les bâtiments et gestion du dépôt.

### Tests à réaliser

- Les collisions des bâtiments sont correctes dans la ville procédurale `DONE`
- Ajout de tests dans le fichier de test des recettes et vérifier que ce dernier est validé lorsqu'on le lance `DONE`


## 24 Juin

Le groupe est au complet en présentiel pour la première fois depuis une semaine (merci le covid). La partie cuisine est finie et fonctionnelle. Il faut encore générer les marchés, les voitures et les zones de livraisons sur la ville.

### Réflexions en cours

Comment récupérer le marché le plus proche ? Faire un cache avec toutes les tuiles du marché ? (Au final pas la solution retenue)
On va également modifier la physique de déplacement car actuellement c'est trop compliqué de se déplacer dans la ville (on atteint trop vite la vitesse max et on ne peut pas faire de tout petit déplacements).

### Plan de codage / des tâches

#### Aurélien + Elise

Finitions du scénario de la démo (ajout du texte voix off)
Implémentation des sons (à finir ce week end)
Aide pour le logo et les sprites

#### Mattéo

J'ai réussi à trouver des solutions à mon problème. Utilisation de GotStuff et GotPower pour effectuer des actions différentes dans des transitions différentes. J'ai augmenté la part d'automate dans mon code ce qui le rend plus robuste

J'ai commencé à monter et filmer la vidéo.

#### Vincent

Réparation des erreurs que j'ai introduites lors des MR. (Spoiler : j'en ai créés encore plus et au final j'ai quasiment tout cassé)

Travail sur les 3 physiques pour les rendre bien distinctes et (relativement) agréable à jouer

#### Edgar

Implémentation de la création marchés dans le `WorldGenerator`, à l'aide d'un bruit OpenSimplex2. Création d'une fonction de localisation des marchés par ordre de proximité à un point donné, en prévision d'une flèche en périphérie du viewport qui indique le marché le plus proche.

#### Mathis

Affichage des flèches indiquant la zone de livraison

#### Loric
Création de sprite pour l'écran de fin. Réparation d'erreurs d'affichage

#### Paul

Création de sprite pour le pop du cafard et pour le logo du jeu. (en attente d'implémentation)

### Tests à réaliser

- Vérifier que la flèche de direction du lieu de livraison pointe bien au bon endroit et qu'elle disparaît si il apparait dans notre viewport. `DONE`
- Vérifier que les physiques sont bien distinctes les unes des autres et (relativement) bien contrôlables par le joueur. `DONE`
- Le localisateur de marché marche bien. `DONE` (la réponse est non)

## 25 & 26 Juin

Dernière ligne droite, on a dressé une liste de tout ce qu'il reste à faire d'ici à la fin du week-end.

### Réflexions en cours

* Récupérer les nouveaux sprites voiture/camion `DONE`
* Permettre aux entités associés de tourner (visuellement) `DONE`
* Gérer l'apparition des marchés, des points de livraison et des ralentisseurs sur la map `DONE`
* Gérer les fonctionnalités liées aux ralentisseurs `DONE`
* Physique à 60 FPS / 40 TPS `DONE`
* Gérer l'apparition des voitures (possiblement en passant le bon automate à CityTile et en stockant qq une sur lesquelles executee des ticks) `DONE (mais pas comme dit ici`
* Gérer la disparition des voitures/camions si elles sortent trop loin du viewport `DONE`
* 2-3 améliorations à l'écran de début / fin (permettre à l'autre joueur de se déplacer, afficher le score au début et revoir le texte à la fin) `DONE`
* Gérer le passage d'une phyisque à une autre (en appelant correctement les méthodes) lorsqu'on fait des erreurs dans la cuisine (actuellement on a que smoke, dc on peut pas savoir si c'est une erreur de cuisson = lent ou friture = rapide ) `DONE`
* Appelez la méthode addTime de Game lorsqu'on a livré une recette, avec des tps différents en fct des recettes / extras (ex: +15s tomate et salde, +10s sauce, +20s frites, +40s burger classique...).  `DONE (mais temps unique +50s`
* Voir ce qu'on fait de l'espace en dessous du timer `DONE (score)`
* Intégrer les sons manquants au jeu et essayer de voir si c'est possible de réduire le délai. `DONE (mais toujours un délai)`
* Implémenter des méthodes au niveau le plus haut possible pour le + d'actions possible pr assurer la compatibilité avec d'autres automates `DONE`
* Refaire l'affichage des recettes `DONE`
* Corriger les collisions de certains batiments en L `DONE`
* Nettoyer le code, l'anglicisé, le réorganiser (si besoin). `DONE`
* Récupérer les sprites sur quelques spritesheet aptement nommée `DONE`
* Faire la vidéo de présentation `DONE`

### Plan de codage / des tâches

#### Paul

Ajout des sprites des camions (joueur ou non) et amélioration de celui des voitures.
Rassemblement des sprites en plusieurs spritesheets.
Mise à jour du sprite des recettes lors du jeu.
Implémentation à plus haut niveau des fonctions des automates pour pouvoir l'utiliser sur plus d'entités.
Ajout d'un personnage sur les écrans de début et de fin.
Ajout d'une fonctionnalité pour regénérer le monde à la volée (changement de seed) à l'appui de la touche G.

#### Mathis

Correction et tests des zones de livraison (dans la cuisine et dans la ville).
Implémentation sommaire dee MarketEntity.

#### Aurélien & Elise

Fix des sons, ajout de quelques nouveaux sons manquants.
Dernière review du scénario de démo avec le texte de la voix off.
Relecture du code écrit pour ajouter des commentaires et réorganiser au besoin.

#### Vincent

Samedi :
* Physique à 60 FPS / 40 TPS.
* Apparition et disparition de voitures
* Améliorations sur les écrans de démarrage / de fin.
* Ajout des ralentisseurs et de leurs fonctionnalités.
* Modification de la génération de l'aléatoire dans la génération du monde.
* Travail sur les collisions entre les différents véhicules de la ville.

Dimanche
* Fin du travail sur la génération avec l'aide de Edgar
* Fix collisions des speedbumps et des marketEntity
* Ajout de la possibilité de passer à la physique No Brakes
* Intégration des derniers sons
* Génération des entités étals de marchés et de leurs sprites
* Multiples fix (livraison uniquement si le joueur était prêt du lieu de livraison, swap infinis pour restocker tous les ingrédients)

#### Loric

Gestion du changement de physique en cas de fumée.
Fix le fait que les caches de la génération du monde n'étaient pas bien vidés.

#### Mattéo

Préparation des séquences de gameplay, enregistrement du texte et montage de la vidéo de présentation.

#### Edgar

* Beaucoup de merge / rebase / nettoyage des branches
* Fix du localisateur de marché et implémentation de celui-ci dans la branche de Mathis qui ajoute les flèches de directions.
* Grand nettoyage du code du dépôt à la fin, en suivant les conseils d'IntelliJ Idea et de Checkstyle

### Tests à réaliser

- Apparition et déplacement corrects des zones de livraison `DONE`
- Affichage du cadre clignotant des zones de livraison `DONE`
- Interaction entre la cuisine et le camion dans la ville quant aux livraisons `DONE`
- Fonctionnement correct du localisateur de marché `DONE`
- Génération correctes des étals de marchés et des ralentisseurs `DONE`
- Comportement corrects des étals de marchés et des ralentisseurs `DONE`
- Les inspections d'IntelliJ Idea ne renvoient plus autant d'erreur et de warning qu'avant `DONE`