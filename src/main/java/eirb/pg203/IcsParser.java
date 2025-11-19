package eirb.pg203;
import java.nio.file.Path;


public interface IcsParser {
  Calender parse(Path path);
}
