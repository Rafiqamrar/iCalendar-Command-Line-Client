package eirb.pg203;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Args: " + Arrays.toString(args));
        // System.out.println(loadCalendarData(Path.of("src", "test", "resources", "i2.ics")));
        // ptitTestLoadLines();
        ptitTestfileToChunks();
        ptitTestparseChunk();
    }

    public static String loadCalendarData(Path path) throws IOException {
        return Files.readString(path)
            .lines()
            .limit(20)
            .collect(Collectors.joining("\n"));
    }

    public static void ptitTestLoadLines(){
        List<String> l = Utils.loadLines(Path.of("src", "test", "resources", "i2.ics")).subList(0, 5);
        for(String line : l ){
            System.out.println(line);
        }
    }

    // prints the first chunk of "i2.ics" as a String
    public static void ptitTestfileToChunks(){
        String chunk = Parser.fileToChunks(Path.of("src", "test", "resources", "i2.ics")).get(0);
        
        System.out.println("\n--- VEVENT chunk ---");
        System.out.println(chunk);
        System.out.println("---------------------\n");
    }

    // parses the first chunk of "i2.ics" and prints it
    public static void ptitTestparseChunk(){
        String chunk = Parser.fileToChunks(Path.of("src", "test", "resources", "i2.ics")).get(0);
        Map<String,String> map = Parser.parseChunk(chunk);
        
        System.out.println("\n--- parsed VEVENT chunk ---");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("---------------------\n");
    }
    // no need to test Parser.parse since it does the same thing basically
}
