package eirb.pg203;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
  
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
  
  public static List<Map<String,String>> parse(List<String> chunks) {
    List<Map<String,String>> maps = new ArrayList<>();
    for(String chunk : chunks){
      maps.add(Parser.parseChunk(chunk));
    }
    return maps;
  }

  public static Map<String, String> parseChunk(String chunk) {
    Map<String, String> map = new HashMap<>();
    String[] lines = chunk.split("\n");

    String currKey = null;
    String currVal = "";

    for (String l : lines) {
        l = l.trim();
        int colonIndex = l.indexOf(':');
        if (colonIndex != -1) {
            String possibleKey = l.substring(0, colonIndex);
            if (Parser.isKey(possibleKey)) {
                // if theres already a key saved : put it in map first
                if (currKey != null) {
                    map.put(currKey, currVal.toString());
                }
                currKey = possibleKey;
                currVal = l.substring(colonIndex + 1);
                continue;
            }
        }
        // Continuation of previous value
        currVal = currVal + "\n" + l;
    }

    // Add the last key-value
    if (currKey != null) {
        map.put(currKey, currVal.toString());
    }
    return map;
  }

  public static boolean isKey(String s) {
    return s != null && s.matches("[A-Z-]+");
  }
}