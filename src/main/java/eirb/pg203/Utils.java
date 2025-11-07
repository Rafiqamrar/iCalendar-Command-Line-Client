package eirb.pg203;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Utils {

  // takes a path and returns a List<String> of its lines
  public static List<String> loadLines(Path path) {
      try {
          return Files.readAllLines(path);
      } catch (IOException | SecurityException e) {
          System.err.println("Error reading file: " + e.getMessage());
          return new ArrayList<>();
      }
  }

}
