## Description de l'application

Efrei++ est un projet de gestion d'école de type clients/serveur, c'est à dire que plusieurs clients se connectent au même serveur. Le fonctionnement se déroule de la façon suivante: le client se connecte puis le serveur détermine ces droits d'utilisateurs, ce qui permet de limiter les actions de chacun. 

Ce projet a été réalisé en **Java** en utilisant la librairie graphique **Swing** ainsi que **RMI** pour la gestion de la partie réseau et **Junit** pour les tests unitaires. Ces choix nous ont permis de réaliser une interface utilisateur complète et agréable tout en utilisant le pattern **MVC** qui facilite grandement l'organisation du code. 

La sécurité a été mise en avant puisque toutes les vérifications s'effectuent coté serveur notamment la gestion des droits d'accès. Les données sont également toutes stocké dans la base de données **SQLite** du serveur afin d'assurer la sécurité des celles-ci, elles transitent ensuite vers les différents clients.

## Fonctions développées 

Un élève peut:
- Connaitre sa majeure.
- Choisir une majeure si c'est sa première connexion.
- Consulter uniquement ses notes.
- Planifier son cursus en s'inscrivant aux matières facultatives ou en s'en désinscrivant.

Un professeur peut:
- Connaitre les matières qui lui sont attribués.
- Afficher un relevé de note des matières qui lui sont attribués.
- Ajouter des notes aux élèves des matières qui lui sont attribués.

Un administrateur peut:
- Ajouter et retirer des élèves du système.
- Ajouter et retirer des professeurs du système.
- Attribuer des matières aux professeurs.
- Attribuer des élèves aux professeurs tuteurs.
- Ajouter et retirer des majeures du système.
- Ajouter et retirer des cours, en précisant à quelle majeur ils appartiennent (pouvant être catégorisé optionnels).

Un professeur (le tuteur) est alerté lorsqu'un étudiant a reçu 3 notes inférieures à la moyenne.


## Réalisation de l’application

### Utilisation de RMI

RMI ou Remote Method Invocation est une API Java qui permet d’appeler des méthodes à distance. Cette API nécessite un registre RMI sur la machine hôte.
Le principe est de définir une interface remote sur le serveur qui va contenir toutes les méthodes pouvant être appelée à distance. On écrit ensuite une classe qui va implémenter
cette interface. On doit ensuite faire en sorte que le serveur instancie cette classe, l’enregistre et lui affecte un nom sur le registre de nom RMI.
Du côté client, il suffit d’obtenir une référence vers l’objet distant enregistré dans le registre RMI du serveur. Il ne reste plus qu’à l’utiliser, ce qui donne l’illusion qu’il n’y a aucune
distance entre le client et le serveur.

Couplé au design pattern proxy de protection, RMI permet de faire une application réseau multi-utilisateur pouvant gérer des droits d’accès.

Nous avons alors réalisé différents InvocationHandler:
- InvocationHandlerAnonymous: lorsque le client est inconnu celui-ci peut seulement appeler la méthode login.
- InvocationHandlerStudent: le client a été identifié comme étudiant, celui-ci peut appeler les méthodes permettant de choisir ses cours et afficher ses notes.
- InvocationHandlerTeacher: le client a été identifié comme professeur, celui-ci peut appeler les méthodes pour ajouter des notes, pour ces cours.
- InvocationHandlerAdmin: celui-ci peut appeler toutes les méthodes.
  
Si une méthode est appelé alors que le client n'a pas les droits (un étudiant qui modifierais les notes d'un autre étudiant ou tentative de piratage), alors le serveur
renvoi une **IllegalAccessException**.

Par défaut le client se connecte via RMI via l'interface RemoteController avec le proxy InvocationHandlerAnonymous, ensuite la méthode de login retourne au client un RemoteController avec le proxy associé à ses droits d'utilisateur.

