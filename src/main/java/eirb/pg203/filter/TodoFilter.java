package eirb.pg203.filter;

import eirb.pg203.cli.StatusOption;
import eirb.pg203.model.Todo;
import java.util.List;

public class TodoFilter implements CalendarFilter<Todo> {
    
    private final StatusOption option;
    
    public TodoFilter(StatusOption option) {
        this.option = option;
    }
    
    @Override
    public List<Todo> filter(List<Todo> elements) {
        return switch (option) {
            case ALL -> elements;
            case COMPLETED -> filterByStatus(elements, "COMPLETED");
            case IN_PROCESS -> filterByStatus(elements, "IN-PROCESS");
            case NEEDS_ACTION -> filterByStatus(elements, "NEEDS-ACTION");
            case INCOMPLETE -> filterIncomplete(elements);
        };
    }
    
    private List<Todo> filterByStatus(List<Todo> todos, String status) {
        return todos.stream()
            .filter(todo -> {
                String todoStatus = todo.getStatus();
                return todoStatus != null && todoStatus.equalsIgnoreCase(status);
            })
            .toList();
    }
    
    private List<Todo> filterIncomplete(List<Todo> todos) {
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