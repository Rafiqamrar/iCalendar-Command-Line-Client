package eirb.pg203;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Factory {
    public static CalElement calElement(Map<String, String> map) {
        String type = map.get("BEGIN");

        switch (type) {
            case "VEVENT" : return new Event(map);
            case "VTODO" : return new Todo(map);
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