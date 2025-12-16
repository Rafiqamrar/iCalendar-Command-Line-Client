package eirb.pg203.cli;

import eirb.pg203.model.ViewType;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.time.LocalDate;

/**
 * Conteneur de configuration CLI après parsing.
 * Stocke tous les paramètres extraits de la ligne de commande.
 */
public class CliConfig
{

  private Path inputFile;            // Chemin du fichier ICS
  private ViewType viewType;         // Type d'éléments (EVENTS/TODOS)

  private EventFilterType eventFilter; // Filtre pour événements
  private TodoFilterType todoFilter;   // Filtre pour TODOs

  private LocalDate from;            // Date de début (pour -from)
  private LocalDate to;              // Date de fin (pour -to)

  private OutputFormat outputFormat; // Format de sortie (TEXT/ICS/HTML)
  private Path outputFile;           // Fichier de sortie (null = stdout)

  /**
   * Constructeur principal.
   */
  public CliConfig (Path inputFile, ViewType viewType,
                    EventFilterType eventFilter, TodoFilterType todoFilter,
                    LocalDate from, LocalDate to, OutputFormat outputFormat,
                    Path outputFile)
  {

    this.inputFile = inputFile;
    this.viewType = viewType;
    this.eventFilter = eventFilter;
    this.todoFilter = todoFilter;
    this.from = from;
    this.to = to;
    this.outputFormat = outputFormat;
    this.outputFile = outputFile;
  }

  // GETTERS
  public Path
  getInputFile ()
  {
    return inputFile;
  }

  public ViewType
  getViewType ()
  {
    return viewType;
  }

  public EventFilterType
  getEventFilter ()
  {
    return eventFilter;
  }

  public TodoFilterType
  getTodoFilter ()
  {
    return todoFilter;
  }

  public LocalDate
  getFrom ()
  {
    return from;
  }

  public LocalDate
  getTo ()
  {
    return to;
  }

  public OutputFormat
  getOutputFormat ()
  {
    return outputFormat;
  }

  /**
   * Retourne un OutputStream pour écrire la sortie.
   * Si outputFile est null, retourne System.out.
   * Sinon, crée un FileOutputStream vers le fichier spécifié.
   *
   * @return OutputStream approprié pour l'écriture
   * @throws RuntimeException Si impossible de créer le flux fichier
   */
  public OutputStream
  getOutputStream ()
  {
    try
      {
        if (outputFile == null)
          {
            return System.out;
          }
        return new FileOutputStream (outputFile.toFile ());
      }
    catch (Exception e)
      {
        throw new RuntimeException (
            "Impossible d'écrire le fichier de sortie");
      }
  }
}
