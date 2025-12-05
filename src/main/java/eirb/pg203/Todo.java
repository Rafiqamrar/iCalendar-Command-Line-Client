package eirb.pg203;

import java.util.Map;
import java.time.LocalDate;

public class Todo extends CalElement {

    // public Todo(Map<String,String> m){
    //     super(m);
    // }

    final private String UID ; 
    final private String SUMMARY ;
    final private String LOCATION ;
    final private String STATUS ;
    final private String PERCENT_COMPLETE ;
    final private Number DUE ;
    final private String CLASS ; 
    final private Number PRIORITY ;
    final private LocalDate LAST_MODIFIED ;
    final private LocalDate DTSTAMP ;
    final private Number SEQUENCE ;
    final private String ORGANIZER_name  ;
    final private String ORGANIZER_mail  ;
    final private ViewType type;

    Todo(String uid, String summary, String location, String status, String percent_complete, Number due, String class_, Number priority, LocalDate last_modified, LocalDate dtstamp, Number sequence, String organizer_name, String organizer_mail, ViewType type) {
        this.UID = uid; 
        this.SUMMARY = summary;
        this.LOCATION = location;
        this.STATUS = status;
        this.PERCENT_COMPLETE = percent_complete;
        this.DUE = due;
        this.CLASS = class_;
        this.PRIORITY = priority;
        this.LAST_MODIFIED = last_modified;
        this.DTSTAMP = dtstamp;
        this.SEQUENCE = sequence;
        this.ORGANIZER_name = organizer_name;
        this.ORGANIZER_mail = organizer_mail;
        this.type = type;
    }

    public ViewType viewType(){
        return this.type;
    }






    // @Override
    // public String toString() {
    //     // return """
    //     // [TODO]
    //     // Summary     : %s
    //     // Status      : %s
    //     // Percent     : %s%%
    //     // Due         : %s
    //     // Location    : %s
    //     // UID         : %s
    //     // """.formatted(
    //     //         getSummary(),
    //     //         getStatus(),
    //     //         getPercent(),
    //     //         getDue(),
    //     //         getLocation(),
    //     //         getUid()
    //     // );


    //     /System.out.println("i'm todo") ; 
    // }
}
