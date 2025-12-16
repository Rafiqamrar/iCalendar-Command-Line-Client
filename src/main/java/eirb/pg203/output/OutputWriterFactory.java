package eirb.pg203.output;

import eirb.pg203.cli.CliConfig;

/**
 * Factory pour créer les writers de sortie selon le format demandé.
 */
public class OutputWriterFactory
{
  /**
   * Crée un OutputWriter d'après le format spécifié dans la configuration CLI.
   * 
   * @param config Configuration CLI contenant le format de sortie
   * @return Instance de OutputWriter (TextWriter, IcsWriter ou HtmlWriter)
   */
  public static OutputWriter
  create (CliConfig config)
  {
    switch (config.getOutputFormat ())
      {
      case TEXT:
        return new TextWriter ();
      case ICS:
        return new IcsWriter ();
      case HTML:
        return new HtmlWriter ();
      default:
        return new TextWriter ();
      }
  }
}
