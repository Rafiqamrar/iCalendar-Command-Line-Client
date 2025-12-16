package eirb.pg203;

import static org.junit.jupiter.api.Assertions.*;

import eirb.pg203.model.CalElement;
import eirb.pg203.model.Calendar;
import eirb.pg203.model.Event;
import eirb.pg203.model.Todo;
import eirb.pg203.model.ViewType;
import eirb.pg203.parser.Parser;
import eirb.pg203.util.Utils;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParsedContentTest
{
  private final Parser parser = new Parser ();

  private Path
  getTestFile (String filename)
  {
    return Paths.get ("src", "test", "resources", filename);
  }

  @Test
  void
  ParsedContentTest_SimpleTodo_Content () throws Exception
  {
    Path file = getTestFile ("simple_todo.ics");
    Calendar calendar = parser.parse (file);

    Todo todo = (calendar.getTodos ().get (0)); // vérifié dans ParserTest
    assertEquals ("simple-todo-123", todo.getUid (), "UID incorrect");
    assertEquals ("Test Todo Simple", todo.getSummary (), "Summary incorrect");
  }

  @Test
  void
  ParsedContentTest_SimpleEvent_Content () throws Exception
  {
    Path file = getTestFile ("simple_event.ics");
    Calendar calendar = parser.parse (file);
    Event event = calendar.getEvents ().get (0); // vérifié dans ParserTest
    assertEquals ("simple-event-456", event.getUid (), "UID incorrect");
    assertEquals ("Test Event Simple", event.getSummary (),
                  "Summary incorrect");
    assertEquals (Utils.dateTimeFormatter ("20250101T090000Z"),
                  event.getStart (), "DTSTART incorrect");
    assertEquals (Utils.dateTimeFormatter ("20250101T100000Z"),
                  event.getEnd (), "DTEND incorrect");
    assertEquals ("Salle A", event.getLocation (), "LOCATION incorrect");
  }

  @Test
  void
  ParsedContentTest_RealTodo_Content () throws Exception
  {
    Path file = getTestFile ("real_todo.ics");
    Calendar calendar = parser.parse (file);
    Todo todo = calendar.getTodos ().get (0);

    assertEquals ("457d2974-389e-44f4-b201-efdd66183608", todo.getUid (),
                  "UID incorrect");
    assertEquals ("Réviser l'examen de POO", todo.getSummary (),
                  "Summary incorrect");
    assertEquals ("Enseirb", todo.getLocation (), "Location incorrect");
    assertEquals (100, todo.getPercentComplete (), "Percent incorrect");
    assertEquals ("COMPLETED", todo.getStatus (), "Status incorrect");

    // Vérifier les propriétés avec get()
    assertEquals (5, todo.getPriority (), "Priority incorrect");
    assertEquals ("PUBLIC", todo.getTClass (), "Class incorrect");
    assertEquals ("mailto:foo@bar.fr", todo.getOrganizerMail (),
                  "Organizer incorrect");
    assertEquals (Utils.dateFormatter ("20251107"), todo.getDue (),
                  "Due incorrect");
    assertEquals (Utils.dateTimeFormatter ("20251104T204504Z"),
                  todo.getCompleted (), "Completed incorrect");
    assertEquals (Utils.dateTimeFormatter ("20251104T204504Z"),
                  todo.getLastModified (), "Last-modified incorrect");
    assertEquals (Utils.dateTimeFormatter ("20251104T204504Z"),
                  todo.getDtStamp (), "DTSTAMP incorrect");
    assertEquals (2, todo.getSequence (), "Sequence incorrect");
  }

  @Test
  void
  ParsedContentTest_RealEvent_Content () throws Exception
  {
    Path file = getTestFile ("real_event.ics");
    Calendar calendar = parser.parse (file);
    Event event = calendar.getEvents ().get (0);

    assertEquals ("Présentation PFA", event.getSummary (),
                  "Summary incorrect");
    assertEquals ("EA- (AMPHI A)", event.getLocation (), "Location incorrect");
    assertEquals (Utils.dateTimeFormatter ("20251110T151000Z"),
                  event.getStart (), "DTSTART incorrect");
    assertEquals (Utils.dateTimeFormatter ("20251110T154000Z"),
                  event.getEnd (), "DTEND incorrect");

    String description = event.getDescription ();
    assertNotNull (description, "Description ne devrait pas être null");
    assertTrue (description.contains ("Reunion"),
                "Description devrait contenir 'Reunion'");
    assertTrue (description.contains ("JANIN David"),
                "Description devrait contenir 'JANIN David'");
    assertTrue (description.contains ("Exporté le:04/11/2025 22:58"),
                "Description devrait contenir la date d'export");

    assertEquals ("ADE60323032352d323032362d343731362d302d30", event.getUid (),
                  "UID incorrect");
    assertEquals (Utils.dateTimeFormatter ("20251104T215832Z"),
                  event.getDtStamp (), "DTSTAMP incorrect");
    assertEquals (Utils.dateTimeFormatter ("19700101T000000Z"),
                  event.getCreated (), "CREATED incorrect");
    assertEquals (Utils.dateTimeFormatter ("20251104T215832Z"),
                  event.getLastModified (), "LAST-MODIFIED incorrect");
    assertEquals (2142021698, event.getSequence (), "SEQUENCE incorrect");
  }

  @Test
  void
  ParsedContentTest_MultipleItems_Content () throws Exception
  {
    Path file = getTestFile ("multiple_items.ics");
    Calendar calendar = parser.parse (file);

    assertNotNull (calendar, "Le calendrier ne devrait pas être null");

    List<Todo> todos = calendar.getTodos ();
    List<Event> events = calendar.getEvents ();

    assertEquals (2, todos.size (), "Devrait avoir 2 TODOs");
    assertEquals (2, events.size (), "Devrait avoir 2 EVENTS");

    Todo todo1 = todos.get (0);
    assertEquals ("todo-1", todo1.getUid (), "TODO1 UID incorrect");
    assertEquals ("First Todo", todo1.getSummary (),
                  "TODO1 Summary incorrect");
    assertEquals (1, todo1.getPriority (), "TODO1 Priority incorrect");

    Todo todo2 = todos.get (1);
    assertEquals ("todo-2", todo2.getUid (), "TODO2 UID incorrect");
    assertEquals ("Second Todo", todo2.getSummary (),
                  "TODO2 Summary incorrect");
    assertEquals (2, todo2.getPriority (), "TODO2 Priority incorrect");

    Event event1 = events.get (0);
    assertEquals ("event-1", event1.getUid (), "EVENT1 UID incorrect");
    assertEquals ("First Event", event1.getSummary (),
                  "EVENT1 Summary incorrect");
    assertEquals (Utils.dateTimeFormatter ("20250101T100000Z"),
                  event1.getStart (), "EVENT1 DTSTART incorrect");

    Event event2 = events.get (1);
    assertEquals ("event-2", event2.getUid (), "EVENT2 UID incorrect");
    assertEquals ("Second Event", event2.getSummary (),
                  "EVENT2 Summary incorrect");
    assertEquals (Utils.dateTimeFormatter ("20250102T100000Z"),
                  event2.getStart (), "EVENT2 DTSTART incorrect");
  }

  @Test
  void
  ParsedContentTest_TodoWithSemicolonInKey () throws Exception
  {
    Path file = getTestFile ("real_todo.ics");
    Calendar calendar = parser.parse (file);

    assertNotNull (calendar, "Le calendrier ne devrait pas être null");

    List<Todo> todos = calendar.getTodos ();
    assertFalse (todos.isEmpty (), "Devrait avoir des TODOs");

    Todo todo = todos.get (0);

    String organizer = todo.getOrganizerMail ();
    assertEquals ("mailto:foo@bar.fr", organizer, "Organizer incorrect");
    String cn = todo.getOrganizerName ();
    assertEquals ("Alice", cn, "Organizer incorrect");

    LocalDate due = todo.getDue ();
    assertEquals (Utils.dateFormatter ("20251107"), due, "Due date incorrect");
  }

  @Test
  void
  ParsedContentTest_EventMultilineDescription () throws Exception
  {
    Path file = getTestFile ("real_event.ics");
    Calendar calendar = parser.parse (file);

    assertNotNull (calendar, "Le calendrier ne devrait pas être null");

    List<Event> events = calendar.getEvents ();
    assertFalse (events.isEmpty (), "Devrait avoir des EVENTS");

    Event event = events.get (0);

    String description = event.getDescription ();
    assertNotNull (description, "Description ne devrait pas être null");
    assertTrue (description.contains ("\n"),
                "Description devrait contenir des retours à la ligne");
    assertTrue (description.contains ("Reunion"),
                "Description devrait contenir 'Reunion'");
    assertTrue (description.contains ("I2"),
                "Description devrait contenir 'I2'");
    assertTrue (description.contains ("JANIN David"),
                "Description devrait contenir 'JANIN David'");
  }

  @Test
  void
  ParsedContentTest_EmptyCalendar () throws Exception
  {
    Path file = getTestFile ("empty.ics");
    Calendar calendar = parser.parse (file);

    assertNotNull (calendar, "Le calendrier ne devrait pas être null");

    List<Todo> todos = calendar.getTodos ();
    List<Event> events = calendar.getEvents ();

    assertTrue (todos.isEmpty (), "Devrait avoir 0 TODOs");
    assertTrue (events.isEmpty (), "Devrait avoir 0 EVENTS");
  }

  @Test
  void
  ParsedContentTest_CalendarInfoInFirstElement () throws Exception
  {
    Path file = getTestFile ("real_todo.ics");
    Calendar calendar = parser.parse (file);

    assertNotNull (calendar, "Le calendrier ne devrait pas être null");

    List<Todo> todos = calendar.getTodos ();
    assertFalse (todos.isEmpty (), "Devrait avoir des TODOs");

    Todo todo = todos.get (0);
    assertNotNull (todo.getSummary (),
                   "Todo summary ne devrait pas être null");
    assertNotNull (todo.getUid (), "Todo UID ne devrait pas être null");
  }
}