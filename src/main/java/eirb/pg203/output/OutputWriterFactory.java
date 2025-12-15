package eirb.pg203.output;

import eirb.pg203.cli.CliConfig;

public class OutputWriterFactory
{
  public static OutputWriter
  create (CliConfig config)
  {
    switch (config.getOutputFormat ())
      {
      case TEXT:
        return new TextWriter ();
      case ICS:
        return new IcsWriter ();
      case HTML:
        return new HtmlWriter ();
      default:
        return new TextWriter ();
      }
  }
}
