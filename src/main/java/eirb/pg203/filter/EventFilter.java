package eirb.pg203.filter;

import eirb.pg203.cli.TemporalOption;
import eirb.pg203.model.Event;
import eirb.pg203.Utils;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.List;

public class EventFilter implements CalendarFilter<Event> {
    
    private final TemporalOption option;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    
    
    // option (TODAY, TOMORROW, WEEK, RANGE, ALL)
    public EventFilter(TemporalOption option, LocalDate fromDate, LocalDate toDate) {
        this.option = option;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    
    @Override
    public List<Event> filter(List<Event> elements) {
        return switch (option) {
            case TODAY -> filterByDate(elements, LocalDate.now());
            case TOMORROW -> filterByDate(elements, LocalDate.now().plusDays(1));
            case WEEK -> filterByWeek(elements, LocalDate.now());
            case RANGE -> filterByRange(elements, fromDate, toDate);
            case ALL -> elements; // No filter
        };
    }
    
    private List<Event> filterByDate(List<Event> events, LocalDate targetDate) {
        return events.stream()
            .filter(event -> {
                LocalDate eventDate = getEventDate(event);
                return eventDate != null && eventDate.equals(targetDate);
            })
            .toList();
    }
    
    private List<Event> filterByWeek(List<Event> events, LocalDate today) {
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
        
        return filterByRange(events, startOfWeek, endOfWeek);
    }
    
    private List<Event> filterByRange(List<Event> events, LocalDate from, LocalDate to) {
        return events.stream()
            .filter(event -> {
                LocalDate eventDate = getEventDate(event);
                if (eventDate == null) return false;
                
                return !eventDate.isBefore(from) && !eventDate.isAfter(to);
            })
            .toList();
    }
    
    private LocalDate getEventDate(Event event) {
        String start = event.getStart();
        if (start == null || start.isEmpty()) {
            return null;
        }
        return Utils.dateGetter(start);
    }
}
