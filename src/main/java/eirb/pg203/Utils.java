package eirb.pg203;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    
    public static LocalDate dateGetter(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String[] elts = new String[3];
        elts[0] = date.substring(0, 4); // year
        elts[1] = date.substring(4, 6); // month
        elts[2] = date.substring(6, 8); // day
        String result = String.join("/", elts);
        return LocalDate.parse(result, formatter);
    }

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


