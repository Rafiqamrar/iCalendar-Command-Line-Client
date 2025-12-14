package eirb.pg203.filter;

import eirb.pg203.Event;
import eirb.pg203.CalElement;
import eirb.pg203.cli.CliConfig;
import eirb.pg203.cli.EventFilterType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventFilter {

    public static List<CalElement> filter(List<Event> events, CliConfig cfg) {

        List<CalElement> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Event e : events) {
            // Récupérer le LocalDateTime de début
            if (e.getStart() == null) continue;
            LocalDate eventDate = e.getStart().toLocalDate(); // Convertir en LocalDate pour comparer

            boolean keep = false;

            switch (cfg.getEventFilter()) {
                case TODAY -> keep = eventDate.equals(today);
                case TOMORROW -> keep = eventDate.equals(today.plusDays(1));
                case WEEK -> keep = !eventDate.isBefore(today) && !eventDate.isAfter(today.plusDays(7));
                case RANGE -> {
                    LocalDate from = cfg.getFrom();//.toLocalDate();
                    LocalDate to = cfg.getTo();//.toLocalDate();
                    keep = !eventDate.isBefore(from) && !eventDate.isAfter(to);
                }
            }

            if (keep) {
                result.add(e);
            }
        }
        return result;
    }
}
