package eirb.pg203.parser;

import eirb.pg203.model.Calendar;
import java.nio.file.Path;

public interface IcsParser {
  Calendar parse(Path path);
}
