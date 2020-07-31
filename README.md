
# Tests de recrutement mobile

<img src="https://raw.githubusercontent.com/smartnsoft/flutter-test/master/documentation/splash.png" width="120" height="260" hspace="20"/> <img src="https://raw.githubusercontent.com/smartnsoft/flutter-test/master/documentation/login.png" width="120" height="260" hspace="20"/> <img src="https://raw.githubusercontent.com/smartnsoft/flutter-test/master/documentation/list.png" width="120" height="260" hspace="20"/> <img src="https://raw.githubusercontent.com/smartnsoft/flutter-test/master/documentation/favorites.png" width="120" height="260" hspace="20"/> <img src="https://raw.githubusercontent.com/smartnsoft/flutter-test/master/documentation/detail.png" width="120" height="260" hspace="20"/>

## Pré-requis

Test android: Kotlin

Test iOS: Swift, utilisation des xibs demandés


## Introduction

Ce test a pour objectif d'évaluer les compétences d'un candidat développeur mobile.
Il sera demandé au candidat de réaliser une petite application avec certaines fonctionnalités précises, listées ci-dessous.

> Le candidat est entièrement libre de l'architecture choisie, des normes de codes mises en place ainsi que des packages utilisés ou non.

En revanche, les choix devront être argumentés dans un fichier `arguments.md` que nous invitons le candidat à créer à la racine du projet.

## Evaluation

Etant donné que le candidat est libre de ses choix, nous estimons que l'application livrée est conforme à sa vision du développement mobile en général.

De notre côté, nous évaluerons le résultat sous différents aspects :

* respect du fonctionnel attendu et des consignes
* qualité du code 
* architecture mise en place par rapport à la problématique
* maintenabilité de la solution
* responsivité de l'interface graphique sur différentes tailles de téléphone
* respect des maquettes et soin apporté à l'UI et à l'UX, gestion des erreurs, du chargement, etc.
* bonne utilisation de git

> Tous les éléments sont importants ici, ne pas se fier à l'ordre de la liste.
> En effet, la gestion des erreurs et des contenus vide, par exemple, nous semble dans une application mobile être très importants pour la navigation utilisateur, il est donc capital d'y apporter une attention particulière.

Rappelons que **tout choix peut être intéressant à condition qu'il soit argumenté et valable dans un contexte donné**.
Le candidat a donc tout intérêt à remplir le fichier `arguments.md` avec le maximum d'informations.

Il est important que le candidat construise une application respectant le plus possible les maquettes fournies.
Selon le niveau du candidat, il n'est pas nécessaire de tout faire, mais le travail de développeur est tout de même beaucoup basé sur le respect des maquettes auxquelles nous accordons beaucoup d'importance.

Plus l'application respectera ces maquettes, meilleure sera l'évaluation !

> L'accès aux maquettes et aux ressources associées vous a normalement été communiqué. Si ce n'est pas le cas, merci de revenir vers nous.

## Résultat attendu
L'application à développer est une application basique mais représentant des fonctionnalités récurrentes en développement mobile.

### 1. Un système d'authentification persistente
Il est demandé au candidat de mettre en place une **fausse** identification.
L'idée est d'avoir, au démarrage de l'application, un **splashscreen**, puis, selon si l'utilisateur est connecté ou non :

* un écran de Login
* un écran "connecté" : la Home

Le login peut tout à fait être mocké, il est cependant nécessaire de feindre un appel réseau en utilisant par exemple un :
> DispatchQueue.main.asyncAfter(deadline: DispatchTime.now() + 1.0) {}

L'application doit mettre en place une **connexion persistante**.
Ce sera donc au moment du splashscreen que l'on vérifiera si l'utilisateur est connecté ou non, et qu'on affichera la vue correspondante.

### 2. Une fois l'utilisateur connecté

La Home de l'application se compose en **2 écrans** portés par une **BottomBar** :
> Vous pouvez utiliser une UITabBarController native pour l'exercice.

* une double liste des livres qui amènent vers un détail
* la liste des favoris qui amènent vers un détail

#### a. Les listes des livres et la vue de détail

Il est demandé au candidat d'afficher sur cet écran la liste des livres qu'il devra récupérer via le webservice **[https://next.json-generator.com/api/json/get/Vy0sPkEvO?delay=1200](https://next.json-generator.com/api/json/get/Vy0sPkEvO?delay=1200)**

Il s'agit en fait de 2 listes, l'une verticale et l'autre horizontale, avec des représentations graphiques spécifiques pour un même livre.

Au clic sur un élément de la liste, une **vue de détail** du livre en question doit-être ouverte.

La vue de détail d'un livre doit rappeler les éléments du livre, comme sur la maquette.
Elle doit surtout contenir un moyen de **mettre en favoris** le post en question et un moyen de **revenir en arrière**, à la liste des livres donc.

> Sur les maquettes, nous voyons qu'il est possible de mettre un livre en favoris depuis la liste.
> Cette fonctionnalité est en fait un bonus (voir "Aller plus loin").

#### b. La liste des favoris  et la vue de détail

Vous l'avez donc compris avec l'écran de détail d'un livre (ou depuis la liste, pour ceux qui font le bonus), l'application doit pouvoir gérer la mise en favoris.

Cet écran va donc afficher la liste de tous les favoris de l'utilisateur.
Au clic sur un favoris, nous retombons sur la même vue de détail que tout à l'heure sur laquelle nous pouvons donc **retirer**  l'élément des favoris.

Il est attendu qu'au retour sur la liste des favoris, cet élément ne soit donc **plus présent** dans la liste.

> Il n'est pas demandé de persister les favoris dans l'application.
> Ceux-ci pourront donc être vide à chaque démarrage de l'application.

#### c. Ajout d'un système de gestion de base de données en local

Permettre à l'utilisateur d'accéder à l'application en mode offline.

## Aller plus loin

Nous avons donc résumé plus haut toutes les fonctionnalités attendues, la méthodologie d'évaluation et les contraintes (aucune finalement, mis à part le respect des maquettes et guidelines demandées).

Bien que cette application présente déjà de nombreux défis intéressants et que la liberté offerte au candidat nous permette d'évaluer beaucoup de choses, il est possible d'aller plus loin dans les fonctionnalités si l'envie vous en prend :

* ajout d'une déconnexion
Il serait intéressant d'avoir une manière de se déconnecter de l'application à tout moment (et donc d'effacer la persistance)

* ajout de l'état favoris (ou non) dans la liste Ajouter un petit état "favoris" ou "non favoris" dans la liste des livres (voir maquettes).

* persister les favoris
L'idée est de garder en mémoire l'état des favoris et donc de les retrouver même après un redémarrage de l'application.

* ajout du Reactive dans l'application,
modifier votre code en ajoutant du RxSwift, proposer des solutions pertinentes et maintenable. Détaillez vos actions dans `arguments.md`

* Ajout de Firebase Analytics & Firebase crashlytics.
