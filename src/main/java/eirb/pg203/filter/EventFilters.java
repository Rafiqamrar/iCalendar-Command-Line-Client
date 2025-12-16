package eirb.pg203.filter;

import eirb.pg203.cli.CliConfig;
import eirb.pg203.cli.EventFilterType;
import eirb.pg203.model.Event;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe  pour filtrer les listes d'événements.
 */
public class EventFilters
{
  /**
   * Filtre une liste d'événements en utilisant la configuration CLI.
   * Méthode principale appelée depuis le flux principal du programme.
   * 
   * @param events Liste complète des événements à filtrer
   * @param config Configuration CLI contenant le type de filtre et les dates
   * @return Liste filtrée des événements
   */
  public static List<Event>
  filter (List<Event> events, CliConfig config)
  {
    return filterEvents (events, config.getEventFilter (), config.getFrom (),
                         config.getTo ());
  }

  /**
   * Filtre une liste d'événements selon un type de filtre et des dates spécifiques.
   * 
   * @param events Liste complète des événements
   * @param option Type de filtre à appliquer
   * @param fromDate Date de début (pour RANGE)
   * @param toDate Date de fin (pour RANGE)
   * @return Liste filtrée des événements
   */
  public static List<Event>
  filterEvents (List<Event> events, EventFilterType option, LocalDate fromDate,
                LocalDate toDate)
  {
    switch (option)
      {
      case TODAY:
        return filterByDate (events, LocalDate.now ());
      case TOMORROW:
        return filterByDate (events, LocalDate.now ().plusDays (1));
      case WEEK:
        return filterByWeek (events, LocalDate.now ());
      case RANGE:
        return filterByRange (events, fromDate, toDate);
      default:
        return events; // Par défaut, retourne tout
      }
  }

  /**
   * Filtre les événements ayant lieu à une date spécifique.
   * 
   * @param events Liste complète des événements
   * @param targetDate Date cible pour le filtrage
   * @return Événements dont la date de début correspond à targetDate
   */
  private static List<Event>
  filterByDate (List<Event> events, LocalDate targetDate)
  {
    return events.stream ()
        .filter (event -> {
          LocalDate eventDate = getEventDate (event);
          return eventDate != null && eventDate.equals (targetDate);
        })
        .toList ();
  }

  /**
   * Filtre les événements de la semaine courante.
   * 
   * @param events Liste complète des événements
   * @param today Date de référence
   * @return Événements dont la date est dans la semaine contenant today
   */
  private static List<Event>
  filterByWeek (List<Event> events, LocalDate today)
  {
    LocalDate startOfWeek = today.with (DayOfWeek.MONDAY);
    LocalDate endOfWeek = today.with (DayOfWeek.SUNDAY);
    return filterByRange (events, startOfWeek, endOfWeek);
  }

  /**
   * Filtre les événements dans une plage de dates.
   * Inclut les événements dont la date est >= from et <= to.
   * 
   * @param events Liste complète des événements
   * @param from Date de début de la plage
   * @param to Date de fin de la plage
   * @return Événements dont la date est dans la plage [from, to]
   */
  private static List<Event>
  filterByRange (List<Event> events, LocalDate from, LocalDate to)
  {
    return events.stream ()
        .filter (event -> {
          LocalDate eventDate = getEventDate (event);
          if (eventDate == null)
            {
              return false; // Ignorer les événements sans date
            }
          // Vérifie que eventDate est dans l'intervalle [from, to]
          return !eventDate.isBefore (from) && !eventDate.isAfter (to);
        })
        .toList ();
  }

  /**
   * Extrait la date d'un événement à partir de sa date de début.
   * Retourne null si l'événement n'a pas de date de début.
   * 
   * @param event Événement
   * @return Date locale ou null
   */
  private static LocalDate
  getEventDate (Event event)
  {
    if (event.getStart () == null)
      {
        return null;
      }
    return event.getStart ().toLocalDate ();
  }
}
