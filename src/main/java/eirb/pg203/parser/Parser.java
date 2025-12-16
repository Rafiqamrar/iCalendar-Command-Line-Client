package eirb.pg203.parser;
import eirb.pg203.model.Calendar;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implémentation du parseur ICS.
 */
public class Parser implements IcsParser
{
  Extractor e = new Extractor ();
  Decoder d = new Decoder ();

  /**
   * Parse un fichier ICS.
   * Étapes :
   * 1. Divise le fichier en chunks (Extractor.fileToChunks)
   * 2. Parse chaque chunk en Map (Extractor.parseChunk)
   * 3. Convertit les Maps en objets métier et construit le Calendar
   * (Decoder.calendar)
   */
  public Calendar
  parse (Path path)
  {
    Extractor e = new Extractor ();
    Decoder d = new Decoder ();
    List<Map<String, String> > maps = e.fileToChunks (path)
                                          .stream ()
                                          .map (e::parseChunk)
                                          .collect (Collectors.toList ());
    return d.calendar (maps);
  }
}