package eirb.pg203.parser;
import eirb.pg203.model.Calendar;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser implements IcsParser {
  Extractor e = new Extractor();
  Decoder d = new Decoder();
  public Calendar parse(Path path) {
    List<String> chunks = e.fileToChunks(path);
    List<Map<String, String>> maps = new ArrayList<>();
    for (String chunk : chunks) {
      maps.add(e.parseChunk(chunk));
    }
    return d.calendar(maps);
  }
}