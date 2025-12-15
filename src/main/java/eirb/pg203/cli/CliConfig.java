package eirb.pg203.cli;

import eirb.pg203.model.ViewType;
import eirb.pg203.output.OutputFormat;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.time.LocalDate;

public class CliConfig
{

  private Path inputFile;
  private ViewType viewType;

  private EventFilterType eventFilter;
  private TodoFilterType todoFilter;

  private LocalDate from;
  private LocalDate to;

  private OutputFormat outputFormat;
  private Path outputFile;

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
