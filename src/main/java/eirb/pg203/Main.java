package eirb.pg203;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Args: " + Arrays.toString(args));
        // System.out.println(loadCalendarData(Path.of("src", "test", "resources", "i2.ics")));
        //ptitTest(args);

                if (args.length < 2) {
                    System.err.println("Usage: clical <file.ics> <events|todos>");
                    return;
                    }

                String path = args[0];
                String mode = args[1].toUpperCase();  // pour utiliser l’enum proprement

                ViewType viewType;

                try {
                    viewType = ViewType.valueOf(mode);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid mode: " + args[1]);
                    System.err.println("Expected: events | todos");
                    return;
                }
                Parser parser = new Parser();
                Calender calendar = parser.parse(Path.of(path));

                ViewType type = ViewType.valueOf(mode.toUpperCase());
                for (CalElement el : calendar.get(type)) {
                    System.out.println(el);
                    System.out.println("---------------------------");
                }


    
/* 
    public static String loadCalendarData(Path path) throws IOException {
        return Files.readString(path)
            .lines()
            .limit(20)
            .collect(Collectors.joining("\n"));
    }

    */
    
        

       
}
/* 
    public static void ptitTest(String[] args){
        Parser p = new Parser();
        Calender c = p.parse(Path.of(args[0]));
        c.display(args[1].substring(0, args[1].length()-1).toUpperCase());
    }
*/


}
