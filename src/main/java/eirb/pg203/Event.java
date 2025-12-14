package eirb.pg203;

import java.time.LocalDate;
import java.util.Map;

public class Event extends CalElement {

    final private String UID;
    final private String SUMMARY;
    final private String LOCATION;
    final private LocalDate LAST_MODIFIED;
    final private LocalDate DTSTAMP;
    final private LocalDate DTSTART;
    final private LocalDate DTEND;
    final private LocalDate CREATED;
    final private String DESCRIPTION;
    final private String SEQUENCE;

    public Event(String UID,
    String SUMMARY,
    String LOCATION,
    LocalDate LAST_MODIFIED,
    LocalDate DTSTAMP,
    LocalDate DTSTART,
    LocalDate DTEND,
    LocalDate CREATED,
    String DESCRIPTION,
    String SEQUENCE) {
        this.UID = UID;
        this.SUMMARY = SUMMARY;
        this.LOCATION = LOCATION;
        this.LAST_MODIFIED = LAST_MODIFIED;
        this.DTSTAMP = DTSTAMP;
        this.DTSTART = DTSTART;
        this.DTEND = DTEND;
        this.CREATED = CREATED;
        this.DESCRIPTION = DESCRIPTION;
        this.SEQUENCE = SEQUENCE;
    }
    @Override
    public ViewType viewType() {
        return ViewType.EVENTS;
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

    public LocalDate getLastModified() {
        return this.LAST_MODIFIED;
    }

    public LocalDate getDtStamp() {
        return this.DTSTAMP;
    }

    public LocalDate getStart() {
        return this.DTSTART;
    }

    public LocalDate getEnd() {
        return this.DTEND;
    }

    public LocalDate getCreated() {
        return this.CREATED;
    }
    public String getDescription() {
        return this.DESCRIPTION;
    }
    public String getSequence() {
        return this.SEQUENCE;
    }

    
    @Override
    public String toString() {
        return """
                [EVENT] =========
                Summary      : %s
                Start        : %s
                End          : %s
                Location     : %s
                UID          : %s
                Description  : %s
                Created      : %s
                LastModified : %s
                DTStamp      : %s
                =================
                """.formatted(
                getSummary(),
                getStart(),
                getEnd(),
                getLocation(),
                getUid(),
                getDescription(),
                getCreated(),
                getLastModified(),
                getDtStamp());
    }

}
