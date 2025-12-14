package eirb.pg203;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser implements IcsParser{

  // this will add only 'BEGIN:*' to all chunks, cuz 'END:*' doesnt matter
  // !! first 'chunk' is for info about the calender starting with 'BEGIN:VCALENDR'
  private List<String> fileToChunks(Path path) {
    List<String> l = Utils.loadLines(path);
    List<String> chunks = new ArrayList<>();
    String temp = "";
    for(String line : l){
      if(line.startsWith("BEGIN:") && !temp.isEmpty()) {
        chunks.add(temp);
        temp = "";
      }
      if(line.startsWith("END:")) continue;
      temp = (temp.isEmpty() ? temp : temp + "\n") + line;
    }
    return chunks;
  }
  
  private Map<String, String> parseChunk(String chunk) {
    Map<String, String> map = new HashMap<>();
    String[] lines = chunk.split("\n");

    String currKey = null;
    String currVal = "";

    for (String l : lines) {
        l = l.trim();
        int colonIndex = l.indexOf(':');
        if (colonIndex != -1) {
            String possibleKey = l.substring(0, colonIndex);
            if (this.isKey(possibleKey)) {
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

  private boolean isKey(String s) {
    return s != null && s.matches("[A-Z-;a-z\"]+"); // added ';' bc its part of the keys in todos
  }

  public Calender parse(Path path){
    List<String> chunks = fileToChunks(path);
    List<Map<String,String>> maps = new ArrayList<>();
    for(String chunk : chunks){
      maps.add(this.parseChunk(chunk));
    }
    return Decoder.calender(maps);
  }

}