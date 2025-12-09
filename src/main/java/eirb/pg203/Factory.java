package eirb.pg203;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;
public class Factory {
    public static Todo makeTodo(Map<String, String> maps){
        String UID=null;
        String SUMMARY=null;
        String LOCATION=null;
        String STATUS=null;
        String PERCENT_COMPLETE=null;
        Number DUE=null;
        String CLASS=null;
        Number PRIORITY=null;
        LocalDate LAST_MODIFIED=null;
        LocalDate DTSTAMP=null;
        Number SEQUENCE=null;
        String ORGANIZER_name=null;
        String ORGANIZER_mail=null;

            


        return new Todo(
                UID,
                SUMMARY,
                LOCATION,
                STATUS,
                PERCENT_COMPLETE,
                DUE,
                CLASS,
                PRIORITY,
                LAST_MODIFIED,
                DTSTAMP,
                SEQUENCE,
                ORGANIZER_name,
                ORGANIZER_mail);
    }

    public static CalElement calElement(Map<String, String> map) {
        String type = map.get("BEGIN");

        switch (type) {
            case "VEVENT" : return new Event(map);
            case "VTODO" : return makeTodo(map);
            default : throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    public static Calender calender(List<Map<String,String>> maps) {

        if (maps.isEmpty()) {
            return new Calender(new HashMap<>(), new ArrayList<>());
        }
        Map<String, String> header = maps.get(0);

        // List<CalElement> els =maps.subList(1, maps.size()) 
    List<CalElement> els = maps.subList(1, maps.size())
        .stream()
        .map(Factory::calElement)
        .toList();

        return new Calender(header, els);
    }
}