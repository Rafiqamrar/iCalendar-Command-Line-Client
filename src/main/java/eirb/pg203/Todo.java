package eirb.pg203;

import java.util.Map;
import java.time.LocalDate;

public class Todo extends CalElement {
    final private String UID;
    final private String SUMMARY;
    final private String LOCATION;
    final private String STATUS;
    final private String PERCENT_COMPLETE;
    final private LocalDate COMPLETED;
    final private LocalDate DUE;
    final private String CLASS;
    final private Number PRIORITY;
    final private LocalDate LAST_MODIFIED;
    final private LocalDate DTSTAMP;
    final private Number SEQUENCE;
    final private String ORGANIZER_name;
    final private String ORGANIZER_mail;

    public Todo(String uid, String summary, String location, String status, String percent_complete, LocalDate completed ,LocalDate due, String class_, Number priority, LocalDate last_modified, LocalDate dtstamp, Number sequence, String organizer_name, String organizer_mail) {
        this.UID = uid; 
        this.SUMMARY = summary;
        this.LOCATION = location;
        this.STATUS = status;
        this.PERCENT_COMPLETE = percent_complete;
        this.COMPLETED = completed;
        this.DUE = due;
        this.CLASS = class_;
        this.PRIORITY = priority;
        this.LAST_MODIFIED = last_modified;
        this.DTSTAMP = dtstamp;
        this.SEQUENCE = sequence;
        this.ORGANIZER_name = organizer_name;
        this.ORGANIZER_mail = organizer_mail;
    }

    public ViewType viewType(){
        return ViewType.TODOS;
    }


    
    public String getUid() {
        return this.UID;
    }

    public String getSummary() {
        return this.SUMMARY;
    }

    public String getLocation() {
        return this.LOCATION;
    }

    public String getStatus() {
        return this.STATUS;
    }

    public String getPercentComplete() {
        return this.PERCENT_COMPLETE;
    }
    public LocalDate getCompleted() {
        return this.COMPLETED;
    }

    public LocalDate getDue() {
        return this.DUE;
    }

    public String getTClass() {
        return this.CLASS;
    }

    public Number getPriority() {
        return this.PRIORITY;
    }

    public LocalDate getLastModified() {
        return this.LAST_MODIFIED;
    }

    public LocalDate getDtStamp() {
        return this.DTSTAMP;
    }

    public Number getSequence() {
        return this.SEQUENCE;
    }

    public String getOrganizerName() {
        return this.ORGANIZER_name;
    }
    public String getOrganizerMail() {
        return this.ORGANIZER_mail;
    }

    
    @Override
    public String toString() {
        return """
                [TODO] ===========
                Summary       : %s
                Status        : %s
                Percent       : %s
                Due           : %s
                Location      : %s
                UID           : %s
                Class         : %s
                Priority      : %s
                LastModified  : %s
                DTStamp       : %s
                Sequence      : %s
                OrganizerName : %s
                OrganizerMail : %s
                """.formatted(
                getSummary(),
                getStatus(),
                getPercentComplete(),
                getDue(),
                getLocation(),
                getUid(),
                getTClass(),
                getPriority(),
                getLastModified(),
                getDtStamp(),
                getSequence(),
                getOrganizerName(),
                getOrganizerMail());
    }
}
