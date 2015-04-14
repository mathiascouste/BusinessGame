#===============================================#
#  Partie serveur de Cookies On Demand			#
#  Groupe  : BC									#
#  Version : 1.0								#
#  Date : 12.04.2015							#
#===============================================#

Pour lancer le serveur, utilisez la commande suivante : 
	mvn clean package tomee:run -DskipTests

#===============================================#
#  Implémentations réalisées sur le serveur :   #
#===============================================#

Nous avons 4 webservices :
- AccountService : pour la création de compte et la connexion
- ShopService 	 : pour la création et la gestion des boutiques
- OrderService 	 : pour la création et la gestion de commandes 
- Catalog 		 : pour lister les cookies et ingrédients et en ajouter de nouveaux

Les stats sont visualisables par une page JSF et ne sont donc pas implémentées en tant que webservice.

Nous avons décidé de gérer l'ensemble de nos services de manière stateless.
Ainsi, nous passons en paramètre des méthodes, lorsque nécessaire, les identifiants des comptes/commandes pour s'assurer d'agir sur les bons éléments.

# COMPTES
* Creation / Suppression
On peut créer un compte en précisant le nom et s'il s'agit d'un compte admin.
On ne garde pas en mémoire le mot de passe puisque celui-ci sera envoyé directement au système de connexion distant .NET.
Les comptes admin sont les comptes de TCF : ils peuvent créer et supprimer des boutiques, ce que ne peuvent pas faire les autres comptes.
* Connexion 
C'est une connexion "bouchon" en attendant la liaison avec le système d'authentification .NET distant
On a un identifiant et un mot de passe. On recherche simplement que l'utilisateur a bien créé un compte. Aucune vérification n'est faite sur le mot de passe.
* Gestion
Certaines méthodes locale au serveur ont été implémentées pour changer le nom, changer la valeur de la cagnotte, adhérer au programme Loyal.
Mais il n'y a pas encore de webservice déployé pour la gestion des comptes.
L'achat de cartes fidélités, la cagnotte, l'adhésion au programme loyalty ne sont donc pas mis en place.

# BOUTIQUES
* Création / Suppression
On peut créer une boutique en précisant l'id de son propriétaire et l'id de la personne créant cette boutique.
On vérifie ainsi que la personne créant la boutique est bien un admin. Il en est de même pour la suppression.
* Gestion
Seul le propriétaire d'une boutique peut faire des modifications sur sa boutique (changement de nom, d'adresse, de taxe, today's special...).
La mise en avant du Today's Special pour les clients n'est pas mis en place.

# COMMANDES
* Création / Gestion
Les commandes peuvent être créés par n'importe qui, en passant l'identifiant du compte de la personne.
Si la personne n'a pas de compte et passe une commande, on souhaite gérer ça en créant un compte temporaire qui serait détruit après coup. Mais ce n'est pas encore implémenté.
Une commande peut avoir 4 états :
0 - en cours : elle a été créée et est en cours (on peut ajouter des cookies, choisir une boutique et une date de récupération)
1 - validée  : elle a été validé (on ne peut plus ajouter de cookies, ni modifier la boutique et la date de récupération).
2 - payée 	 : elle a été payée par le client, avec la cagnotte ou par carte bancaire.
3 - retirée  : elle a été retirée du magasin.
Le paiement est un "bouchon" en attendant la liaison avec le service de paiement .NET distant. Lorsque la méthode est appelée, la paiement est automatique et la commande passe en payée.
On gère bien la réduction de 10% sur la commande tous les 30 cookies lors de la validation de la commande. En revanche on ne prend pas en compte qu'il ne faut pas décompter les cookies sur une commande qui bénéficie de la réduction.
Comme on est en stateless, toutes les commandes sont enregistrées en base de données. A l'avenir, on envisagerait que les commandes non validées soient supprimées si l'utilisateur annule sa commande dans l'interface, ou après un certain délai passé sans être validée.

# CATALOGUE
On peut ajouter de nouveaux cookies et de nouveaux ingrédients. Mais la suppression n'est pas implémentée.
On peut lister les différents cookies et ingrédients.
(L'héritage des ingrédients a sauté, chaque ingrédient est une classe à part entière).

