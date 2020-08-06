Partie Front-End

Les interfaces graphiques ont été implémentées avec des ConstraintLayouts. Ces layouts sont très efficaces 
pour réaliser rapidement des interfaces responsives complexes avec une hiérarchie simple.

La navigation entre les écrans "home" et "favoris" se fait au sein de l'activité principale de l'application et de
ses fragments afin d'apporter un gain de performance. Un écran supplémentaire correspondant au profil utilisateur a été 
ajouté afin de lui permettre de se déconnecter.

Toutes les ressources nécessaires au fonctionnement de l'application sont présentes dans le dossier /res.
Les images du projet ont été récupérées et l'utilisation des vecteurs a été privilégié, permettant d'obtenir
une bonne qualité d'image indépendamment de la taille du téléphone.

Partie Back-End

Pattern et Librairies utilisées : 

Le design pattern utilisé est le MVVM avec l'utilisation des Architectures Components (LiveData, lifecycles, ViewModel, Room).
Cette architecture est aujourd'hui considéré comme l'une des plus optimale dans le développement mobile Android, 
notamment lorsqu'il s'agit de persister des données et de communiquer régulièrement entre un serveur et une base de
données afin d'actualiser l'UI.
 
Les appels au serveur sont réalisés par Retrofit. Cette librairie permet de réaliser des requêtes HTTP 
de manière asynchrone et apporte une facilité dans la gestion des erreurs, rendant l'application plus robuste.
Rx Java est également combiné avec Retrofit afin de faciliter le traitement des données récupérées.

La base de données utilisée est Room puisqu'elle permet la manipulation des LiveData. L'UI est alors immédiatement informé d'un changement 
de données en base. Room présente également une facilité d'utilisation grâce aux annotations disponibles.

La librairie d'injection de dépendances Dagger2 est également intégré au projet, rendant l'application plus maintenable.


Les fonctionnalités en place :

Login : 

Les utilisateurs pouvant se connecter à l'application sont accessibles via l'appel réseau suivant mis en place : https://next.json-generator.com/api/json/get/4JcjYCeWY

Exemple d'utilisateur valide (email = pierre@gmail.com , password = password1)

Gestion des favoris :

La mise en favoris des livres est gérée avec la bdd où une table "favoris" a été créée. Au niveau de l'UI, l'utilisateur peut 
mettre en favoris un livre au niveau de la vue de détail en cliquant sur la petite icône en dessous du nom de l'auteur. Au clic, l'icône s'actualise. 
Les données persistent après un redémarrage de l'application.

Gestion de la déconnexion :

La déconnexion est gérée avec les SharedPreferences stockant un booléen initialisé à "true" lorsque l'utilisateur se connecte puis à "false" lors de la déconnexion.

Amélioration possibles :

- Stockage des images en local : Les images sont aujourd'hui téléchargées avec la librairie Glide prenant en paramètre l'url stockée en base. 
Afin de rendre l'application totalement fonctionnelle en mode déconnecter, le stockage des images dans un fichier en local serait judicieux.
- La mise à jour des icônes favoris sur l'écran "home" lorsqu'un élément est supprimé ou ajouté de la liste
- La mise en place de sessions utilisateur avec création d'une table utilisateur en bdd afin de gérer leurs propres favoris. 
En effet, la version actuelle de l'application partagent les mêmes favoris pour tous les utilisateurs.










