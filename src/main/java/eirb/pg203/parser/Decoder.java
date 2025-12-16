package eirb.pg203.parser;

import eirb.pg203.model.CalElement;
import eirb.pg203.model.Calendar;
import eirb.pg203.model.Event;
import eirb.pg203.model.Todo;
import eirb.pg203.util.Utils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Convertit les Maps de propriétés ICS en objets métier (Event, Todo,
 * Calendar).
 */
class Decoder
{
  // "ORGANIZER;CN=X" : "Y" -> X
  // Retourne null si aucun CN n'est présent.
  private String
  getOname (Map<String, String> map)
  {
    if (map == null)
      return null;
    for (String key : map.keySet ())
      {
        if (key == null)
          continue;
        if (key.startsWith ("ORGANIZER"))
          {
            int semi = key.indexOf (";CN=");
            if (semi >= 0)
              {
                // valeur après ";CN=" : "[name]"
                String cn = key.substring (
                    semi + 5, key.length () - 1); // pour éliminer ""
                return cn.isEmpty () ? null : cn;
              }
            return null;
          }
      }
    return null;
  }

  /**
   * Extrait l'email de l'organisateur depuis la valeur de la clé ORGANIZER.
   */
  private String
  getOmail (Map<String, String> map)
  {
    if (map == null)
      return null;
    String value
        = map.get ("ORGANIZER"); // au cas où il n'y a pas de paramètre CN
    if (value == null)
      {
        // Trouve la première clé qui commence par ORGANIZER
        for (java.util.Map.Entry<String, String> e : map.entrySet ())
          {
            if (e.getKey () != null && e.getKey ().startsWith ("ORGANIZER"))
              {
                value = e.getValue ();
                break;
              }
          }
      }
    if (value == null)
      return null;
    value = value.trim ();
    if (value.isEmpty ())
      return null;
    return value;
  }

  /**
   * Crée un objet Todo à partir d'une Map de propriétés.
   */
  private Todo
  makeTodo (Map<String, String> map)
  {
    String UID = map.get ("UID");
    String SUMMARY = map.get ("SUMMARY");
    String LOCATION = map.get ("LOCATION");
    String STATUS = map.get ("STATUS");
    Integer PERCENT_COMPLETE = Utils.StoNum (map.get ("PERCENT-COMPLETE"));
    LocalDateTime COMPLETED = Utils.dateTimeFormatter (map.get ("COMPLETED"));
    LocalDate DUE = Utils.dateFormatter (map.get ("DUE;VALUE=DATE"));
    LocalDate DTSTART = Utils.dateFormatter (map.get ("DTSTART;VALUE=DATE"));
    String CLASS = map.get ("CLASS");
    Integer PRIORITY = Utils.StoNum (map.get ("PRIORITY"));
    LocalDateTime LAST_MODIFIED
        = Utils.dateTimeFormatter (map.get ("LAST-MODIFIED"));
    LocalDateTime DTSTAMP = Utils.dateTimeFormatter (map.get ("DTSTAMP"));
    Integer SEQUENCE = Utils.StoNum (map.get ("SEQUENCE"));
    String ORGANIZER_name = getOname (map);
    String ORGANIZER_mail = getOmail (map);
    return new Todo (UID, SUMMARY, LOCATION, STATUS, PERCENT_COMPLETE,
                     COMPLETED, DUE, DTSTART, CLASS, PRIORITY, LAST_MODIFIED,
                     DTSTAMP, SEQUENCE, ORGANIZER_name, ORGANIZER_mail);
  }

  /**
   * Crée un objet Event à partir d'une Map de propriétés.
   */
  private Event
  makeEvent (Map<String, String> map)
  {
    String UID = map.get ("UID");
    String SUMMARY = map.get ("SUMMARY");
    String LOCATION = map.get ("LOCATION");
    LocalDateTime LAST_MODIFIED
        = Utils.dateTimeFormatter (map.get ("LAST-MODIFIED"));
    LocalDateTime DTSTAMP = Utils.dateTimeFormatter (map.get ("DTSTAMP"));
    LocalDateTime DTSTART = Utils.dateTimeFormatter (map.get ("DTSTART"));
    LocalDateTime DTEND = Utils.dateTimeFormatter (map.get ("DTEND"));
    LocalDateTime CREATED = Utils.dateTimeFormatter (map.get ("CREATED"));
    String DESCRIPTION = map.get ("DESCRIPTION");
    Integer SEQUENCE = Utils.StoNum (map.get ("SEQUENCE"));
    return new Event (UID, SUMMARY, LOCATION, LAST_MODIFIED, DTSTAMP, DTSTART,
                      DTEND, CREATED, DESCRIPTION, SEQUENCE);
  }

  /**
   * Décide quel type d'élément créer (Event ou Todo) en fonction de la valeur
   * de BEGIN. Lance une IllegalArgumentException si le type n'est pas reconnu.
   */
  CalElement
  calElement (Map<String, String> map)
  {
    String type = map.get ("BEGIN");

    switch (type)
      {
      case "VEVENT":
        return makeEvent (map);
      case "VTODO":
        return makeTodo (map);
      default:
        throw new IllegalArgumentException ("Unknown type: " + type);
      }
  }

  /**
   * Construit un objet Calendar à partir d'une liste de Maps.
   * Le premier Map est considéré comme le header du calendrier.
   * Les Maps suivants sont convertis en Event ou Todo.
   */
  Calendar
  calendar (List<Map<String, String> > maps)
  {

    if (maps.isEmpty ())
      {
        return new Calendar (new HashMap<> (), new ArrayList<> ());
      }
    Map<String, String> header = maps.get (0);

    List<CalElement> els = maps.subList (1, maps.size ())
                               .stream ()
                               .map (this::calElement)
                               .toList ();

    return new Calendar (header, els);
  }
}