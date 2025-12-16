package eirb.pg203.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calendar
{
  private final Map<String, String> fields;
  private final List<CalElement> list;

  public Calendar (Map<String, String> f, List<CalElement> els)
  {
    this.fields = (f == null) ? new HashMap<> () : new HashMap<> (f);
    this.list = (els == null) ? new ArrayList<> () : new ArrayList<> (els);
  }
  public List<CalElement>
  getAll(){
    return list;
  }
  public List<Event>
  getEvents() throws Exception
  {
    List<Event> res = new ArrayList<> ();
    for (CalElement el : list)
      {
        if (el.viewType () == ViewType.EVENTS)
          {
            if( ! (el instanceof Event)) {
              throw new Exception("CalElment with type : EVENT is not instance of Event");
            }
            res.add((Event)el);
          }
      }
    return res;
  }
  public List<Todo>
  getTodos() throws Exception
  {
    List<Todo> res = new ArrayList<> ();
    for (CalElement el : list)
      {
        if (el.viewType () == ViewType.TODOS)
          {
            if( ! (el instanceof Todo)) {
              throw new Exception("CalElment with type : TODO is not instance of Todo");
            }
            res.add((Todo)el);
          }
      }
    return res;
  }
}
