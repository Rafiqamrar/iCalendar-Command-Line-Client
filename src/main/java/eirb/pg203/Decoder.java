package eirb.pg203;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class Decoder {
    // "ORGANIZER;CN=X" : "Y" -> X
    // Returns null if no CN is present.
    public static String getOname(Map<String, String> map) {
        if (map == null) return null;
        System.out.println("[DEBUG] getOname called2 on Map : " + map.keySet());
        for (String key : map.keySet()) {
            if (key == null) continue;
            if (key.startsWith("ORGANIZER")) {
                System.err.println("[DEBUG] " + key);
                int semi = key.indexOf(";CN=");
                if (semi >= 0) {
                    // value after ";CN="
                    String cn = key.substring(semi + 4);
                    return cn.isEmpty() ? null : cn;
                }
                return null;
            }
        }
        return null;
    }

    // "ORGANIZER;CN=X" : "Y" -> Y
    // Returns null if no CN is present.
    public static String getOmail(Map<String, String> map) {
        if (map == null) return null;
        String value = map.get("ORGANIZER"); // in case theres no CN parameter
        if (value == null) {
            // Find the first key that starts with ORGANIZER
            for (java.util.Map.Entry<String, String> e : map.entrySet()) {
                if (e.getKey() != null && e.getKey().startsWith("ORGANIZER")) {
                    value = e.getValue();
                    break;
                }
            }
        }
        if (value == null) return null;
        value = value.trim();
        if (value.isEmpty()) return null;
        return value;
    }

    public static Todo makeTodo(Map<String, String> map){
        String UID=map.get("UID");
        String SUMMARY=map.get("SUMMARY");
        String LOCATION=map.get("LOCATION");
        String STATUS=map.get("STATUS");
        Number PERCENT_COMPLETE=Utils.StoNum(map.get("PERCENT-COMPLETE"));
        LocalDateTime COMPLETED=Utils.dateTimeFormatter(map.get("COMPLETED"));
        LocalDate DUE=Utils.dateFormatter(map.get("DUE;VALUE=DATE"));
        LocalDate DTSTART=Utils.dateFormatter(map.get("DTSTART;VALUE=DATE"));
        String CLASS=map.get("CLASS");
        Number PRIORITY=Utils.StoNum(map.get("PRIORITY"));
        LocalDateTime LAST_MODIFIED=Utils.dateTimeFormatter(map.get("LAST-MODIFIED"));
        LocalDateTime DTSTAMP=Utils.dateTimeFormatter(map.get("DTSTAMP"));
        Number SEQUENCE=Utils.StoNum(map.get("SEQUENCE"));
        String ORGANIZER_name=Decoder.getOname(map);
        String ORGANIZER_mail=Decoder.getOmail(map);
        return new Todo(
                UID,
                SUMMARY,
                LOCATION,
                STATUS,
                PERCENT_COMPLETE,
                COMPLETED,
                DUE,
                DTSTART,
                CLASS,
                PRIORITY,
                LAST_MODIFIED,
                DTSTAMP,
                SEQUENCE,
                ORGANIZER_name,
                ORGANIZER_mail);
    }

    public static Event makeEvent(Map<String, String> map) {
        String UID=map.get("UID");
        String SUMMARY=map.get("SUMMARY");
        String LOCATION=map.get("LOCATION");
        LocalDateTime LAST_MODIFIED=Utils.dateTimeFormatter(map.get("LAST-MODIFIED"));
        LocalDateTime DTSTAMP=Utils.dateTimeFormatter(map.get("DTSTAMP"));
        LocalDateTime DTSTART = Utils.dateTimeFormatter(map.get("DTSTART"));
        LocalDateTime DTEND = Utils.dateTimeFormatter(map.get("DTEND"));
        LocalDateTime CREATED = Utils.dateTimeFormatter(map.get("CREATED"));
        String DESCRIPTION = map.get("DESCRIPTION");
        Number SEQUENCE=Utils.StoNum(map.get("SEQUENCE"));
        return new Event(
                UID,
                SUMMARY,
                LOCATION,
                LAST_MODIFIED,
                DTSTAMP,
                DTSTART,
                DTEND,
                CREATED,
                DESCRIPTION,
                SEQUENCE);
    }

    public static CalElement calElement(Map<String, String> map) {
        String type = map.get("BEGIN");

        switch (type) {
            case "VEVENT" : return makeEvent(map);
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
        .map(Decoder::calElement)
        .toList();

        return new Calender(header, els);
    }
}