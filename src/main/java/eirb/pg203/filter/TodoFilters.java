package eirb.pg203.filter;

import eirb.pg203.cli.TodoFilterType;
import eirb.pg203.model.Todo;
import eirb.pg203.cli.CliConfig;
import java.util.List;

public class TodoFilters {
    public static List<Todo> filter(List<Todo> todos, CliConfig config) {
        return filterTodos(todos, config.getTodoFilter());
    }

    public static List<Todo> filterTodos(List<Todo> todos, TodoFilterType option) {
        return switch (option) {
            case ALL -> todos;
            case COMPLETED -> filterByStatus(todos, "COMPLETED");
            case INPROCESS -> filterByStatus(todos, "INPROCESS");
            case NEEDSACTION -> filterByStatus(todos, "NEEDSACTION");
            case INCOMPLETE -> filterIncomplete(todos);
        };
    }
    
    private static List<Todo> filterByStatus(List<Todo> todos, String status) {
        return todos.stream()
            .filter(todo -> {
                String todoStatus = todo.getStatus();
                return todoStatus != null && todoStatus.equalsIgnoreCase(status);
            })
            .toList();
    }
    
    private static List<Todo> filterIncomplete(List<Todo> todos) {
        return todos.stream()
            .filter(todo -> {
                String status = todo.getStatus();
                return status == null || 
                       status.isEmpty() || 
                       !"COMPLETED".equalsIgnoreCase(status);
            })
            .toList();
    }
}