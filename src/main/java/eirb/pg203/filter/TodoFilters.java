package eirb.pg203.filter;

import eirb.pg203.cli.CliConfig;
import eirb.pg203.cli.TodoFilterType;
import eirb.pg203.model.Todo;
import java.util.List;

public class TodoFilters
{
  public static List<Todo>
  filter (List<Todo> todos, CliConfig config)
  {
    return filterTodos (todos, config.getTodoFilter ());
  }

  public static List<Todo>
  filterTodos (List<Todo> todos, TodoFilterType option)
  {
    switch (option)
      {
      case ALL:
        return todos;
      case COMPLETED:
        return filterByStatus (todos, "COMPLETED");
      case INPROCESS:
        return filterByStatus (todos, "INPROCESS");
      case NEEDSACTION:
        return filterByStatus (todos, "NEEDSACTION");
      case INCOMPLETE:
        return filterIncomplete (todos);
      default:
        return todos;
      }
  }

  private static List<Todo>
  filterByStatus (List<Todo> todos, String status)
  {
    return todos.stream ()
        .filter (todo -> {
          String todoStatus = todo.getStatus ();
          return todoStatus != null && todoStatus.equalsIgnoreCase (status);
        })
        .toList ();
  }

  private static List<Todo>
  filterIncomplete (List<Todo> todos)
  {
    return todos.stream ()
        .filter (todo -> {
          String status = todo.getStatus ();
          return status == null || status.isEmpty ()
              || !"COMPLETED".equalsIgnoreCase (status);
        })
        .toList ();
  }
}
