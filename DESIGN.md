# Document de design

Ceci est le document de template pour décrire l'architecture de votre programme. Vous pouvez le modifier à votre guise, mais assurez-vous de répondre à toutes les questions posées.
***Suivant certaines architectures, certaines des questions peuvent ne pas être pertinentes. Dans ce cas, vous pouvez les ignorer.***
Vous pouvez utiliser autant de diagrammes que vous le souhaitez pour expliquer votre architecture.
Nous vous conseillons d'utiliser le logiciel PlantUML pour générer vos diagrammes.

## Schéma général

Décrivez ici le schéma général de votre programme. Quels sont les composants principaux et comment interagissent-ils?

## Utilisation du polymorphisme

Comment utilisez-vous le polymorphisme dans votre programme?

OutputWriter (interface) avec plusieurs implémentations (HtmlWriter, IcsWriter, TextWriter). Le Main manipule un OutputWriter abstrait et n’a pas besoin de connaître le type concret. C’est le factory qui décide.
CalElement est une classe mère abstraite dont héritent Event et Todo. Même si le Main fait encore des instanceof, l’intention est là : une même collection peut contenir des formes différentes (événements ou todos).

## Utilisation de la déléguation

Comment utilisez-vous la délégation dans votre programme?

Main délègue la lecture des arguments à CliParser et la gestion d’erreurs spécifiques à CliException.
Main délègue la construction du modèle à Parser.
Main délègue le filtrage à EventFilters/TodoFilters, qui encapsulent les règles basées sur CliConfig.
Main délègue la décision du writer au OutputWriterFactory.

Le Writer délègue le format de sortie à ses implémentations.

le Parser délègue l'extraction des 'chunks' à la classe Extractor et la décodation de ces chunks à la classe Decoder.

la sortie dans TextWriter délègue l'affichage du sortie terminal des calElements aux classes Event Todo avec la fonction overwrited : toString 

## Utilisation de l'héritage

Comment utilisez-vous l'héritage dans votre programme?

## Utilisation de la généricité

Comment utilisez-vous la généricité dans votre programme?

## Utilisation des exceptions

Comment utilisez-vous les exceptions dans votre programme?