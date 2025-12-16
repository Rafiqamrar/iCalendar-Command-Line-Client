package eirb.pg203;

import eirb.pg203.cli.*;
import eirb.pg203.filter.*;
import eirb.pg203.model.*;
import eirb.pg203.output.*;
import eirb.pg203.parser.*;
import java.util.ArrayList;
import java.util.List;

public class Main
{

  public static void
  main (String[] args)
  {
    try
      {
        CliConfig config = CliParser.parse (args);

        Parser parser = new Parser ();
        Calendar cal = parser.parse (config.getInputFile ());

        List<CalElement> result = new ArrayList<> ();

        if (config.getViewType () == ViewType.EVENTS)
          {
            List<Event> filteredEvents = EventFilters.filter (cal.getEvents(), config);
            result.addAll (filteredEvents);
          }
        else
          {
            List<Todo> filteredTodos = TodoFilters.filter (cal.getTodos(), config);
            result.addAll (filteredTodos);
          }

        OutputWriter writer = OutputWriterFactory.create (config);
        writer.write (result, config.getOutputStream ());
      }
    catch (CliException e)
      {
        System.err.println ("Erreur: " + e.getMessage ());
      }
    catch (Exception e)
      {
        System.err.println ("Erreur interne: " + e.getMessage ());
        e.printStackTrace ();
      }
  }
}