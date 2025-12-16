


package eirb.pg203.outputTest;

import java.nio.file.Paths;
import eirb.pg203.cli.CliConfig;
import eirb.pg203.output.HtmlWriter;
import eirb.pg203.output.IcsWriter;
import eirb.pg203.output.OutputFormat;
import eirb.pg203.output.OutputWriter;
import eirb.pg203.output.OutputWriterFactory;
import eirb.pg203.output.TextWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class OutputWriterFactoryTest
{
    @Test
    public void createReturnsTextWriterForTextFormat(){
        CliConfig config = new CliConfig(
                Paths.get(""),
                null,
                null,
                null,
                null,
                null,
                OutputFormat.TEXT,
                Paths.get("")
        );
        

        OutputWriter writer = OutputWriterFactory.create (config);
        assertTrue (writer instanceof TextWriter);
    }

    @Test
    public void createReturnsHtmlWriterForHtmlFormat(){
        CliConfig config = new CliConfig(
                Paths.get(""),
                null,
                null,
                null,           
                null,
                null,
                OutputFormat.HTML,
                Paths.get("")
        );
        OutputWriter writer = OutputWriterFactory.create (config);
        assertTrue (writer instanceof HtmlWriter);
    
    }


    @Test
    public void createReturnsIcsWriterForIcsFormat(){
        CliConfig config = new CliConfig(

                Paths.get(""),
                null,
                null,
                null,
                null,
                null,
                OutputFormat.ICS,
                Paths.get("")
        );
        OutputWriter writer = OutputWriterFactory.create (config);  
        assertTrue (writer instanceof IcsWriter);
    }
}