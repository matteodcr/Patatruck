# GPI & PLA : Le patatruck

Concept de base: un food-truck qui cuisine ses plats pendant qu'il se rend sur le lieu de livraison. Un joueur s'occupe de cuisiner les plats tandis que le second conduit le véhicule. L'aggressivité de la conduite peut se faire sentir dans la cuisine tandis que les erreurs en cuisine auront des répercussions sur le conduite.

La cuisine en question https://www.youtube.com/watch?v=LgOyYzm33dw

![illustration conceptuelle](https://media.discordapp.net/attachments/980722135058243647/981831202539446272/fritent.png?width=794&height=447)
_Illustration conceptuelle: la cuisine manque et les compteurs de part et d'autre du camion pourraient changer_

## Contraintes du projet

  * Deux joueurs: un conducteur, un cuisinier
  * Deux mondes avec des physiques différentes: la ville vue de haut en plan large avec une physique de conduite (inertie, accélération), la cuisine du camion (mouvements basiques sur une grille)
      * Des mondes qui intéragissent: le camion ramasse des ingrédients sur la route, l'accélération du camion (positive ou négative) peut pénaliser la cuisine
  * Des entités qui pululent: des cafards dans la cuisine (rare, mais ennuyeux), des piétons, des voitures dans la ville
  * Un des mondes est infini: la ville est générée procéduralement

## Ingrédients / Recettes
![](https://md.edgar.bzh/uploads/upload_524dc869caa95794e7108d0c321d82ae.png)


## Scoring / conditions de fin

* 3 minutes de temps au début
* Une commande livrée = rajoute du temps
* Score = temps que l'on réussit à survivre
* Game over si timer arrive à 0 +

## Plan du patatruck (cuisine)
![](https://md.edgar.bzh/uploads/upload_e85fdd9d29f6de1685d7d527abaa0043.png)

**Tables à sauce :** Contient de la sauce qu'on considère en quantité illimitée
**Ingrédients :** Contient un type d'ingrédient qu'on possède en quantité limitée. On peut ramasser les ingrédients sur la route
**Planches à découper :** Permet de découper certains ingrédients qui peuvent être découpés (patates, tomates, pain)
**Friteuses :** Permet de faire frire les patates découpées
**Feux de cuisson :** Permet de faire cuire la viande et les patates découpées
**Poubelle :** Permet de jeter un ingrédient (en cas d'erreur par exemple)
**Tables :** Permet de déposer un ingrédient dessus
**Livraison :** On peut déposer un ingrédient dessus s'il correspond à la recette demandée et qu'il manque encore à la composition ce la recette. Lorsque la recette est complétée et que le camion se trouve à l'endroit de livraison prévu, les ingrédients disparaissent et la recette est validée, on en obtient une nouvelle

https://docs.google.com/spreadsheets/d/1Ua8SwKIQYQbvkAa6IYdM2cNQfL4THITyxEKHs4Gwb-M/edit?usp=sharing

##  Tuiles de route

Générées procéduralement. Viewport centré sur le camion. La grille est une alternance de cellules 11x11 et 9x9 en diagonale (avec des cellules 9x11 et 11x9 qui les relient).

  * Les cellules 11x11 correspondent forcément à un bâtiment, et sont donc forcément pleines.
  * Les cellules dont un côté fait 9 pixels et l'autre 11 correspondent où bien à des routes, ou bien à des connexions entre deux bâtiments (pour former un bâtiment plus grand). Leur état est déterminé par le générateur procédural.
  * Les cellules 9x9 correspondent à des croisement entre des routes, ou bien à des connexions entre des bâtiments si les 4 cellules l'entourant sont également des connexions entre des bâtiments. Leur état est donc dérivé.

Pour résumer, seul l'état des cellules 9x11 et 11x9 a besoin d'être généré, les états des autres cellules sont constants ou dérivés. L'algorithme de génération est en cours de conception.

Eléments de la ville :

  * Bâtiments (alignés sur la grille) :
      * Carré 1x1
      * Carré 2x2
      * Ligne droite 2x1 (vertical & horizontal)
      * Ligne droite 3x1 (vertical & horizontal)
      * "L" 2x2 (bas-droite, bas-gauche, haut-gauche, haut-droite)
      * _Certains ont plusieurs variantes visuelles_
  * Entités
      * Plots de signalisation
      * Etals de marché : donnent des resources lors d'une collision avec un véhicule
      * Ralentisseurs : mélange les items dans le camion
      * Verglas : change la physique du camion
      * Nid de poule : échange le conducteur et le cuisinier
      * Si ralentisseur trop rapide + item par terre dans camion : impacte la vision de la route

## Automates/entités dans le camion

#### Cafards
``` haskell
Cafard(DeplacementN){
 * (DeplacementN): 						
 | Cell(H,@) 	? Explode() :()				//Le cafard regarde si le joueur est sur sa case, si oui il meurt
 | Cell(N,@) 	? :(DeplacementS)				//Le cafard regarde si le joueur est sur la case nord, si oui il se déplace vers le sud pour l'éviter
 | Cell(H,P)	? 4%Pick(H) / 48%Pop / 48%Wizz  :(_)		//Le cafard regarde si la case actuelle contient un pickable, si oui il a 4% de chances de le prendre
 | Cell(N,V/A)	? Move(N) :(_)					//Le cafard regarde si la case nord est vide ou possède un cafard, il se déplace vers le nord
 | True      	? Turn(S) :(_)					//Dans ce cas là, le cafard ne pourra pas avancer vers le nord et se tourne vers le sud
 
 * (DeplacementS): 
 | Cell(S,@) 	? Explode() :()				//Le cafard regarde si le joueur est sur sa case, si oui il meurt
 | Cell(S,@)	? :(DeplacementN)				//Le cafard regarde si le joueur est sur la case sud, si oui il se déplace vers le nord pour l'éviter
 | Cell(H,P)	? 4%Pick(H) / 48%Pop / 48%Wizz  :(_)		//Le cafard regarde si la case actuelle contient un pickable, si oui il a 4% de chances de le prendre
 | Cell(S,V/A)	? Move(S) :(_)					//Le cafard regarde si la case sud est vide ou possède un cafard, si oui il se déplace vers le sud
 | True      	? Turn(N) :(_)					//Dans ce cas là, le cafard ne pourra pas avancer vers le sud et se tourne vers le nord
 
 * (DeplacementE): 
 | Cell(H,@)	? Explode() :()				//Le cafard regarde si le joueur est sur sa case, si oui il meurt
 | Cell(E,@)	? :(DeplacementR)				//Le cafard regarde si le joueur est sur la case Est, si oui il se déplace vers l'ouest pour l'éviter
 | Cell(H,P)	? 4%Pick(H) / 48%Pop / 48%Wizz  :(_)		//Le cafard regarde si la case actuelle contient un pickable, si oui il a 4% de chances de le prendre
 | Cell(E,V/A)	? Move(E) :(_)					//Le cafard regarde si la case Est est vide ou possède un cafard, si oui il se déplace vers l'est
 | True    	? Turn(W) :(_)					//Dans ce cas là, le cafard ne pourra pas avancer vers l'Est et se tourne vers l'ouest
 
 * (DeplacementW): 
 | Cell(H,@)	? Explode() :()				//Le cafard regarde si le joueur est sur sa case, si oui il meurt
 | Cell(W,@)	? :(DeplacementL)				//Le cafard regarde si le joueur est sur la case ouest, si oui il se déplace vers l'Est pour l'éviter
 | Cell(H,P)	? 4%Pick(H) / 48%Pop / 48%Wizz  :(_)		//Le cafard regarde si la case actuelle contient un pickable, si oui il a 4% de chances de le prendre
 | Cell(W,V/A)	? Move(W) :(_)					//Le cafard regarde si la case ouest est vide ou possède un cafard, si oui il se déplace vers l'ouest
 | True     	? Turn(E) :(_)					//Dans ce cas là, le cafard ne pourra pas avancer vers l'ouest et se tourne vers l'Est
 
 * (Dupplication): 
 | True ? 10%Egg(H) / 40%Pop / 40%Wizz :(_)			//Le cafard à 10% de chances de se duppliquer, sinon il fait des annimations graphiques
  
 * ()
 }
 
 //Pop et Wizz sont des animations graphiques
```
#### Equipement cuisine 

```haskell
Feu_cuisson(wait){
    *(wait)
    |Cell(H,P) ? Pop:(cook)
    |Cell(H,V) ?    :(wait)
    *(cook)
    |True ?     wait:(cuit)

    *(cuit)
    |Cell(H,P) ?    :(cuit)
    |Cell(H,V) ?    Wizz:(wait)
}

Friteuse(wait){
    *(wait)
    |Cell(H,P) ? Pop:(cook)
    |Cell(H,V) ?    :(wait)
    *(cook)
    |True ?     wait:(cuit)

    *(cuit)
    |Cell(H,P) ?    :(cuit)
    |Cell(H,V) ?    Wizz:(wait)
}

Garde_manger(wait){
    *(wait)
    |Cell(H,V) & GotStuff ?    Wizz:(vide)
    |True ?    :(wait)
    *(vide)
    |True ? wait:(restock)
    *(restock)
    |True ? Pop:(wait)
}

Table(vide)
    *(vide)
    |Cell(H,P) ? Pop:(plein)
    |Cell(H,V) ?    :(wait)
    *(plein)
    |Cell(H,P) ?    :(plein)
    |Cell(H,V) ? Wiz:(wait)

Table_a_sauce(wait){
        *(plein)
        |!GotStuff ? Wizz:(vide)
        |GotStuff ?   :(plein)
        *(vide)
        |!GotStuff ? :(vide)
        |GotStuff ?  Pop:(plein)
    }

Planche(vide){
    *(vide)
    |Cell(H,P) ? Pop:(occuper)
    |Cell(H,V) ?    :(wait)
    *(occuper)
    |Cell(H,P) & Key(SPACE) & Cell(F,@) ? Hit: (couper1)
    |True ?    :(occuper)
    *(couper1)
    |Cell(H,P) & Key(SPACE) & Cell(F,@) ? Hit: (couper2)
    |True ?    :(couper1)
    *(couper2)
    |Cell(H,P) & Key(SPACE) & Cell(F,@) ? Hit: (couper)
    |True ?    :(couper2)
    *(couper)
    |Cell(H,V) ? Wiz:(wait)
    |True ?    :(couper2)
}
```

#### Cuisinier = joueur
```haskell
Joueur_Droite(Mains_Vides){
  * (Mains_Vides):
  | Key(FU) ? :(Demande_Nord)
  | Key(FR) ? :(Demande_Est)
  | Key(FL) ? :(Demande_Ouest)
  | Key(FD) ? :(Demande_Sud)
  | Key(ENTER) ? :(Demande_Pick)
  
   * (Mains_Prises):
   //image mains prises
  | Key(FU) ? :(Demande_Nord)
  | Key(FR) ? :(Demande_Est)
  | Key(FL) ? :(Demande_Ouest)
  | Key(FD) ? :(Demande_Sud)
  | Key(ENTER) ? :(Demande_Drop)
  
  * (Demande_Nord):
    //image de dos
  | Cell(F,!O) ? Move(N) :(Mains_Vides)
  |True ? Pop :(Mains_Vides)
  
  * (Demande_Est):
  //image vers la droite
  | Cell(F,!O) ? Move(E) :(Mains_Vides)
  |True ? Pop :(Init)
  
  * (Demande_Ouest):
  //image vers la gauche
  | Cell(F,!O) ? Move(O) :(Mains_Vides)
  |True ? Pop :(Mains_Vides)
  
  * (Demande_Sud):
  //image de face
  | Cell(F,!O) ? Move(S) :(Mains_Vides)
  |True ? Pop :(Mains_Vides)
  
  * (Demande_Pick):
  | Cell(F,P&O) ? Pick :(Mains_Prises) //un pickable sur l'obstacle devant moi on le prend
  | True ? Pop :(Mains_Vides) //pas d'obstacle devant on ne peut pas prendre
  
  * (Demande_Drop):
  | Cell(F,!P&O) ? : (Drop) //un obstacle est diponible
  | True ? Pop :(Mains_Prises) //pas d'obstacle devant on ne peut pas poser

  *(Drop):
  | GotStuff ? Wizz :(Block) //l'aliment peut être posé ici
  | True ? Pop :(Mains_Prises) //l'aliment ne peut pas être posé ici
  
  *(Block):
  |GotPower ? :(Mains_Vides) //aliment cuit et prêt sur l'obstacle
  |True ? :(Block)
  
}

//Pop = quand on peut pas effectuer une action
//Wizz = drop

//joueur_Gauche:
//	FU=Z
//	FR=D
//	FL=Q
//	FD=S
//	ENTER=E
```

## Automates/entités sur la route

#### Camion = joueur

:::danger
TODO
:::

#### Voitures
```haskell
Voiture(Move) { -- Route : Jumpable; Croisement : Gate; Autre : Obstacle
    * (Roule) 
    | Cell(_,@) ? Pop :(Stop)
    | Cell(F,O) & Cell(R,J) ? Turn(R) :(Roule)
    | Cell(F,O) & Cell(L,J) ? Turn(L) :(Roule)
    | Cell(H,G) ? 10%Turn(R) / 10%Turn(L) / Move(F) :(Roule)
    | True ? Move(F) :(Roule)

    * (Stop) 
    | Cell(_,@) ? :(Stop)
    | True ? Wizz :(Roule)
}
```
#### Stand de marché 

:::danger
TODO
:::


#### Cône de signalisation autour du marché
```haskell
TrafficCone(Still) {
  * (Still):
  | Cell(H,_) ? Pop :()
  | True ? Wizz :(Still)
  * ()
}
```
<BLOCKQUOTE>
Le cone ne fait rien mais se détruit s'il touche une autre entité.
</BLOCKQUOTE>

#### (Piéton ?)

## Effet de la cuisine sur la conduite = Doublure

Chaque erreur sur la cuisson aura un impact sur (l'automate de) la conduite 
* Erreur de friture : flaque d'huile donc plus de freins
* Erreur sur la cuisson de la viande : fumée donc obligé d'aller doucement (ralentit)

## Diagramme UML 

A venir

