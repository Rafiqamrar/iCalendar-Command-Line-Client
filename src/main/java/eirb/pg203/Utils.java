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

  // this keeps the BEGIN:VEVENT and END:VEVENT , in case we need it later.  
  public static List<String> fileToChunks(Path path) {
    List<String> l = Utils.loadLines(path);
    List<String> chunks = new ArrayList<>();
    String temp = "";
    Boolean start = false;
    for(String line : l){
      if(line.startsWith("BEGIN:VEVENT")){
        start = true;
      }
      
      // 'if' here to add the "BEGIN:VEVENT" 
      if(start){
        temp = (temp == "" ? "" : temp + "\n") + line;
      }

      // 'if' here to add the "END:VEVENT" 
      if(line.startsWith("END:VEVENT")){
        chunks.add(temp);
        temp = ""; // restart
      }
    }
    return chunks;
  }
}
