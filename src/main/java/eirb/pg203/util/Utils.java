package eirb.pg203.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe contenant des méthodes statiques pour des opérations courantes :
 * - Conversion de chaînes en dates/nombres
 * - Lecture de fichiers
 * - Formatage de données ICS
 */
public class Utils
{

  /**
   * Convertit une chaîne au format "yyyyMMdd" en objet LocalDate.
   * Ex: "20231225" -> LocalDate du 25 décembre 2023.
   *
   * @param date Chaîne de 8 chiffres représentant une date
   * @return LocalDate correspondante, ou null si le format est invalide
   */
  public static LocalDate
  dateGetter (String date)
  {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("yyyy/MM/dd");

    String[] elts = new String[3];
    elts[0] = date.substring (0, 4); // année
    elts[1] = date.substring (4, 6); // mois
    elts[2] = date.substring (6, 8); // jour
    String result = String.join ("/", elts);
    return LocalDate.parse (result, formatter);
  }

  /**
   * Convertit une chaîne en Integer.
   * Retourne null si la chaîne est nulle, vide, ou non numérique.
   *
   * @param s Chaîne à convertir
   * @return Integer correspondant, ou null si conversion impossible
   */
  public static Integer
  StoNum (String s)
  {
    if (s == null)
      return null;
    s = s.trim ();
    if (s.isEmpty ())
      return null;
    try
      {
        return Integer.valueOf (s);
      }
    catch (NumberFormatException e)
      {
        return null;
      }
  }

  // Formatters pour les dates ICS
  private static final DateTimeFormatter DATE
      = DateTimeFormatter.ofPattern ("yyyyMMdd");
  private static final DateTimeFormatter DATETIME
      = DateTimeFormatter.ofPattern ("yyyyMMdd'T'HHmmss");

  /**
   * Parse une chaîne ICS représentant une date (format yyyyMMdd).
   * Utilisé pour des champs comme DUE;VALUE=DATE ou DTSTART;VALUE=DATE.
   *
   * @param raw Chaîne du fichier ICS
   * @return LocalDate correspondante, ou null si invalide
   */
  public static LocalDate
  dateFormatter (String raw)
  {
    if (raw == null)
      return null;
    String v = raw.trim ();
    if (v.isEmpty ())
      return null;

    try
      {
        if (v.length () == 8 && v.chars ().allMatch (Character::isDigit))
          {
            return LocalDate.parse (v, DATE);
          }
        return null;
      }
    catch (Exception e)
      {
        return null;
      }
  }

  /**
   * Parse une chaîne ICS représentant une date-heure (format
   * yyyyMMdd'T'HHmmss). Utilisé pour des champs comme DTSTART, DTEND, CREATED,
   * etc.
   *
   * @param raw Chaîne du fichier ICS
   * @return LocalDateTime correspondante, ou null si invalide
   */
  public static LocalDateTime
  dateTimeFormatter (String raw)
  {
    if (raw == null)
      return null;
    String v = raw.trim ();
    if (v.isEmpty ())
      return null;

    try
      {
        if (v.endsWith ("Z"))
          {
            String core = v.substring (0, v.length () - 1);
            LocalDateTime ldt = LocalDateTime.parse (core, DATETIME);
            return ldt;
          }
        LocalDateTime ldt = LocalDateTime.parse (v, DATETIME);
        return ldt;
      }
    catch (Exception e)
      {
        return null;
      }
  }

  /**
   * Lit toutes les lignes d'un fichier et les retourne sous forme de liste.
   *
   * @param path Chemin vers le fichier à lire
   * @return Liste des lignes du fichier, ou liste vide en cas d'erreur
   */
  public static List<String>
  loadLines (Path path)
  {
    try
      {
        return Files.readAllLines (path);
      }
    catch (IOException | SecurityException e)
      {
        System.err.println ("Error reading file: " + e.getMessage ());
        return new ArrayList<> ();
      }
  }
}
