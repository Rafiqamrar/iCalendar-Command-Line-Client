package eirb.pg203.output;

import eirb.pg203.model.CalElement;
import java.io.OutputStream;
import java.util.List;

public interface OutputWriter {
    void write(List<? extends CalElement> elements, OutputStream out);
}