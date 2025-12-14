package eirb.pg203.output;

import eirb.pg203.*;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class IcsWriter implements OutputWriter {

    private static final DateTimeFormatter FMT = DateTimeFormatter.BASIC_ISO_DATE;

    @Override
    public void write(List<? extends CalElement> elements, OutputStream out) {
        PrintWriter pw = new PrintWriter(out);

        pw.println("BEGIN:VCALENDAR");
        pw.println("VERSION:2.0");

        for (CalElement el : elements) {
            if (el instanceof Event e) {
                writeEvent(e, pw);
            } else if (el instanceof Todo t) {
                writeTodo(t, pw);
            }
        }

        pw.println("END:VCALENDAR");
        pw.flush();
    }

    private void writeEvent(Event e, PrintWriter pw) {
        pw.println("BEGIN:VEVENT");

        if (e.getUid() != null)
            pw.println("UID:" + e.getUid());
        if (e.getSummary() != null)
            pw.println("SUMMARY:" + e.getSummary());
        if (e.getLocation() != null)
            pw.println("LOCATION:" + e.getLocation());
        if (e.getDescription() != null)
            pw.println("DESCRIPTION:" + e.getDescription());
        if (e.getStart() != null)
            pw.println("DTSTART:" + e.getStart().format(FMT));
        if (e.getEnd() != null)
            pw.println("DTEND:" + e.getEnd().format(FMT));

        pw.println("END:VEVENT");
    }

    private void writeTodo(Todo t, PrintWriter pw) {
        pw.println("BEGIN:VTODO");

        if (t.getUid() != null)
            pw.println("UID:" + t.getUid());
        if (t.getSummary() != null)
            pw.println("SUMMARY:" + t.getSummary());
        if (t.getStatus() != null)
            pw.println("STATUS:" + t.getStatus());
        if (t.getDue() != null)
            pw.println("DUE:" + t.getDue().format(FMT));
        if (t.getCompleted() != null)
            pw.println("COMPLETED:" + t.getCompleted().format(FMT));

        pw.println("END:VTODO");
    }
}
