package eirb.pg203;

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
        String option = (args.length >= 3) ? args[2] : null;
        String date = (args.length == 4) ? args[3] : null;

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
        List<CalElement> elements = calendar.get(viewType);

        // ---- FILTRAGE ----
        if (option != null) {

            if (viewType == ViewType.EVENTS) {

                List<Event> events = elements.stream().map(e -> (Event) e).toList();

                OptionsEvent ev = new OptionsEvent();

                List<Event> filtered = ev.filter(option, events, date);

                elements = List.copyOf(filtered);

            }
        }

        for (CalElement el : elements) {
            System.out.println(el);
            System.out.println("---------------------------");
        }
    }
}
