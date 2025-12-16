package eirb.pg203;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import eirb.pg203.cli.CliConfig;
import eirb.pg203.cli.CliParser;
import eirb.pg203.cli.EventFilterType;
import eirb.pg203.filter.EventFilters;
import eirb.pg203.model.CalElement;
import eirb.pg203.model.Calendar;
import eirb.pg203.model.Event;
import eirb.pg203.model.ViewType;
import eirb.pg203.parser.Parser;

public class FilterTest {
    private final Parser parser = new Parser();

    private Path getTestFile(String filename) {
        return Paths.get("src", "test", "resources", filename);
    }

    @Test
    void testFromTo_SimpleEventFile() {
        List<String> args = List.of(
                "i2.ics", "events", 
                "-from", "20260101",
                "-to", "20260202");
        CliConfig config = CliParser.parse(args.toArray(new String[0]));
        assertEquals(config.getEventFilter(), EventFilterType.RANGE, "Il faut Spécifier les dates pour les options");
        Path file = getTestFile("i2.ics");
        Calendar calendar = parser.parse(file);
        assertNotNull(calendar, "Calendrier ne devrait pas être null pour i2.ics");
        List<CalElement> cals = calendar.get(ViewType.EVENTS);
        List<Event> events = new ArrayList<>();

        for (CalElement cal : cals) {
            events.add((Event) cal);
        }

        assertEquals(156, cals.size(), "Devrait avoir 156 EVENT");

        List<Event> filteredEvents = EventFilters.filter(events, config);

        assertEquals(16, filteredEvents.size(), "On doit avoir 16 events");

    }

    @Test
    void testFrom_SimpleEventFile(){
        List<String> args = List.of(
                "i2.ics", "events", 
                "-from", "20260101");
        CliConfig config = CliParser.parse(args.toArray(new String[0]));
        assertEquals(config.getEventFilter(), EventFilterType.RANGE, "Il faut Spécifier les dates pour les options");
        Path file = getTestFile("i2.ics");
        Calendar calendar = parser.parse(file);
        assertNotNull(calendar, "Calendrier ne devrait pas être null pour i2.ics");
        List<CalElement> cals = calendar.get(ViewType.EVENTS);
        List<Event> events = new ArrayList<>();

        for (CalElement cal : cals) {
            events.add((Event) cal);
        }

        assertEquals(156, cals.size(), "Devrait avoir 156 EVENT");

        List<Event> filteredEvents = EventFilters.filter(events, config);

        assertEquals(72, filteredEvents.size(), "On doit avoir 16 events");
    }

    @Test
    void testTo_SimpleEventFile(){
        List<String> args = List.of(
                "i2.ics", "events", 
                "-to", "20260101");
        CliConfig config = CliParser.parse(args.toArray(new String[0]));
        assertEquals(config.getEventFilter(), EventFilterType.RANGE, "Il faut Spécifier les dates pour les options");
        Path file = getTestFile("i2.ics");
        Calendar calendar = parser.parse(file);
        assertNotNull(calendar, "Calendrier ne devrait pas être null pour i2.ics");
        List<CalElement> cals = calendar.get(ViewType.EVENTS);
        List<Event> events = new ArrayList<>();

        for (CalElement cal : cals) {
            events.add((Event) cal);
        }

        assertEquals(156, cals.size(), "Devrait avoir 156 EVENT");

        List<Event> filteredEvents = EventFilters.filter(events, config);

        assertEquals(84, filteredEvents.size(), "On doit avoir 16 events");
    }

}