package eirb.pg203.output;

import eirb.pg203.model.CalElement;
import java.io.OutputStream;
import java.util.List;

/**
 * Interface pour tous les writers de sortie.
 * Définit le contrat que doivent respecter tous les formats de sortie.
 */
public interface OutputWriter
{
  /**
   * Écrit une liste d'éléments de calendrier dans un flux de sortie.
   *
   * @param elements Liste des éléments à écrire (Events ou Todos)
   * @param out Flux de sortie
   */

  void write (List<? extends CalElement> elements, OutputStream out);
}