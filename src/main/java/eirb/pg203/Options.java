package eirb.pg203;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Options {

    public static LocalDate dateGetter(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String[] elts = new String[3];
        elts[0] = date.substring(0, 4); // year
        elts[1] = date.substring(4, 6); // month
        elts[2] = date.substring(6, 8); // day
        String result = String.join("/", elts);
        return LocalDate.parse(result, formatter);
    }

    public static List<Map<String, String>> filter(String type, List<Map<String, String>> list){
        List<Map<String, String>> res = new ArrayList<>();

        switch (type) {

            case "today" -> {
                LocalDate now = LocalDate.now();

                for (Map<String, String> event : list) {
                    String start = event.get("DTSTAMP");

                    if (start != null) {
                        LocalDate date = dateGetter(start);

                        if (date.equals(now)) {
                            res.add(event);
                        }
                    }
                }
            }
            case "tomorrow" -> {
                LocalDate tmrw = LocalDate.now().plusDays(1);

                for (Map<String, String> event : list) {
                    String start = event.get("DTSTAMP");

                    if (start != null) {
                        LocalDate date = dateGetter(start);

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
