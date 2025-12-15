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

public class Utils
{

  public static LocalDate
  dateGetter (String date)
  {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("yyyy/MM/dd");

    String[] elts = new String[3];
    elts[0] = date.substring (0, 4); // year
    elts[1] = date.substring (4, 6); // month
    elts[2] = date.substring (6, 8); // day
    String result = String.join ("/", elts);
    return LocalDate.parse (result, formatter);
  }

  public static Number
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

  private static final DateTimeFormatter DATE
      = DateTimeFormatter.ofPattern ("yyyyMMdd");
  private static final DateTimeFormatter DATETIME
      = DateTimeFormatter.ofPattern ("yyyyMMdd'T'HHmmss");

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

  // takes a path and returns a List<String> of its lines
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
