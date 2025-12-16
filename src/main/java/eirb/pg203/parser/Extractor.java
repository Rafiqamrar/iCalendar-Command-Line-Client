// File: Extractor.java
package eirb.pg203.parser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eirb.pg203.util.Utils;

/**
 * Classe pour extraire et parser les blocs d'un fichier ICS.
 * Divise le fichier en "chunks" (blocs) correspondant aux sections BEGIN/END,
 * puis parse chaque chunk en une Map clé/valeur.
 */
class Extractor {
  /**
   * Lit un fichier ICS et le divise en blocs délimités par BEGIN: et END:.
   * Le premier bloc est le header du calendrier (BEGIN:VCALENDAR).
   *
   * @param path Chemin vers le fichier ICS
   * @return Liste des blocs sous forme de chaînes de caractères
   */
  List<String> fileToChunks (Path path)
  {
    List<String> l = Utils.loadLines (path);
    List<String> chunks = new ArrayList<> ();
    String temp = "";
    for (String line : l)
      {
        if (line.startsWith ("BEGIN:") && !temp.isEmpty ())
          {
            chunks.add (temp);
            temp = "";
          }
        if (line.startsWith ("END:"))
          {
            if (!temp.isEmpty ())
              {
                chunks.add (temp);
                temp = "";
              }

            continue;
          }
        temp = (temp.isEmpty () ? temp : temp + "\n") + line;
      }
    return chunks;
  }

  /**
   * Parse un bloc ICS en une Map où chaque clé est un nom de propriété
   * et chaque valeur est la chaîne correspondante.
   * Gère les lignes continuées en les concaténant.
   *
   * @param chunk Bloc ICS sous forme de chaîne
   * @return Map des propriétés extraites
   */
  Map<String, String> parseChunk (String chunk)
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

  /**
   * Détermine si une chaîne est une clé ICS valide (contient lettres, chiffres, ;, =, -, ")
   */
  private boolean isKey (String s)
  {
    return s != null && s.matches ("[A-Za-z;\"=-]+");
  }
}