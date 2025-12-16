package eirb.pg203.cli;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import eirb.pg203.model.ViewType;
import eirb.pg203.output.OutputFormat;

/**
 * Parseur de ligne de commande.
 * Analyse les arguments passés au programme et construit une configuration
 * (CliConfig).
 */
public class CliParser {
  // Formatter pour les dates au format ICS (YYYYMMDD)
  private static final DateTimeFormatter DATE_YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");

  /**
   * Vérifie qu'un argument supplémentaire existe après une option.
   *
   * @param args         Tableau des arguments
   * @param currentIndex Index actuel
   * @param optionName   Nom de l'option pour le message d'erreur
   * @throws CliException Si l'argument manque
   */
  private static void validateNextArgument(String[] args, int currentIndex, String optionName) {
    if (currentIndex + 1 >= args.length) {
      throw new CliException("L'option " + optionName
          + " nécessite un argument");
    }
  }

  /**
   * Parse une date au format YYYYMMDD.
   *
   * @param dateString La chaîne à parser
   * @param optionName Nom de l'option pour le message d'erreur
   * @return LocalDate parsée
   * @throws CliException Si le format est invalide
   */
  private static LocalDate parseDate(String dateString, String optionName) {
    try {
      return LocalDate.parse(dateString, DATE_YYYYMMDD);
    } catch (DateTimeParseException e) {
      throw new CliException("Format de date invalide pour " + optionName
          + ": attendu YYYYMMDD, reçu '" + dateString
          + "'");
    }
  }

  /**
   * Méthode principale de parsing de la ligne de commande.
   * Analyse les arguments et construit un objet CliConfig.
   *
   * @param args Arguments de la ligne de commande
   * @return Configuration CLI complète
   * @throws CliException En cas d'arguments invalides ou manquants
   */
  public static CliConfig parse(String[] args) {
    // Vérification du nombre minimal d'arguments
    if (args.length < 2) {
      throw new CliException(
          "Usage: clical <file> <events|todos> [options]");
    }

    // Premier argument: fichier ICS d'entrée
    Path inputFile = Path.of(args[0]);

    // Deuxième argument: type d'éléments à afficher (events ou todos)
    ViewType viewType;
    if (args[1].equals("events")) {
      viewType = ViewType.EVENTS;
    } else if (args[1].equals("todos")) {
      viewType = ViewType.TODOS;
    } else {
      throw new CliException("Type invalide: '" + args[1]
          + "'. Attendu: 'events' ou 'todos'");
    }

    EventFilterType eventFilter = EventFilterType.TODAY;
    TodoFilterType todoFilter = TodoFilterType.INCOMPLETE;

    LocalDate from = null;
    LocalDate to = null;
    boolean range = false;

    OutputFormat format = OutputFormat.TEXT;
    Path outputFile = null;

    // Parcours des options (à partir du 3ème argument)
    for (int i = 2; i < args.length; i++) {

      switch (args[i]) {
        // FILTRES POUR ÉVÉNEMENTS
        case "-today":
          eventFilter = EventFilterType.TODAY;
          break;

        case "-tomorrow":
          eventFilter = EventFilterType.TOMORROW;
          break;

        case "-week":
          eventFilter = EventFilterType.WEEK;
          break;

        case "-from":
          validateNextArgument(args, i, "-from");
          range = true;
          from = parseDate(args[++i], "-from");
          to = parseDate("20300101", "-to");
          eventFilter = EventFilterType.RANGE;
          break;

        case "-to":
          validateNextArgument(args, i, "-to");
          range = true;
          to = parseDate(args[++i], "-to");
          eventFilter = EventFilterType.RANGE;
          break;

        // FILTRES POUR TODOS
        case "-all":
          todoFilter = TodoFilterType.ALL;
          break;

        case "-completed":
          todoFilter = TodoFilterType.COMPLETED;
          break;

        case "-inprocess":
          todoFilter = TodoFilterType.INPROCESS;
          break;

        case "-needsaction":
          todoFilter = TodoFilterType.NEEDSACTION;
          break;

        case "-incomplete":
          todoFilter = TodoFilterType.INCOMPLETE;
          break;

        // FORMATS DE SORTIE
        case "-text":
          format = OutputFormat.TEXT;
          break;

        case "-ics":
          format = OutputFormat.ICS;
          break;

        case "-html":
          format = OutputFormat.HTML;
          break;

        case "-o":
          validateNextArgument(args, i, "-o");
          outputFile = Path.of(args[++i]);
          break;

        default:
          throw new CliException("Option inconnue: " + args[i]);
      }
    }

    // VALIDATION
    if (viewType == ViewType.TODOS && range) {
      throw new CliException(
          "Les options -from/-to sont interdites pour les TODOS");
    }

    if (viewType == ViewType.EVENTS && range) {
      if (from == null) {
        from = parseDate("20100101", "from");
      }
      if (to == null) {
        to = parseDate("20300101", "to");
      }
    }

    if (from != null && to != null && from.isAfter(to)) {
      throw new CliException("La date -from (" + from
          + ") ne peut pas être après -to (" + to
          + ")");
    }

    return new CliConfig(inputFile, viewType, eventFilter, todoFilter, from,
        to, format, outputFile);
  }
}