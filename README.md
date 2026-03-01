# Projet PIF (SAE 3.2) 📸
Le projet **PIF** est une suite d'outils composée d'un **convertisseur** d'images et d'un **visualisateur**. Ce projet a été réalisé dans le cadre de la **SAÉ 3.2** à l'IUT de Fontainebleau. Il permet de manipuler un format d'image spécifique (.pif) en utilisant des algorithmes de compression (Huffman) et des interfaces graphiques en Java.

## Description du projet
Le projet se divise en deux applications distinctes :
1. **Converter** : Transforme des images standards en fichiers au format `.pif` en utilisant une table de fréquences et un arbre de Huffman pour optimiser le stockage.
2. **Visualizer** : Permet d'ouvrir, d'afficher et de manipuler (zoom/déplacement) les images au format `.pif`.

## Structure du projet
```
.
├── src/        // Code source Java organisé par packages
│   └── fr/iutfbleau/pif/
│       ├── converter/    // Convertisseur
│       └── visualizer/   // Visualisateur
├── res/        // Ressources : Images de test, diagrammes et rapport du projet
├── build/      // Fichiers compilés (.class)
├── doc/        // Documentation Javadoc - généré après commande
├── Makefile    // Fichier Makefile
└── README.md   // Fichier markdown de présentation pour ce projet
```
## Compilation convertisseur
```bash
make converter # convertisseur
make visualizer # visualisateur
```
## Exécution du programme
Voici les commandes pour exécuter les programmes, la spécification du fichier n'est pas obligatoire.  
```bash
make runconv file=".res/example.png" # convertisseur
make runvisu file="./res/example.pif"  # visualisateur
```
## Création de la documentation javadoc
```bash
make javadoc
```

## Nettoyage des fichiers compilés
```bash
make clean
```

## Rapport
Le rapport du projet est disponible dans le dossier `res`, ainsi que les diagrammes de classe

## Auteurs
Ce projet a été réalisé dans le cadre d'une situation d'apprentissage et d'évaluation au sein de la formation de BUT informatique à Fontainebleau dans un but d'apprentissage uniquement. Les auteurs sont :

- [Michel TRAN](https://grond.iut-fbleau.fr/tranm/)
- [Rayan BISSON](https://grond.iut-fbleau.fr/bissonr/)
- [Emmanuel SRIVASTAVA-TIAMZON](https://grond.iut-fbleau.fr/srivasta/)