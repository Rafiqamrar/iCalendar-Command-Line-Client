package eirb.pg203.output;

import eirb.pg203.model.CalElement;
import eirb.pg203.model.Event;
import eirb.pg203.model.Todo;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Writer pour générer une sortie HTML à partir des éléments de calendrier.
 */
public class HtmlWriter implements OutputWriter
{

  @Override
  public void
  write (List<? extends CalElement> elements, OutputStream out)
  {
    PrintWriter pw = new PrintWriter (out);

    pw.println ("<!DOCTYPE html>");
    pw.println ("<html lang=\"en\">");
    pw.println ("<head>");
    pw.println ("<meta charset=\"UTF-8\">");
    pw.println ("<title>clical output</title>");
    pw.println ("</head>");
    pw.println ("<body>");

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

    pw.println ("</body>");
    pw.println ("</html>");
    pw.flush ();
  }

  /**
   * Génère la représentation HTML d'un événement.
   */
  private void
  writeEvent (Event e, PrintWriter pw)
  {
    pw.println ("<section>");
    pw.println ("<h2>Event</h2>");
    pw.println ("<ul>");
    li (pw, "Summary", e.getSummary ());
    li (pw, "Start", e.getStart ());
    li (pw, "End", e.getEnd ());
    li (pw, "Location", e.getLocation ());
    li (pw, "Description", e.getDescription ());
    pw.println ("</ul>");
    pw.println ("</section>");
  }

  /**
   * Génère la représentation HTML d'une tâche (TODO).
   */
  private void
  writeTodo (Todo t, PrintWriter pw)
  {
    pw.println ("<section>");
    pw.println ("<h2>Todo</h2>");
    pw.println ("<ul>");
    li (pw, "Summary", t.getSummary ());
    li (pw, "Status", t.getStatus ());
    li (pw, "Due", t.getDue ());
    li (pw, "Percent", t.getPercentComplete ());
    pw.println ("</ul>");
    pw.println ("</section>");
  }

  /**
   * Méthode pour générer un élément de liste HTML.
   */
  private void
  li (PrintWriter pw, String key, Object value)
  {
    if (value != null)
      {
        pw.println ("<li><strong>" + key + ":</strong> " + value + "</li>");
      }
  }
}
