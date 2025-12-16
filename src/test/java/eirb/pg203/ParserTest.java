package eirb.pg203;

import static org.junit.jupiter.api.Assertions.*;

import eirb.pg203.model.Calendar;
import eirb.pg203.model.ViewType;
import eirb.pg203.parser.Parser;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class ParserTest
{
  private final Parser parser = new Parser ();

  private Path
  getTestFile (String filename)
  {
    return Paths.get ("src", "test", "resources", filename);
  }

  @Test
  void
  parserTest_TodoSomeFields () throws Exception
  {
    Path file = getTestFile ("simple_todo.ics");
    Calendar calendar = parser.parse (file);
    assertNotNull (calendar,
                   "Calendrier ne devrait pas être null pour simple_todo.ics");
    assert calendar.getTodos ().size ()
        == 1 : "Calendrier doit contenir un seul Todo";
    assert calendar.getEvents ().size ()
        == 0 : "Calendrier doit contenir 0 Events";
    assert calendar.getAll ().size ()
        == 1 : "Calendrier doit contenir 1 élement";
  }

  @Test
  void
  parserTest_EventSomeFields () throws Exception
  {
    Path file = getTestFile ("simple_event.ics");
    Calendar calendar = parser.parse (file);
    assertNotNull (
        calendar, "Calendrier ne devrait pas être null pour simple_event.ics");
    assert calendar.getTodos ().size ()
        == 0 : "Calendrier doit contenir 0 Todo";
    assert calendar.getEvents ().size ()
        == 1 : "Calendrier doit contenir 1 Events";
    assert calendar.getAll ().size ()
        == 1 : "Calendrier doit contenir 1 élement";
  }

  @Test
  void
  parserTest_TodoAllFields () throws Exception
  {
    Path file = getTestFile ("real_todo.ics");
    Calendar calendar = parser.parse (file);
    assertNotNull (calendar,
                   "Calendrier ne devrait pas être null pour real_todo.ics");
    assert calendar.getTodos ().size ()
        == 1 : "Calendrier doit contenir un seul Todo";
    assert calendar.getEvents ().size ()
        == 0 : "Calendrier doit contenir 0 Events";
    assert calendar.getAll ().size ()
        == 1 : "Calendrier doit contenir 1 élement";
  }

  @Test
  void
  parserTest_EventAllFields () throws Exception
  {
    Path file = getTestFile ("real_event.ics");
    Calendar calendar = parser.parse (file);
    assertNotNull (calendar,
                   "Calendrier ne devrait pas être null pour real_event.ics");
    assert calendar.getTodos ().size ()
        == 0 : "Calendrier doit contenir 0 Todo";
    assert calendar.getEvents ().size ()
        == 1 : "Calendrier doit contenir 1 Events";
    assert calendar.getAll ().size ()
        == 1 : "Calendrier doit contenir 1 élement";
  }

  @Test
  void
  parserTest_MultipleItemsFile () throws Exception
  {
    Path file = getTestFile ("multiple_items.ics");
    Calendar calendar = parser.parse (file);
    assertNotNull (
        calendar,
        "Calendrier ne devrait pas être null pour multiple_items.ics");
    assert calendar.getTodos ().size ()
        == 2 : "Calendrier doit contenir 2 Todo";
    assert calendar.getEvents ().size ()
        == 2 : "Calendrier doit contenir 2 Events";
    assert calendar.getAll ().size ()
        == 4 : "Calendrier doit contenir 4 élement";
  }
}