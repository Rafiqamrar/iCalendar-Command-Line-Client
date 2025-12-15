package eirb.pg203.cli;

import eirb.pg203.model.ViewType;
import eirb.pg203.output.OutputFormat;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CliParser {

    // Formatter pour les dates au format YYYYMMDD
    private static final DateTimeFormatter DATE_YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static CliConfig parse(String[] args) {

        if (args.length < 2) {
            throw new CliException("Usage: clical <file> <events|todos> [options]");
        }

        Path inputFile = Path.of(args[0]);

        ViewType viewType;
        if (args[1].equals("events")) {
            viewType = ViewType.EVENTS;
        } else if (args[1].equals("todos")) {
            viewType = ViewType.TODOS;
        } else {
            throw new CliException("events ou todos attendu");
        }

        EventFilterType eventFilter = EventFilterType.TODAY;
        TodoFilterType todoFilter = TodoFilterType.INCOMPLETE;

        LocalDate from = null;
        LocalDate to = null;

        OutputFormat format = OutputFormat.TEXT;
        Path outputFile = null;

        for (int i = 2; i < args.length; i++) {

            switch (args[i]) {

                // EVENTS
                case "-today":
                    eventFilter = EventFilterType.TODAY;
                    break;
                case "-tomorrow":
                    eventFilter = EventFilterType.TOMORROW;
                    break;
                case "-week":
                    eventFilter = EventFilterType.WEEK;
                    break;
                case "-from":
                    from = LocalDate.parse(args[++i], DATE_YYYYMMDD); // parse avec le formatter
                    eventFilter = EventFilterType.RANGE;
                    break;
                case "-to":
                    to = LocalDate.parse(args[++i], DATE_YYYYMMDD); // parse avec le formatter
                    break;

                // TODOS
                case "-all":
                    todoFilter = TodoFilterType.ALL;
                    break;
                case "-completed":
                    todoFilter = TodoFilterType.COMPLETED;
                    break;
                case "-inprocess":
                    todoFilter = TodoFilterType.INPROCESS;
                    break;
                case "-needsaction":
                    todoFilter = TodoFilterType.NEEDSACTION;
                    break;
                case "-incomplete":
                    todoFilter = TodoFilterType.INCOMPLETE;
                    break;

                // OUTPUT
                case "-text":
                    format = OutputFormat.TEXT;
                    break;
                case "-ics":
                    format = OutputFormat.ICS;
                    break;
                case "-html":
                    format = OutputFormat.HTML;
                    break;
                case "-o":
                    outputFile = Path.of(args[++i]);
                    break;

                default:
                    throw new CliException("Option inconnue: " + args[i]);
            }
        }

        // VALIDATION
        if (viewType == ViewType.TODOS && (from != null || to != null)) {
            throw new CliException("Les options -from/-to sont interdites pour les TODOS");
        }

        if (eventFilter == EventFilterType.RANGE && (from == null || to == null)) {
            throw new CliException("Les options -from et -to doivent être utilisées ensemble");
        }

        return new CliConfig(
                inputFile,
                viewType,
                eventFilter,
                todoFilter,
                from,
                to,
                format,
                outputFile
        );
    }
}
