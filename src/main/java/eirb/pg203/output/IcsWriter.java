package eirb.pg203.output;

import eirb.pg203.model.CalElement;
import eirb.pg203.model.Event;
import eirb.pg203.model.Todo;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Writer pour générer une sortie au format .
 */
public class IcsWriter implements OutputWriter
{

  // Format pour les dates avec heure: 20250101T100000Z
  private static final DateTimeFormatter FMT_DATETIME
      = DateTimeFormatter.ofPattern ("yyyyMMdd'T'HHmmss'Z'");

  // Format pour les dates seules: 20250101
  private static final DateTimeFormatter FMT_DATE
      = DateTimeFormatter.ofPattern ("yyyyMMdd");

  @Override
  public void
  write (List<CalElement> elements, OutputStream out)
  {
    PrintWriter pw = new PrintWriter (out);

    pw.println ("BEGIN:VCALENDAR");
    pw.println ("VERSION:2.0");
    pw.println ("PRODID:-//clical//EN");

    for (CalElement el : elements)
      {
        if (el instanceof Event e)
          {
            writeEvent (e, pw);
          }
        else if (el instanceof Todo t)
          {
            writeTodo (t, pw);
          }
      }

    pw.println ("END:VCALENDAR");
    pw.flush ();
  }

  /**
   * Écrit un événement au format VEVENT ICS.
   */
  private void
  writeEvent (Event e, PrintWriter pw)
  {
    pw.println ("BEGIN:VEVENT");

    writeField (pw, "UID", e.getUid ());
    writeField (pw, "SUMMARY", e.getSummary ());
    writeField (pw, "LOCATION", e.getLocation ());
    writeField (pw, "DESCRIPTION", e.getDescription ());

    writeDateTimeField (pw, "DTSTART", e.getStart ());
    writeDateTimeField (pw, "DTEND", e.getEnd ());
    writeDateTimeField (pw, "CREATED", e.getCreated ());
    writeDateTimeField (pw, "LAST-MODIFIED", e.getLastModified ());
    writeDateTimeField (pw, "DTSTAMP", e.getDtStamp ());

    writeField (pw, "SEQUENCE", e.getSequence ());

    pw.println ("END:VEVENT");
  }

  /**
   * Écrit une tâche au format VTODO ICS.
   */
  private void
  writeTodo (Todo t, PrintWriter pw)
  {
    pw.println ("BEGIN:VTODO");

    writeField (pw, "UID", t.getUid ());
    writeField (pw, "SUMMARY", t.getSummary ());
    writeField (pw, "LOCATION", t.getLocation ());
    writeField (pw, "STATUS", t.getStatus ());
    writeField (pw, "CLASS", t.getTClass ());

    writeDateField (pw, "DUE;VALUE=DATE", t.getDue ());
    writeDateField (pw, "DTSTART;VALUE=DATE", t.getDtstart ());

    writeDateTimeField (pw, "COMPLETED", t.getCompleted ());
    writeDateTimeField (pw, "LAST-MODIFIED", t.getLastModified ());
    writeDateTimeField (pw, "DTSTAMP", t.getDtStamp ());

    writeField (pw, "PERCENT-COMPLETE", t.getPercentComplete ());
    writeField (pw, "PRIORITY", t.getPriority ());
    writeField (pw, "SEQUENCE", t.getSequence ());

    if (t.getOrganizerName () != null && t.getOrganizerMail () != null)
      {
        pw.println ("ORGANIZER;CN=" + t.getOrganizerName () + ":"
                    + t.getOrganizerMail ());
      }
    else if (t.getOrganizerMail () != null)
      {
        pw.println ("ORGANIZER:" + t.getOrganizerMail ());
      }

    pw.println ("END:VTODO");
  }

  /**
   * Écrit un champ texte si la valeur n'est pas nulle.
   */
  private void
  writeField (PrintWriter pw, String fieldName, Object value)
  {
    if (value != null)
      {
        pw.println (fieldName + ":" + value);
      }
  }

  /**
   * Écrit un champ de date-heure au format ICS (yyyyMMdd'T'HHmmss'Z').
   */
  private void
  writeDateTimeField (PrintWriter pw, String fieldName, LocalDateTime value)
  {
    if (value != null)
      {
        pw.println (fieldName + ":" + value.format (FMT_DATETIME));
      }
  }

  /**
   * Écrit un champ de date simple au format ICS (yyyyMMdd).
   */
  private void
  writeDateField (PrintWriter pw, String fieldName, LocalDate value)
  {
    if (value != null)
      {
        pw.println (fieldName + ":" + value.format (FMT_DATE));
      }
  }
}