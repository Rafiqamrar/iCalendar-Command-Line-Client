package eirb.pg203.model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Représente un événement (VEVENT) d'un fichier ICS.
 * Hérite de CalElement et contient toutes les propriétés spécifiques aux événements
 */
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

  /**
   * Constructeur pour créer un événement.
   * @param uid Identifiant unique
   * @param summary Titre de l'événement
   * @param location Lieu de l'événement
   * @param last_modified Date de dernière modification
   * @param dtstamp Date de création de l'instance
   * @param dtstart Date et heure de début
   * @param dtend Date et heure de fin
   * @param created Date de création de l'événement
   * @param description Description de l'événement
   * @param sequence Numéro de séquence
   */
  public Event (String uid, String summary, String location,
                LocalDateTime last_modified, LocalDateTime dtstamp,
                LocalDateTime dtstart, LocalDateTime dtend,
                LocalDateTime created, String description, Integer sequence)
  {
    if (uid == null || uid.isEmpty ())
      {
        throw new IllegalArgumentException ("UID ne peut pas être null");
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

  /**
   * Retourne le type de vue associé (EVENTS).
   */
  @Override
  public ViewType
  viewType ()
  {
    return ViewType.EVENTS;
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

  /**
   * Représentation de l'événement pour l'affichage console.
   */
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
