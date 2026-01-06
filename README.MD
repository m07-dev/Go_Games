# Jeu de Go (console) — Java 17

Petit projet Java qui implémente un **jeu de Go en console** (plateau “goban”), avec gestion des tours, pose de coups, détection de groupes/libertés, captures et calcul du score final.

## Pré-requis

- **Java JDK 17**
- (Optionnel) **IntelliJ IDEA** pour ouvrir le projet facilement

## Lancer le projet

### Depuis IntelliJ IDEA
1. Ouvrir le projet dans IntelliJ.
2. Repérer la classe qui contient la méthode `public static void main(String[] args)` (le point d’entrée).
3. Cliquer sur ▶️ Run.

### Depuis un terminal (si projet simple sans build tool)
> Adapte les chemins selon ton dossier `src/`.


Si ton point d’entrée n’est pas `Main`, remplace `Main` par le nom de la classe qui a le `main`.

## Règles / Gameplay (résumé)

- Deux joueurs : **Noir** et **Blanc** jouent à tour de rôle.
- Un coup consiste à entrer des coordonnées **X** et **Y**.
- Le joueur peut **PASSER** en entrant `-1`.
- Un **groupe** est un ensemble de pierres connectées orthogonalement (haut/bas/gauche/droite).
- Un groupe est **vivant** s’il possède au moins **une liberté** (case vide adjacente).
- Si un groupe n’a plus de liberté, il est **capturé** (retiré du plateau).
- Fin de partie : généralement quand les deux joueurs passent (selon ton implémentation globale).
- Score :
    - **Noir** = captures_noir + territoires contrôlés
    - **Blanc** = captures_blanc + territoires contrôlés + **0.5** (komi simplifié)

## Contrôles (saisie)

Quand c’est ton tour, le programme demande :
- une position **Y** (ou `-1` pour passer)
- une position **X** (ou `-1` pour passer)

Les entrées sont re-demandées tant que les coordonnées sont hors limites.

## Fonctionnalités principales

- Alternance des joueurs (tour par tour)
- Saisie des coups en console
- Détection récursive :
    - taille d’un groupe
    - libertés d’un groupe
- Suppression d’un groupe capturé
- Calcul du score final :
    - ajout des captures
    - exploration des zones vides pour attribuer le territoire

## Structure (indicative)

- `Jeu.java` : logique de jeu (groupes, libertés, suppression, score)
- `MethodePlateau.java` : constantes (VIDE/NOIR/BLANC) + vérification des limites + affichage/gestion du plateau (selon ton projet)
- Autres fichiers : point d’entrée (`main`) et gestion du déroulement complet de partie (selon ton projet)

## Améliorations possibles

- Interdire les coups suicides et gérer la règle du **ko**
- Sauvegarde/chargement d’une partie
- Interface graphique (Swing / JavaFX)

## Auteur

- MOHAMED FOFANA & BACHIR TAHENNI