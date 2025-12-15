package eirb.pg203;

import java.time.LocalDateTime;
import java.util.Map;

public class Event extends CalElement {
    final private String UID;
    final private String SUMMARY;
    final private String LOCATION;
    final private LocalDateTime LAST_MODIFIED;
    final private LocalDateTime DTSTAMP;
    final private LocalDateTime DTSTART;
    final private LocalDateTime DTEND;
    final private LocalDateTime CREATED;
    final private String DESCRIPTION;
    final private Number SEQUENCE;

    public Event(String UID,
    String SUMMARY,
    String LOCATION,
    LocalDateTime LAST_MODIFIED,
    LocalDateTime DTSTAMP,
    LocalDateTime DTSTART,
    LocalDateTime DTEND,
    LocalDateTime CREATED,
    String DESCRIPTION,
    Number SEQUENCE) {
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

    public LocalDateTime getLastModified() {
        return this.LAST_MODIFIED;
    }

    public LocalDateTime getDtStamp() {
        return this.DTSTAMP;
    }

    public LocalDateTime getStart() {
        return this.DTSTART;
    }

    public LocalDateTime getEnd() {
        return this.DTEND;
    }

    public LocalDateTime getCreated() {
        return this.CREATED;
    }

    public String getDescription() {
        return this.DESCRIPTION;
    }

    public Number getSequence() {
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
