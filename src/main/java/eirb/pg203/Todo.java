package eirb.pg203;

import java.util.Map;

public class Todo extends CalElement {

    public Todo(Map<String,String> m){
        super(m);
    }



    // --- Getters ---
    public String getSummary() {
        return get("SUMMARY");
    }

    public String getLocation() {
        return get("LOCATION");
    }

    public String getStatus() {
        return get("STATUS");
    }

    public String getPercent() {
        return get("PERCENT-COMPLETE");
    }

    public String getDue() {
        return get("DUE"); // parfois DUE;VALUE=DATE
    }
    @Override
    public ViewType viewType() {
         return ViewType.TODOS;
}

    @Override
    public String toString() {
        return """
        [TODO]
        Summary     : %s
        Status      : %s
        Percent     : %s%%
        Due         : %s
        Location    : %s
        UID         : %s
        """.formatted(
                getSummary(),
                getStatus(),
                getPercent(),
                getDue(),
                getLocation(),
                getUid()
        );
    }
}
