package eirb.pg203.filter;

import eirb.pg203.Todo;
import eirb.pg203.CalElement;
import eirb.pg203.cli.CliConfig;
import eirb.pg203.cli.TodoFilterType;

import java.util.ArrayList;
import java.util.List;

public class TodoFilter {

    public static List<CalElement> filter(List<Todo> todos, CliConfig cfg) {

        List<CalElement> result = new ArrayList<>();

        for (Todo t : todos) {

            boolean keep = false;

            switch (cfg.getTodoFilter()) {
                case ALL:
                    keep = true;
                    break;
                case COMPLETED:
                    keep = t.getCompleted() != null;
                    break;
                case INCOMPLETE:
                    keep = t.getCompleted() == null;
                    break;
                case INPROCESS:
                    keep = "IN-PROCESS".equalsIgnoreCase(t.getStatus());
                    break;
                case NEEDSACTION:
                    keep = "NEEDS-ACTION".equalsIgnoreCase(t.getStatus());
                    break;
            }

            if (keep) {
                result.add(t);
            }
        }
        return result;
    }
}
