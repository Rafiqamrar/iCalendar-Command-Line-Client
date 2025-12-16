package eirb.pg203.cli;

/**
 * Énumération des types de filtres applicables aux TODOs.
 * Utilisée par le parseur CLI pour savoir quel type de filtrage appliquer aux tâches.
 * Correspond aux options CLI: -incomplete, -all, -completed, -inprocess, -needsaction
 */
public enum TodoFilterType
{
  INCOMPLETE,   // Tâches non complétées (par défaut)
  ALL,          // Toutes les tâches
  COMPLETED,    // Tâches marquées comme complétées
  INPROCESS,    // Tâches en cours de traitement
  NEEDSACTION   // Tâches nécessitant une action
}
