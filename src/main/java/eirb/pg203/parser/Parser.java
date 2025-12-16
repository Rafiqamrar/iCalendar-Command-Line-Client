package eirb.pg203.parser;
import eirb.pg203.model.Calendar;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implémentation du parseur ICS.
 */
public class Parser implements IcsParser {
  Extractor e = new Extractor();
  Decoder d = new Decoder();

  /**
   * Parse un fichier ICS.
   * Étapes :
   * 1. Divise le fichier en chunks (Extractor.fileToChunks)
   * 2. Parse chaque chunk en Map (Extractor.parseChunk)
   * 3. Convertit les Maps en objets métier et construit le Calendar (Decoder.calendar)
   */
  public Calendar parse(Path path) {
    List<String> chunks = e.fileToChunks(path);
    List<Map<String, String>> maps = new ArrayList<>();
    for (String chunk : chunks) {
      maps.add(e.parseChunk(chunk));
    }
    return d.calendar(maps);
  }
}