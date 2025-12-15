package eirb.pg203.output;

import eirb.pg203.model.CalElement;
import eirb.pg203.model.Event;
import eirb.pg203.model.Todo;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class TextWriter implements OutputWriter
{

  @Override
  public void
  write (List<? extends CalElement> elements, OutputStream out)
  {
    PrintWriter pw = new PrintWriter (out);
    for (CalElement el : elements)
      {
        pw.println (el.toString ());
      }
    pw.flush ();
  }
}
