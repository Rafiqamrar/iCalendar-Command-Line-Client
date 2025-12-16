package eirb.pg203.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Représente une tâche (VTODO) d'un fichier ICS.
 * Hérite de CalElement et contient les propriétés spécifiques aux TODOs
 */
public class Todo extends CalElement
{
  final private String UID;
  final private String SUMMARY;
  final private String LOCATION;
  final private String STATUS;
  final private Integer PERCENT_COMPLETE;
  final private LocalDateTime COMPLETED;
  final private LocalDate DUE;
  final private LocalDate DTSTART;
  final private String CLASS;
  final private Integer PRIORITY;
  final private LocalDateTime LAST_MODIFIED;
  final private LocalDateTime DTSTAMP;
  final private Integer SEQUENCE;
  final private String ORGANIZER_name;
  final private String ORGANIZER_mail;

  /**
   * Constructeur pour créer une tâche (TODO).
   * @param uid Identifiant unique
   * @param summary Titre de la tâche
   * @param location Lieu associé
   * @param status Statut ("COMPLETED", "INPROCESS", "NEEDS-ACTION")
   * @param percent_complete Pourcentage d'avancement (0-100)
   * @param completed Date de complétion
   * @param due Date limite
   * @param dtstart Date de début
   * @param todo_class Classification
   * @param priority Niveau de priorité
   * @param last_modified Date de dernière modification
   * @param dtstamp Date de création de l'instance
   * @param sequence Numéro de séquence
   * @param organizer_name Nom de l'organisateur
   * @param organizer_mail Email de l'organisateur
   */
  public Todo (String uid, String summary, String location, String status,
               Integer percent_complete, LocalDateTime completed,
               LocalDate due, LocalDate dtstart, String todo_class,
               Integer priority, LocalDateTime last_modified,
               LocalDateTime dtstamp, Integer sequence, String organizer_name,
               String organizer_mail)
  {
    if (uid == null || uid.isEmpty ())
      {
        throw new IllegalArgumentException ("UID ne peut pas être null");
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

  /**
   * Retourne le type de vue associé (TODOS).
   */
  public ViewType
  viewType ()
  {
    return ViewType.TODOS;
  }

  // Getters
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

  public Integer
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

  public Integer
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

  public Integer
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

  /**
   * Représentation de la tâche pour l'affichage console.
   */
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
