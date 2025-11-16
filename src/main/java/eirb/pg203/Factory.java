package eirb.pg203;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;

public class Factory {
    public static CalElement calElement(Map<String, String> map) {
        String type = map.get("BEGIN");

        switch (type) {
            case "VEVENT" : return new Event(map);
            default : throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    public static Calender calender(Path path) {
        List<Map<String, String>> parsedChunks = Parser.parse(path);


        Map<String, String> header = parsedChunks.get(0);

        List<CalElement> els =parsedChunks.subList(1, 3)
                        .stream()
                        .map(Factory::calElement)
                        .toList();

        return new Calender(header, els);
    }
}