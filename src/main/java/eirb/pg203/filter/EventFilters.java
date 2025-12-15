package eirb.pg203.filter;

import eirb.pg203.cli.CliConfig;
import eirb.pg203.cli.EventFilterType;
import eirb.pg203.model.Event;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class EventFilters
{
  public static List<Event>
  filter (List<Event> events, CliConfig config)
  {
    return filterEvents (events, config.getEventFilter (), config.getFrom (),
                         config.getTo ());
  }

  public static List<Event>
  filterEvents (List<Event> events, EventFilterType option, LocalDate fromDate,
                LocalDate toDate)
  {
    switch (option)
      {
      case TODAY:
        return filterByDate (events, LocalDate.now ());
      case TOMORROW:
        return filterByDate (events, LocalDate.now ().plusDays (1));
      case WEEK:
        return filterByWeek (events, LocalDate.now ());
      case RANGE:
        return filterByRange (events, fromDate, toDate);
      default:
        return events;
      }
  }

  private static List<Event>
  filterByDate (List<Event> events, LocalDate targetDate)
  {
    return events.stream ()
        .filter (event -> {
          LocalDate eventDate = getEventDate (event);
          return eventDate != null && eventDate.equals (targetDate);
        })
        .toList ();
  }

  private static List<Event>
  filterByWeek (List<Event> events, LocalDate today)
  {
    LocalDate startOfWeek = today.with (DayOfWeek.MONDAY);
    LocalDate endOfWeek = today.with (DayOfWeek.SUNDAY);
    return filterByRange (events, startOfWeek, endOfWeek);
  }

  private static List<Event>
  filterByRange (List<Event> events, LocalDate from, LocalDate to)
  {
    return events.stream ()
        .filter (event -> {
          LocalDate eventDate = getEventDate (event);
          if (eventDate == null)
            {
              return false;
            }
          return !eventDate.isBefore (from) && !eventDate.isAfter (to);
        })
        .toList ();
  }

  private static LocalDate
  getEventDate (Event event)
  {
    if (event.getStart () == null)
      {
        return null;
      }
    return event.getStart ().toLocalDate ();
  }
}
