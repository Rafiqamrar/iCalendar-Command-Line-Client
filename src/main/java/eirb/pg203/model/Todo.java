package eirb.pg203.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class Todo extends CalElement
{
  final private String UID;
  final private String SUMMARY;
  final private String LOCATION;
  final private String STATUS;
  final private Number PERCENT_COMPLETE;
  final private LocalDateTime COMPLETED;
  final private LocalDate DUE;
  final private LocalDate DTSTART;
  final private String CLASS;
  final private Number PRIORITY;
  final private LocalDateTime LAST_MODIFIED;
  final private LocalDateTime DTSTAMP;
  final private Number SEQUENCE;
  final private String ORGANIZER_name;
  final private String ORGANIZER_mail;

  public Todo (String uid, String summary, String location, String status,
               Number percent_complete, LocalDateTime completed, LocalDate due,
               LocalDate dtstart, String todo_class, Number priority,
               LocalDateTime last_modified, LocalDateTime dtstamp,
               Number sequence, String organizer_name, String organizer_mail)
  {
    if (uid == null || uid.isEmpty ())
      {
        throw new IllegalArgumentException ("UID was not provided");
      }
    this.UID = uid;
    this.SUMMARY = summary;
    this.LOCATION = location;
    this.STATUS = status;
    this.PERCENT_COMPLETE = percent_complete;
    this.COMPLETED = completed;
    this.DUE = due;
    this.DTSTART = dtstart;
    this.CLASS = todo_class;
    this.PRIORITY = priority;
    this.LAST_MODIFIED = last_modified;
    this.DTSTAMP = dtstamp;
    this.SEQUENCE = sequence;
    this.ORGANIZER_name = organizer_name;
    this.ORGANIZER_mail = organizer_mail;
  }

  public ViewType
  viewType ()
  {
    return ViewType.TODOS;
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

  public String
  getStatus ()
  {
    return this.STATUS;
  }

  public Number
  getPercentComplete ()
  {
    return this.PERCENT_COMPLETE;
  }
  public LocalDateTime
  getCompleted ()
  {
    return this.COMPLETED;
  }

  public LocalDate
  getDue ()
  {
    return this.DUE;
  }
  public LocalDate
  getDtstart ()
  {
    return this.DTSTART;
  }

  public String
  getTClass ()
  {
    return this.CLASS;
  }

  public Number
  getPriority ()
  {
    return this.PRIORITY;
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

  public Number
  getSequence ()
  {
    return this.SEQUENCE;
  }

  public String
  getOrganizerName ()
  {
    return this.ORGANIZER_name;
  }
  public String
  getOrganizerMail ()
  {
    return this.ORGANIZER_mail;
  }

  @Override
  public String
  toString ()
  {
        return """
                [TODO] ===========
                Summary       : %s
                Status        : %s
                Percent       : %s
                Due           : %s
                Dtstart       : %s
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
                getDtstart(),
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
