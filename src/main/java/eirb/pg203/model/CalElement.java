package eirb.pg203.model;

import java.util.Map;

/**
 * Classe abstraite représentant un élément de calendrier.
 * Définit une méthode abstraite viewType() pour identifier le type d'élément.
 */
public abstract class CalElement
{
  /**
   * Retourne le type (EVENTS ou TODOS) de cet élément.
   */
  public abstract ViewType viewType ();
}
