package eirb.pg203.filter;

import eirb.pg203.model.CalElement;
import java.util.List;

public interface CalendarFilter<T extends CalElement> {
    List<T> filter(List<T> elements);
}