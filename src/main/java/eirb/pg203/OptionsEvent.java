package eirb.pg203;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OptionsEvent extends Options<Event>{

    @Override
    public List<Event> filter(String type, List<Event> elements){
        List<Event> res = new ArrayList<>();

        switch (type.toLowerCase()) {

            case "-today" -> {
                LocalDate now = LocalDate.now();

                for (Event event : elements) {
                    String start = event.getStart();

                    if (start != null) {
                        LocalDate date = Utils.dateGetter(start);

                        if (date.equals(now)) {
                            res.add(event);
                        }
                    }
                }
            }
            case "-tomorrow" -> {
                LocalDate tmrw = LocalDate.now().plusDays(1);

                for (Event event : elements) {
                    String start = event.getStart();

                    if (start != null) {
                        LocalDate date = Utils.dateGetter(start);

                        if (date.equals(tmrw)) {
                            res.add(event);
                        }
                    }
                }
            }
            case "-yesterday" -> {
                LocalDate tmrw = LocalDate.now().plusDays(-1);

                for (Event event : elements) {
                    String start = event.getStart();

                    if (start != null) {
                        LocalDate date = Utils.dateGetter(start);

                        if (date.equals(tmrw)) {
                            res.add(event);
                        }
                    }
                }
            }
        }

        return res;
    }
}