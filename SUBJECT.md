---
documentclass: book
papersize: a4
fontsize: 10pt
header-includes: |
    \usepackage{hyperref}
    \hypersetup{
        colorlinks = true,
        linkbordercolor = {pink},
    }
    \usepackage{newunicodechar}
---

# Projet Programmation Orientée Objet (PG203)

L’objectif de ce projet est de réaliser un client de calendrier en ligne de commande.
Il permet de récupérer lire un flux de données contenant un calendrier [iCalendar](https://icalendar.org/) (ICS) puis d'afficher le planning sur une période donnée (journée, semaine) dans un format de sortie (texte, ICS, html).
Attention, bien qu'il existe des bibliothèques qui permettent de faire du ICS en Java (par exemple ical4J) il est demandé dans ce projet d'écrire toute cette partie du code à la main.
Il existe aussi des bibliothèques qui permettent de réaliser des clients en ligne de commande (par exemple picocli) mais il est demandé dans ce projet d'écrire aussi cette partie à la main.

Bien entendu, l'objectif principal de ce projet est de mettre en oeuvre les principes fondamentaux de la programmation orientée objet: encapsulation, délégation, héritage, polymorphisme à bon escient afin d'avoir une design le plus clair et facile à maintenir.
Le développement d'un programme qui implémente les fonctionnalités demandées est secondaire.
Compte tenu que l'enjeu dans ce projet est de vous faire réfléchir aux différentes façons d'utiliser les concepts vu en cours pour implémenter les fonctionnalités demandées, *l'utilisation d'outils de générations de code est interdite pour la réalisation des livrables du projet.*

## Organisation

Les groupes de projet reprennent les équipes de TDs. Pour faciliter le
travail, le projet est découpé en trois itérations successives :

- la première et la seconde itération feront l’objet d’une démo et
  d’une rapide revue de code durant le début de chaque séance de suivi
  de projet;

- la troisième itération sera complétée et rendue avec un ensemble de
  livrables à la fin du projet.

### Starter-kit

Pour faciliter le développement, un starter-kit qui contient un squelette de projet est fourni.
Il utilise l'outil de build `Gradle` pour gérer les dépendances aux bibliothèques externes, pour compiler le projet, pour lancer les tests et pour lancer le client.
Le starter kit se contente d'afficher les 5 premières lignes d'un fichier ICS d'exemple `i2.ics` qui contient l'emploi du temps des I2.

### Première itération

Dans la première itération il faut être en mesure de parser des objets évènements de type `VEVENT` contenus dans un fichier ICS.
Un fichier d'exemple contenant ce type d'évènements se trouve dans le fichier `i2.ics`.
Il faut dans un premier temps configurer un client en ligne de commande qui prend en premier argument le flux vers le fichier ICS et qui affiche les évènements contenus dans le flux sur la console.
Vous êtes libres de déterminer la façon d'afficher les évènements mais les informations principales doivent ressortir (date, description, lieu).
La ligne de commande s'utilise donc de cette manière: `clical path/to/i2.ics`.

### Seconde itération

Dans cette seconde itération il faut gérer les objets TODOs de type `VTODO`.
Il faut donc maintenant configurer le client en ligne de commande pour prendre un deuxième argument obligatoire qui est le type d'objets que l'on veut visualiser: `events` pour les évènements ou `todos` pour les TODOs.
Un fichier `todos.ics` contient des TODOs d'exemple.
La ligne de commande s'utilisera donc de cette manière: `clical path/to/i2.ics events` ou `clical path/to/todos.ics todos`.
Il faut bien entendu aussi être capable maintenant d'afficher aussi des TODOs.
Encore une fois, vous êtes libres de déterminer la façon d'afficher les TODOs mais les informations principales doivent ressortir (description, lieu, statut, progression, date butoir).

### Troisième itération

Dans cette troisième itération il faut être en mesure de configurer la sortie.
Chaque type d'évènement va prendre des options qui vont permettre de configurer ce qui va être affiché.

Pour les évènements on peut admettre:

- une option `-today` (option par défaut, non cumulative avec les autres)
- une option `-tomorrow` (non cumulative avec les autres)
- une option `-week` (non cumulative avec les autres)
- une option `-from DATE` (cumulative avec le flag `-to`)
- une option `-to DATE` (cumulative avec le flag `-from`)

Pour les TODOs:

- une option `-incomplete` (option par défaut, non cumulative avec les autres, englobe tous les évènements non complétés)
- une option `-all` (non cumulative avec les autres)
- une option `-completed` (non cumulative avec les autres)
- une option `-inprocess` (non cumulative avec les autres)
- une option `-needsaction` (non cumulative avec les autres)

Il faut aussi une option qui permet de configurer le format de sortie.
Cette option peut s'appliquer autant aux évènements que aux TODOs.
Il doit être possible de choisir entre une sortie sur la console ou un fichier:

- `-text` (option par défaut, sauvegarde au format texte)
- `-ics` (sauvegarde au format ICS)
- `-html` (sauvegarde au format HTML)
- `-o FILE` (le nom du fichier à sauvegarder, sinon `stdout`)

Voici des exemples de commandes:

- `clical path/to/i2.ics events -from 20251111 -ics -o output.ics`
- `clical path/to/todos.ics todos -incomplete`

Bien entendu une ligne de commande invalide telle que `clical path/to/i2.ics todos -from 20251111` doit occasionner un message d'erreur.

## Livrables

Pour la dernière itération, les livrables sont les suivants :

- Le code source intégral du projet, utilisant une indentation claire,
  des noms de variables explicites et des commentaires pertinents. La
  lisibilité du code sera un des critères de notation.

- Outre la lisibilité, le code fourni devra pouvoir être compilé et
  exécuté sans erreurs en utilisant les commandes de bases décrites
  dans le starter-kit :

  ```
  gradlew build
  gradlew run
  gradlew test
  ```

- Un document de conception qui explique les choix de conception utilisé dans votre programme. Vous trouverez un template de ce document dans le fichier `DESIGN.md`.

- Un jeu de tests unitaires qui couvre toutes les parties importantes du code. L'outil `Jacoco` installé dans le starter-kit permet de calculer et visualiser la couverture de code des tests.
