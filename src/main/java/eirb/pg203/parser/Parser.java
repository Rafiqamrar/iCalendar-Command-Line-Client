package eirb.pg203.parser;
import eirb.pg203.model.Calendar;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser implements IcsParser {
  public Calendar parse(Path path) {
    Extractor e = new Extractor();
    Decoder d = new Decoder();

    List<String> chunks = e.fileToChunks(path);

    List<Map<String, String>> maps = chunks
        .stream()
        .map(e::parseChunk)
        .collect(Collectors.toList());
    return d.calendar(maps);
  }
}