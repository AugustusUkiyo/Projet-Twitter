Guillaume Neupont 
Dao Quoc Hiep 

Projet Twister.

La partie serveur se trouve dans le répertoire Serveur.
On accède aux classes que nous avons codé en suivant : /Serveur/TwisterGN&DH/src/
On accède aux fichier web.xml en suivant : /Serveur/TwisterGN&DH/WebContent/WEB-INF/
Le projet doit être exporté sur TOMCAT sous le nom : "TwisterGN&DH" pour fonctionner avec la partie Client.

La partie Client se trouve dans le répertoire Client.
src contient tous les fichiers que nous avons écrit.
Néanmoins pour le faire fonctionner, nous devions aller dans /tmp/ pour ensuite créer un répertoire. Depuis ce répertoire appeler "create-react-app application" et aller dans le répertoire créé. Il suffit alors de remplacer le répertoire src créé par celui de notre projet, d'aller dans ce nouveau src, de tapper "npm install axios" puis "npm start".
Nous ne pouvions pas mettre tout le répertoire créé par "create-react-app" directement dans le rendu de projet car il prennait trop de place.

Une très grande partie de notre projet a été réalisée avec comme navigateur support google chrome. De ce fait il est conseillé d'utiliser le même pour voir correctement le résultat.
De plus les tests des services dans la partie serveur peuvent ne plus être à jour, ils dataient du temps où l'on ne pouvait pas les tester autrement mais depuis qu'il est possible de tester avec le Client nous ne les avons pas mis à jour.

Le fichier Hiep-Guillaume-BD-ALaMain.sql permet de créer la base de donnée SQL avec des copier/coller et comporte des instructions pour implémenter la base de donnée MongoDB.

nom de notre BD SQL : Hiep-Guillaume-BD
nom de notre BD Mongo : Hiep-Guillaume-MDB
