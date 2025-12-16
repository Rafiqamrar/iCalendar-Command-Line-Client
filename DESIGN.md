# Document de design

Ceci est le document de template pour décrire l'architecture de votre programme. Vous pouvez le modifier à votre guise, mais assurez-vous de répondre à toutes les questions posées.
***Suivant certaines architectures, certaines des questions peuvent ne pas être pertinentes. Dans ce cas, vous pouvez les ignorer.***
Vous pouvez utiliser autant de diagrammes que vous le souhaitez pour expliquer votre architecture.
Nous vous conseillons d'utiliser le logiciel PlantUML pour générer vos diagrammes.

## Schéma général

Décrivez ici le schéma général de votre programme. Quels sont les composants principaux et comment interagissent-ils?


## Utilisation du polymorphisme

Comment utilisez-vous le polymorphisme dans votre programme?

## Utilisation de la déléguation

Comment utilisez-vous la délégation dans votre programme?

## Utilisation de l'héritage

Comment utilisez-vous l'héritage dans votre programme?



## Utilisation de la généricité

Comment utilisez-vous la généricité dans votre programme?

Nous utilisons la généricité principalement dans l'interface `OutputWriter` avec sa méthode `write(List<? extends CalElement> elements, OutputStream out)`. Le wildcard `? extends CalElement` permet d'accepter une liste contenant n'importe quel sous-type de `CalElement` (comme `Event` ou `Todo`), offrant ainsi une grande flexibilité pour traiter différents types d'éléments de calendrier avec une seule interface. Cette approche générique permet à nos trois implémentations (`IcsWriter`, `HtmlWriter`, `TextWriter`) de gérer uniformément tous les types d'éléments tout en préservant la sécurité du typage.



## Utilisation des exceptions

Comment utilisez-vous les exceptions dans votre programme?

Dans notre programme, nous utilisons principalement des exceptions pour gérer les erreurs de saisie utilisateur dans l'interface en ligne de commande. La `CliException` personnalisée est lancée lorsque l'utilisateur fournit des arguments invalides, comme des options manquantes, des formats de date incorrects ou des combinaisons d'options incompatibles. Ces exceptions sont attrapées au niveau le plus haut du programme pour afficher des messages d'erreur clairs et guider l'utilisateur vers une utilisation correcte de l'application.