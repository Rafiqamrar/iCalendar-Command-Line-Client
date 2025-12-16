package eirb.pg203.output;

import eirb.pg203.model.CalElement;
import eirb.pg203.model.Event;
import eirb.pg203.model.Todo;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Writer pour la sortie texte (console).
 * Utilise simplement la méthode toString() de chaque élément pour l'affichage.
 */
public class TextWriter implements OutputWriter
{

  @Override
  public void
  write (List<CalElement> elements, OutputStream out)
  {
    PrintWriter pw = new PrintWriter (out);
    for (T el : elements)
      {
        // Délègue le formatage à la méthode toString() de chaque élément
        pw.println (el.toString ());
      }
    pw.flush ();
  }
}
