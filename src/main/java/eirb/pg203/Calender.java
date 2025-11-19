package eirb.pg203;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Calender {
  private final Map<String,String> fields;
  private final List<CalElement> list;

  Calender(Map<String,String> f,List<CalElement> els){
    this.fields = (f == null) ? new HashMap<>() : new HashMap<>(f);
    this.list = (els == null) ? new ArrayList<>() : new ArrayList<>(els);
  }

  /*
  public void display(String type){
    System.out.println("\n--- begin ["+ fields.get("BEGIN") + " : "+ type +" ] display -----");
    
    for (Map.Entry<String, String> entry : this.fields.entrySet()) {
      if("BEGIN".equals(entry.getKey())) continue;
      System.out.println(entry.getKey() + ":" + entry.getValue());
    }

    for( CalElement el : list ){
      if(el.type().equals(type))
        el.display();
    }
    System.out.println("\n--- end ["+ fields.get("BEGIN") + " : "+ type +" ] display -----");
  }*/



  public List<CalElement> get(ViewType type) {
    List<CalElement> res = new ArrayList<>();
    for (CalElement el : list) {
        if (el.viewType() == type) {
            res.add(el);
        }
    }
    return res;
}

}
