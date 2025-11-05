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
---

# Projet de PG203

Ce starter kit vous permet de démarrer un projet d'application en
ligne de commande Java. La gestion du build est effectuée par l'outil
Gradle. Deux exécutables sont fournis: `gradlew` pour Unix ou MacOS et
`gradlew.bat` pour Windows.

Le starter kit vient avec:

- le framework [`JUnit 5`](https://junit.org/junit5/docs/current/user-guide/) pour gérer les tests;

- l'outil [`Jacoco`](https://www.jacoco.org/) pour la couverture du
  code par les tests.

Le starter-kit contient un fichier
`src/main/java/eirb/pg203/Main.java` qui contient un programme de
démonstration. Ce programme récupère lit un fichier iCalendar contenant
l'emploi du temps de I2 et affiche les 20 premières lignes sur
la console.

Le fichier `src/main/java/eirb/pg203/SampleTest.java` contient un
petit exemple de test unitaire de la fonction qui charge les données.

Voici comment effectuer les différentes commandes importantes.

## Compilation

```bash
./gradlew build
```

## Lancement des tests

```bash
./gradlew test
```

## Génération du rapport de couverture

```bash
./gradlew jacocoTestReport
```

Le rapport se trouve dans `build/reports/jacoco/test/html/index.html`.

## Lancement du programme

```
./gradlew run --args="arg1 arg2"
```
