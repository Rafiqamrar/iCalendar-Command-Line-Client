package eirb.pg203.parser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eirb.pg203.util.Utils;

class Extractor {
  // this will add only 'BEGIN:*' to all chunks, cuz 'END:*' doesnt matter
  // !! first 'chunk' is for info about the calendar starting with
  // 'BEGIN:VCALENDR'
  List<String>
  fileToChunks (Path path)
  {
    List<String> l = Utils.loadLines (path);
    List<String> chunks = new ArrayList<> ();
    String temp = "";
    for (String line : l)
      {
        if (line.startsWith ("BEGIN:") && !temp.isEmpty ())
          {
            chunks.add (temp); // for header
            temp = "";
          }
        if (line.startsWith ("END:"))
          {
            if (!temp.isEmpty ())
              { // add close element
                chunks.add (temp);
                temp = "";
              }

            continue;
          }
        temp = (temp.isEmpty () ? temp : temp + "\n") + line;
      }
    return chunks;
  }

  Map<String, String>
  parseChunk (String chunk)
  {
    Map<String, String> map = new HashMap<> ();
    String[] lines = chunk.split ("\n");

    String currKey = null;
    StringBuilder currVal = new StringBuilder ();

    for (String l : lines)
      {
        l = l.trim ();
        int colonIndex = l.indexOf (':');

        if (colonIndex != -1)
          {
            String possibleKey = l.substring (0, colonIndex);

            if (this.isKey (possibleKey))
              {
                // Si il y a déjà une clé sauvegardée on la met dans la map
                if (currKey != null)
                  {
                    map.put (currKey, currVal.toString ());
                  }

                currKey = possibleKey;
                currVal = new StringBuilder (l.substring (colonIndex + 1));
                continue;
              }
          }

        // Continuation de la valeur précédente
        if (currVal.length () > 0)
          {
            currVal.append ("\n");
          }
        currVal.append (l);
      }

    // Ajouter la dernière paire clé-valeur
    if (currKey != null)
      {
        map.put (currKey, currVal.toString ());
      }

    return map;
  }

  private boolean
  isKey (String s)
  {
    return s != null && s.matches ("[A-Za-z;\"=-]+");
  }
}
