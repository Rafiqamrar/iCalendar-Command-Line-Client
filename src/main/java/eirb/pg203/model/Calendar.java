package eirb.pg203.model;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Calendar {
  private final Map<String,String> fields;
  private final List<CalElement> list;

  public Calendar(Map<String,String> f,List<CalElement> els){
    this.fields = (f == null) ? new HashMap<>() : new HashMap<>(f);
    this.list = (els == null) ? new ArrayList<>() : new ArrayList<>(els);
  }

  public List<CalElement> get(ViewType type) {
    List<CalElement> res = new ArrayList<>();
    for (CalElement el : list) {
        if ( type == ViewType.ALL || el.viewType() == type) {
            res.add(el);
        }
    }
    return res;
  }
}
