package eirb.pg203;
import java.util.Map;
import java.util.HashMap;

public class Event implements CalElement{
  private final Map<String,String> fields;
  private final String _type = "EVENT";

  Event(Map<String,String> m){
    this.fields = (m == null) ? new HashMap<>() : new HashMap<>(m);
  }

  public String type(){
    return _type;
  }
  public void display() {
    System.out.println("\n--- begin [ "+ fields.get("BEGIN") + " ] display -----");
    for (Map.Entry<String, String> entry : this.fields.entrySet()) {
      System.out.println(entry.getKey() + ":" + entry.getValue());
    }
    System.out.println("--- end [ "+ fields.get("BEGIN") + " ] display -----");
  }
}
