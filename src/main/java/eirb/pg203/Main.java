/*package eirb.pg203;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Args: " + Arrays.toString(args));

        // Vérification minimum d'arguments
        if (args.length < 2) {
            System.err.println("Usage: clical <file.ics> <events|todos> [option]");
            return;
        }

        String path = args[0];
        String mode = args[1].toUpperCase();
        // String option;
        // if (args.length >= 3 && args.length <= 4) {
        //     option = args[2];
        // }
        // else if(args.length > 4 && args[2].equals("-from") && args[4].equals("-to")){
        //     option = "fromto";
        // }
        // else{
        //     option = null;
        // }
        // String date1 = (args.length >= 4) ? args[3] : null;
        // String date2 = (args.length == 6) ? args[5] : null;


        // Vérification du mode
        ViewType viewType;
        try {
            viewType = ViewType.valueOf(mode);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid mode: " + args[1]);
            System.err.println("Expected: events | todos");
            return;
        }

        // Parsing du fichier
        Parser parser = new Parser();
        Calender calendar = parser.parse(Path.of(path));

        // Récupérer les éléments correspondants au type (events ou todos)
        // List<CalElement> elements = calendar.get(viewType);

        // // ---- FILTRAGE ----
        // if (option != null) {

        //     if (viewType == ViewType.EVENTS) {

        //         List<Event> events = elements.stream().map(e -> (Event) e).toList();

        //         OptionsEvent ev = new OptionsEvent();

        //         List<Event> filtered = ev.filter(option, events, date1, date2);

        //         elements = List.copyOf(filtered);

        //     }
        // }

        for (CalElement el : calendar.get(viewType)) {
            System.out.println(el);
        }
    }
}

*/

package eirb.pg203;

import eirb.pg203.cli.*;
import eirb.pg203.filter.*;
import eirb.pg203.output.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            
            CliConfig config = CliParser.parse(args);

            
            Parser parser = new Parser();
            Calender cal = parser.parse(config.getInputFile());

            List<CalElement> result = new ArrayList<>();

            //  Sélection + filtrage
            if (config.getViewType() == ViewType.EVENTS) {
                List<Event> events = new ArrayList<>();
                for (CalElement el : cal.get(ViewType.EVENTS)) {
                    if (el instanceof Event) {
                        events.add((Event) el);
                    }
                }
                result = EventFilter.filter(events, config);

            } else {
                List<Todo> todos = new ArrayList<>();
                for (CalElement el : cal.get(ViewType.TODOS)) {
                    if (el instanceof Todo) {
                        todos.add((Todo) el);
                    }
                }
                result = TodoFilter.filter(todos, config);
            }

            //  Writer
            OutputWriter writer = OutputWriterFactory.create(config);

            //  Output
            writer.write(result, config.getOutputStream());

        } catch (CliException e) {
            System.err.println("Erreur: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur interne: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
