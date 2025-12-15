package eirb.pg203;

import static org.junit.jupiter.api.Assertions.*;

import eirb.pg203.model.Calendar;
import eirb.pg203.parser.Parser;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class ParserTest {
  private final Parser parser = new Parser();

  private Path getTestFile(String filename) {
    return Paths.get("src", "test", "resources", filename);
  }

  @Test
  void testParse_SimpleTodoFile() {
    Path file = getTestFile("simple_todo.ics");
    Calendar calendar = parser.parse(file);
    assertNotNull(calendar, "Calendrier ne devrait pas être null pour simple_todo.ics");
  }

  @Test
  void testParse_SimpleEventFile() {
    Path file = getTestFile("simple_event.ics");
    Calendar calendar = parser.parse(file);
    assertNotNull(calendar, "Calendrier ne devrait pas être null pour simple_event.ics");
  }

  @Test
  void testParse_RealTodoFile() {
    Path file = getTestFile("real_todo.ics");
    Calendar calendar = parser.parse(file);
    assertNotNull(calendar, "Calendrier ne devrait pas être null pour real_todo.ics");
  }

  @Test
  void testParse_RealEventFile() {
    Path file = getTestFile("real_event.ics");
    Calendar calendar = parser.parse(file);
    assertNotNull(calendar, "Calendrier ne devrait pas être null pour real_event.ics");
  }

  @Test
  void testParse_MultipleItemsFile() {
    Path file = getTestFile("multiple_items.ics");
    Calendar calendar = parser.parse(file);
    assertNotNull(calendar, "Calendrier ne devrait pas être null pour multiple_items.ics");
  }
}