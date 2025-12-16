package eirb.pg203.filter;

import eirb.pg203.cli.CliConfig;
import eirb.pg203.cli.TodoFilterType;
import eirb.pg203.model.Todo;
import java.util.List;

/**
 * Classe pour filtrer les listes de TODOs.
 */
public class TodoFilters
{
  /**
   * Filtre une liste de TODOs en utilisant la configuration CLI.
   * Méthode principale appelée depuis le flux principal du programme.
   * 
   * @param todos Liste complète des TODOs à filtrer
   * @param config Configuration CLI contenant le type de filtre à appliquer
   * @return Liste filtrée des TODOs
   */
  public static List<Todo>
  filter (List<Todo> todos, CliConfig config)
  {
    return filterTodos (todos, config.getTodoFilter ());
  }

  /**
   * Filtre une liste de TODOs selon un type de filtre spécifique.
   * 
   * @param todos Liste complète des TODOs
   * @param option Type de filtre à appliquer
   * @return Liste filtrée des TODOs
   */
  private static List<Todo>
  filterTodos (List<Todo> todos, TodoFilterType option)
  {
    switch (option)
      {
      case ALL:
        return todos; // Aucun filtrage, retourne tous les TODOs
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

  /**
   * Filtre les TODOs ayant un statut spécifique.
   * 
   * @param todos Liste complète des TODOs
   * @param status Statut à rechercher
   * @return TODOs ayant ce statut
   */
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

  /**
   * Filtre les TODOs incomplets.
   * Considère comme incomplets :
   * - TODOs sans statut (null)
   * - TODOs avec statut vide
   * - TODOs avec statut différent de "COMPLETED"
   * 
   * @param todos Liste complète des TODOs
   * @return TODOs considérés comme incomplets
   */
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
