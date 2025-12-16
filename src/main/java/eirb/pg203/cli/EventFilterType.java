package eirb.pg203.cli;

/**
 * Énumération des types de filtres applicables aux événements.
 * Utilisée par le parseur CLI pour savoir quel type de filtrage appliquer aux
 * événements. Correspond aux options CLI: -today, -tomorrow, -week, -from/-to
 * (RANGE)
 */
public enum EventFilterType
{
  TODAY,    // Événements du jour courant
  TOMORROW, // Événements du lendemain
  WEEK,     // Événements de la semaine courante
  RANGE     // Événements dans une plage de dates spécifiée par -from/-to
}
