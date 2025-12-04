package eirb.pg203;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OptionsEvent extends Options<Event>{

    @Override
    public List<Event> filter(String type, List<Event> elements, String Date1, String Date2){
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
            case "-from" -> {
                LocalDate from = Utils.dateGetter(Date1);

                for (Event event: elements){
                    String start = event.getStart();

                    if (from != null){
                        LocalDate date = Utils.dateGetter(start);

                        if (date.isAfter(from) || date.isEqual(from)){
                            res.add(event);
                        }
                    }
                }
            }
            case "-to" -> {
                LocalDate to = Utils.dateGetter(Date1);

                for (Event event: elements){
                    String start = event.getStart();

                    if (to != null){
                        LocalDate date = Utils.dateGetter(start);

                        if (date.isBefore(to)|| date.isEqual(to)){
                            res.add(event);
                        }
                    }
                }
            }
            case "fromto" -> {
                LocalDate from = Utils.dateGetter(Date1);
                LocalDate to = Utils.dateGetter(Date2);
                for (Event event: elements){
                    String start = event.getStart();

                    if (from != null){
                        LocalDate date = Utils.dateGetter(start);

                        if (date.isBefore(to) && date.isAfter(from)|| date.isEqual(from)|| date.isEqual(to)){
                            res.add(event);
                        }
                    }
                }
            }
            case "-week" ->{
                LocalDate from = Utils.dateGetter(Date1);
                LocalDate to = from.plusDays(7);

                for (Event event : elements) {
                    String start = event.getStart();

                    if (start != null) {
                        LocalDate date = Utils.dateGetter(start);

                        if (date.equals(from) || date.equals(to) || date.isBefore(to) && date.isAfter(from)) {
                            res.add(event);
                        }
                    }
                }
            }
        }

        return res;
    }
}