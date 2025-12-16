# Document de design

Ceci est le document de template pour décrire l'architecture de votre programme. Vous pouvez le modifier à votre guise, mais assurez-vous de répondre à toutes les questions posées.
***Suivant certaines architectures, certaines des questions peuvent ne pas être pertinentes. Dans ce cas, vous pouvez les ignorer.***
Vous pouvez utiliser autant de diagrammes que vous le souhaitez pour expliquer votre architecture.
Nous vous conseillons d'utiliser le logiciel PlantUML pour générer vos diagrammes.

## Schéma général

Décrivez ici le schéma général de votre programme. Quels sont les composants principaux et comment interagissent-ils?

Notre programme suit une architecture modulaire composée de plusieurs couches :

CLI (Command Line Interface) : analyse les arguments et construit la configuration (CliConfig).
Parser : lit le fichier ICS et construit un modèle Calendar contenant des éléments (Event, Todo).
Filter : appliquent des règles de filtrage sur les événements ou les tâches selon la configuration.
Output : choisit dynamiquement un writer (HtmlWriter, IcsWriter, TextWriter) via une factory pour générer la sortie dans le format demandé.

Flux général :
Main -> CliParser -> Parser -> Calendar -> Filters -> OutputWriterFactory -> OutputWriter.

## Utilisation du polymorphisme

Comment utilisez-vous le polymorphisme dans votre programme?

Nous utilisons le polymorphisme principalement via des interfaces.

Par exemple, OutputWriter (interface) avec plusieurs implémentations (HtmlWriter, IcsWriter, TextWriter). Le Main manipule un OutputWriter abstrait et n’a pas besoin de connaître le type concret. C’est le factory qui décide.

l’interface ICSParser définit le contrat de parsing, et nous avons actuellement une implémentation Parser, mais d’autres parseurs peuvent être ajoutés sans modifier le code appelant.

## Utilisation de la déléguation

Comment utilisez-vous la délégation dans votre programme?

Notre programme utilise largement la délégation pour séparer les responsabilités : la classe `Main` délègue respectivement l'analyse des arguments à `CliParser`, la construction du modèle à `Parser`, le filtrage aux classes `EventFilters`/`TodoFilters`, et le choix du format de sortie à `OutputWriterFactory`. De même, le `Parser` délègue l'extraction des données à `Extractor` et leur décodage à `Decoder`, tandis que les writers délèguent le formatage concret à leurs implémentations et que `TextWriter` délègue l'affichage des éléments aux classes `Event` et `Todo` via leur méthode `toString()`. Cette approche modulaire favorise la maintenabilité et la testabilité du code.

## Utilisation de l'héritage

Comment utilisez-vous l'héritage dans votre programme?

Nous utilisons l’héritage pour structurer le modèle comme suit :
La classe abstraite CalElement représente un élément générique du calendrier.
Les classes concrètes Event et Todo héritent de CalElement et implémentent la méthode viewType() pour indiquer leur type.
Cette hiérarchie permet de traiter uniformément les éléments tout en conservant leurs spécificités.


## Utilisation de la généricité

Comment utilisez-vous la généricité dans votre programme?

Nous utilisons la généricité pour rendre certaines méthodes flexibles tout en gardant la sécurité de type. Par exemple, la méthode :

void write (List<? extends CalElement> elements, OutputStream out);

accepte une liste d’Event, de Todo ou de CalElement grâce au wildcard ? extends CalElement. Cela évite les casts et permet de traiter différents sous-types avec une seule méthode. 
Cependant, nous n’avons pas trouvé beaucoup d’occasions d’utiliser la généricité dans ce projet, car la plupart des besoins étaient couverts par des types spécifiques (Event, Todo) et des interfaces.

## Utilisation des exceptions

Comment utilisez-vous les exceptions dans votre programme?

Dans notre programme, nous utilisons principalement des exceptions pour gérer les erreurs de saisie utilisateur dans l'interface en ligne de commande. La `CliException` personnalisée est lancée lorsque l'utilisateur fournit des arguments invalides, comme des options manquantes, des formats de date incorrects ou des combinaisons d'options incompatibles. Ces exceptions sont attrapées au niveau le plus haut du programme pour afficher des messages d'erreur clairs et guider l'utilisateur vers une utilisation correcte de l'application.