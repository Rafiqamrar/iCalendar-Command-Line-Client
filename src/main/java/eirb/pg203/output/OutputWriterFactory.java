package eirb.pg203.output;

import eirb.pg203.cli.CliConfig;

public class OutputWriterFactory {

  public static OutputWriter create(CliConfig config) {
    return switch (config.getOutputFormat()) {
            case TEXT -> new TextWriter();
            case ICS -> new IcsWriter();
            case HTML -> new HtmlWriter();
        };
    }
}
