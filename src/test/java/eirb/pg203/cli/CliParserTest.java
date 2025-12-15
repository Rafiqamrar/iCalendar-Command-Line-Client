package eirb.pg203.cli;

import static org.junit.jupiter.api.Assertions.*;

import eirb.pg203.model.ViewType;
import org.junit.jupiter.api.Test;

public class CliParserTest
{

  @Test
  public void
  testParseEventsCommand ()
  {
    String[] args = { "test.ics", "events" };
    CliConfig config = CliParser.parse (args);

    assertEquals (ViewType.EVENTS, config.getViewType ());
    assertEquals (EventFilterType.TODAY, config.getEventFilter ());
  }

  @Test
  public void
  testParseTodosCommand ()
  {
    String[] args = { "test.ics", "todos" };
    CliConfig config = CliParser.parse (args);

    assertEquals (ViewType.TODOS, config.getViewType ());
    assertEquals (TodoFilterType.INCOMPLETE, config.getTodoFilter ());
  }

  @Test
  public void
  testInvalidCommandThrowsException ()
  {
    String[] args = { "test.ics" };

    assertThrows (CliException.class, () -> { CliParser.parse (args); });
  }

  @Test
  public void
  testTodosWithFromOptionThrowsException ()
  {
    String[] args = { "test.ics", "todos", "-from", "20250101" };

    assertThrows (CliException.class, () -> { CliParser.parse (args); });
  }
}