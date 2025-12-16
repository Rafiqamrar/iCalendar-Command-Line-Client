package eirb.pg203.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Représente un calendrier contenant une liste d'éléments (Event/Todo).
 */
public class Calendar
{
  private final Map<String, String> fields;
  private final List<CalElement> list;

  /**
   * Constructeur.
   * @param f Données du calendrier sous forme de Map (clé/valeur)
   * @param els Liste des éléments du calendrier
   */
  public Calendar (Map<String, String> f, List<CalElement> els)
  {
    this.fields = (f == null) ? new HashMap<> () : new HashMap<> (f);
    this.list = (els == null) ? new ArrayList<> () : new ArrayList<> (els);
  }

  /**
   * Retourne une liste filtrée des éléments selon le type demandé.
   * @param type Type d'éléments à récupérer (EVENTS, TODOS, ou ALL pour tous)
   * @return Liste des éléments correspondants
   */
  public List<CalElement>
  get (ViewType type)
  {
    List<CalElement> res = new ArrayList<> ();
    for (CalElement el : list)
      {
        if (type == ViewType.ALL || el.viewType () == type)
          {
            res.add (el);
          }
      }
    return res;
  }
}
