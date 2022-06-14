# PLA : Le Patatruck — Contrat

## Brève description

Nous jouons un cuisinier et un chauffeur d'un camion de livraison de plat préparé, un joueur doit préparer les commandes pendant que l'autre joueur doit se rendre au lieu de livraison.
Les deux joueurs doivent donc collaborer pour livrer les commandes en temps imparti.
Loin de jouer indépendamment, l'agressivité de la conduite d'un des joueurs pourra avoir des effets négatifs dans la cuisine, tandis que la maladresse du cuisinier peut impacter la conduite de l'autre.

![illustration](https://media.discordapp.net/attachments/980722135058243647/981831202539446272/fritent.png?width=794&height=447)

_Illustration: la cuisine est ici vide et il manque un affichage de la recette en cours_

## Scènes

Le jeu est divisé en deux « scènes » superposées : la cuisine et la ville. Ces deux scènes ont pour point commun d'être chacunes représentées par une grille de « tuiles » carrées. Cependant, les grilles ne se comportent pas de la même manière : la taille des cellules diffère, l'origine (la cellule 0,0) peut être décalée d'un certain nombre de pixels, et des overlays spécifiques peuvent être affichés.

Les « tuiles » sont des entités, dont la spécificité est d'être alignées sur des cellules de la grille. Chaque cellule -- identifiée par des coordonnées entières x,y -- contient ou bien une tuile, ou bien aucune tuile (`null`). La grille n'a pas de taille, elle se contente de récupérer dynamiquement le contenu des tuiles visibles à un moment donné.

### Cuisine

La partie cuisine est constituée d'une grille de taille 4x10, constituée de cellules 13x13, en périphérie de laquelle sont placé des blocs « équipement de cuisine » avec lesquels le joueur pourra interagir pour préparer les recettes.

Certains de ces équipements sont destinés à la combinaison et la transformation des ingrédients, d'autres au stockage.

**Tables à sauce :** Contient de la sauce qu'on considère en quantité illimitée
**Ingrédients :** Contient un type d'ingrédient qu'on possède en quantité limitée. On peut ramasser les ingrédients sur la route (marché)
**Planches à découper :** Permet de découper certains ingrédients qui peuvent être découpés (patates, tomates, pain)
**Friteuses :** Permet de faire frire les patates découpées
**Feux de cuisson :** Permet de faire cuire la viande et les patates découpées
**Poubelle :** Permet de jeter un ingrédient (en cas d'erreur par exemple)
**Tables :** Permet de déposer un ingrédient dessus
**Livraison :** On peut déposer n'importe quel item sur chaque bac à livraison. Lorsque le camion se trouve à l'endroit de livraison prévu, les ingrédients disparaissent et la recette est validée, on en obtient une nouvelle

Pour cuire et découper le joueur devra effectuer des interactions clavier, qui en cas d'erreurs aurons une influence sur la conduite:
- rater la cuisson: apparition de fumée qui ralentit le camion pendant un moment (limite de vitesse).
- rater la friture: impossibilité de freiner pendant un moment.

Des évènements aléatoires pourront également avoir lieu :
- apparition de cafard sur le sol de la cuisine. Il faut les écraser sinon ils se multiplient (avec egg) et ont une faible chance de voler de la nourriture quand ils sont proches d'un plat (pomme de terre, burger,...).

### Ville

Dans la partie ville, le deuxième joueur conduit un camion pour se rendre au lieu de livraison. Au cours de son trajet, il devra éviter des obstacles et des voitures. Dans le cas contraire, les conséquences se feront sentir dans la cuisine. Il pourra également récupérer des ingrédients pour le cuisinier au cours de son chemin en percutant des entités "marché".

- percuter une voiture: arrêt du camion
- rouler sur un dos d'âne (trop vite): changement des emplacements des équipements dans le camion
- rouler sur un marché: gain d'ingrédients
    - la direction du marché le plus proche et de la livraison sera affichée par une flèche en bordure d'écran (si pas présent dans le viewport)

Le conducteur peut utiliser sa touche d'action pour échanger de véhicule avec un camion devant lui, afin de récupérer les ingrédients de ce dernier (et de démontrer la capacité d'échange d'automates).

La ville est affichée dans un viewport, ainsi seul une partie de la ville est chargé à chaque instant pour alléger la charge sur le programme.

Lorsque on roule sur une tuile verglas on change le stunt du camion pour une physique moins précise que d'habitude. On a donc 2 types de stunt : `verglas` et `classique`

#### Génération procédurale

La ville est infinie et générée procéduralement. L'algorithme n'est pas encore défini pour le moment, mais il mêlera probablement des générateurs de nombres pseudo-aléatoires avec graine, des fonctions de bruit et peut-être des diagrammes de Voronoï.

#### Grille de la ville

La grille de la ville est très particulière. Les routes sont plus fines (9px) que les bâtiments (11px), et les routes et les bâtiments sont en alternance sur les deux axes.

Il y a donc :
* des cellules 9x9 à l'intersection entre deux routes
* des cellules 11x11 qui correspondent à des bâtiments
* des cellules 9x11 et 11x9 qui correspondent soit à des routes, soit à une jonction entre les deux cellules "bâtiments" de part et d'autre (des côtés de taille 11)

Par souci de simplicité, on représentera la grille sous la forme d'une grille carrée en groupant 4 sous-cellules: une 9x9 dans le coin supérieur gauche, une 11x11 dans le coin inférieur droit, une 9x11 (verticale) et une 11x9 (horizontale). Ainsi, on manipule uniquement des cellules carrées et de taille constante.

<center>

![illustration cellule 20x20](https://media.discordapp.net/attachments/435474546305269763/984801957300535367/cell2020.drawio.png)

</center>

#### Collisions et physique dans la ville

Chaque cellule 20x20 aura une boîte de collision spécifique selon l'état des 4 sous-cellules ET selon les cellules voisines inférieures et droite.

Les collisions seront "observées" à la fois par les automates qui contrôlent le camion et par le système de physique, qui gère son inertie et son mouvement. Au lieu de simplement incrémenter/décrémenter la position du camion, les instructions de déplacement de la voiture émises par l'automate sont directement envoyées au système de physique et sont considérées comme des forces.

## Recettes de cuisine

![](https://md.edgar.bzh/uploads/upload_524dc869caa95794e7108d0c321d82ae.png)

## Scoring / conditions de fin

Un compte à rebours permet de stopper le jeu lorsque la livraison prend trop de temps, initialisé à une valeur à décider (peut être 1-2 minutes ça va dépendre de l'équilibrage).

Il y a ensuite une infinité de rounds. À chaque round :

* Le jeu commence par nous afficher, à côté de la cuisine, une recette à effectuer. Le chauffeur voit apparaître une flèche sur un côté de l'écran qui indique la direction du point de livraison.
* Le cuisinier réalise la recette pendant que le chauffeur se rend au lieu de livraison, en passant collecter des ingrédients si besoin.
* Lorsque le camion se trouve au bon endroit et que la nourriture dans le bac de livraison de la cuisine correspond à ce qui est demandé, le round est complété et du temps additionel est ajouté au compteur.

## Fonctionnement des automates

Les actions des automates renvoient des booléens censés indiquer si l'action a pu se dérouler correctement.

Les transitions sont exécutées dans l'ordre de déclaration jusqu'à ce qu'une condition soit vérifiée. Si l'action renvoie `false`, on poursuit l'exécution des transitions jusqu'à en trouver une qui fonctionne.

Un automate est composé d'un nom et d'une liste d'états. Un état est représenté par un nom et une liste de transitions. Une transition est composée d'un état d'arrivée, d'une liste de tuple (action, pourcentage) (on en choisit une au hasard en suivant les probabilités) et d'une condition. Enfin, les actions de même nom peuvent avoir un effet unique pour chaque entité.

Les actions et conditions suivent le modèle d'une interface commune appelée IFunction. En effet, ces fonctions sont semblables, car elles renvoient un booléen. Ainsi lors de l'exécution de ces dernières durant le fonctionnement de l'automate, elles font appel à un AutomatonListener (interface permettant de décrire un comportement propre de chaque fonction pour chaque entité) qui leur appartient et exécute l'action/la condition associée.

## Attribution des automates

Nous voudrions avoir, au démarrage de notre jeu, la possibilité d'associer des automates à chaque entité. Pour ce faire, nous associerons à chaque entité un nom ainsi qu'un automate par défaut. Au lancement du jeu, un écran similaire à l'illustration ci-dessous nous permettra d'expérimenter avec des substitutions. Le contrôle se fera avec <kbd>&uarr;</kbd>, <kbd>&rarr;</kbd>, <kbd>&darr;</kbd>, <kbd>&larr;</kbd>, <kbd>ENTRÉE</kbd> pour valider et une touche à définir pour réinitialiser, avec une ligne sélectionnée à chaque instant.

![illustration](https://cdn.discordapp.com/attachments/980722135058243647/986288907429740584/automaton_selection.png)
