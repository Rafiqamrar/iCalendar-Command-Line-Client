package eirb.pg203;

import java.util.Map;

public class Event extends CalElement {

    public Event(Map<String,String> m){
        super(m);
    }


    // --- Getters ---
    public String getStart() {
        return get("DTSTART");
    }

    public String getEnd() {
        return get("DTEND");
    }

    public String getSummary() {
        return get("SUMMARY");
    }

    public String getLocation() {
        return get("LOCATION");
    }

    public String getDescription() {
        return get("DESCRIPTION");
    }
    @Override
    public ViewType viewType() {
        return ViewType.EVENTS;
    }

    @Override
    public String toString() {
        return """
        [EVENT]
        Summary     : %s
        Start       : %s
        End         : %s
        Location    : %s
        UID         : %s
        Description : %s
        """.formatted(
                getSummary(),
                getStart(),
                getEnd(),
                getLocation(),
                getUid(),
                getDescription()
        );
    }
}
