package eirb.pg203.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Event extends CalElement
{
  final private String UID;
  final private String SUMMARY;
  final private String LOCATION;
  final private LocalDateTime LAST_MODIFIED;
  final private LocalDateTime DTSTAMP;
  final private LocalDateTime DTSTART;
  final private LocalDateTime DTEND;
  final private LocalDateTime CREATED;
  final private String DESCRIPTION;
  final private Integer SEQUENCE;

  public Event (String uid, String summary, String location,
                LocalDateTime last_modified, LocalDateTime dtstamp,
                LocalDateTime dtstart, LocalDateTime dtend,
                LocalDateTime created, String description, Integer sequence)
  {
    if (uid == null || uid.isEmpty ())
      {
        throw new IllegalArgumentException ("UID was not provided");
      }
    if (dtstart == null)
      {
        throw new IllegalArgumentException ("DTSTART ne peut pas être null");
      }
    this.UID = uid;
    this.SUMMARY = summary;
    this.LOCATION = location;
    this.LAST_MODIFIED = last_modified;
    this.DTSTAMP = dtstamp;
    this.DTSTART = dtstart;
    this.DTEND = dtend;
    this.CREATED = created;
    this.DESCRIPTION = description;
    this.SEQUENCE = sequence;
  }

  @Override
  public ViewType
  viewType ()
  {
    return ViewType.EVENTS;
  }

  public String
  getUid ()
  {
    return this.UID;
  }

  public String
  getSummary ()
  {
    return this.SUMMARY;
  }

  public String
  getLocation ()
  {
    return this.LOCATION;
  }

  public LocalDateTime
  getLastModified ()
  {
    return this.LAST_MODIFIED;
  }

  public LocalDateTime
  getDtStamp ()
  {
    return this.DTSTAMP;
  }

  public LocalDateTime
  getStart ()
  {
    return this.DTSTART;
  }

  public LocalDateTime
  getEnd ()
  {
    return this.DTEND;
  }

  public LocalDateTime
  getCreated ()
  {
    return this.CREATED;
  }

  public String
  getDescription ()
  {
    return this.DESCRIPTION;
  }

  public Integer
  getSequence ()
  {
    return this.SEQUENCE;
  }

  @Override
  public String
  toString ()
  {
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
