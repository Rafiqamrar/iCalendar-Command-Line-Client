package eirb.pg203.cli;

/**
 * Exception personnalisée pour les erreurs de ligne de commande.
 * Utilisée par CliParser pour signaler les arguments invalides,
 * options manquantes, ou validations échouées.
 */
public class CliException extends RuntimeException
{
  public CliException (String message) { super (message); }
}
