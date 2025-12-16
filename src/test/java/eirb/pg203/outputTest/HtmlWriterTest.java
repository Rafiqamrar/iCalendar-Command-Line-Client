package eirb.test.pg203.outputTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import eirb.pg203.output.HtmlWriter;
import eirb.pg203.model.Event;
import eirb.pg203.model.Todo;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class HtmlWriterTest {

    @Test
    void testWriteWithEvents() {
        // Arrange
        HtmlWriter writer = new HtmlWriter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        Event event = new Event("1", "Test Event", "Test Location",
                                LocalDateTime.now(), LocalDateTime.now(),
                                LocalDateTime.of(2023, 12, 25, 10, 0),
                                LocalDateTime.of(2023, 12, 25, 12, 0),
                                LocalDateTime.now(), "Test Description", 0);
        
        List<Event> events = new ArrayList<>();
        events.add(event);
        
        // Act
        writer.write(events, out);
        String result = out.toString();
        
        // Assert
        assertTrue(result.contains("<h2>Event</h2>"));
        assertTrue(result.contains("Test Event"));
        assertTrue(result.contains("Test Location"));
        assertTrue(result.contains("Test Description"));
        assertTrue(result.contains("2023-12-25T10:00"));
        assertTrue(result.contains("2023-12-25T12:00"));
        
    }

    @Test
    void testWriteWithTodos() {
        // Arrange
        HtmlWriter writer = new HtmlWriter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        Todo todo = new Todo(
            "todo-001",                                     // uid
            "Préparer présentation finale projet",          // summary
            "Bureau 2.3",                                   // location
            "NEEDS-ACTION",                                 // status
            25,                                             // percent_complete
            null,                                           // completed (pas encore)
            LocalDate.of(2023, 12, 15),                     // due (échéance)
            LocalDate.of(2023, 11, 20),                     // dtstart (début prévu)
            "PUBLIC",                                       // todo_class
            1,                                              // priority (1 = haute)
            LocalDateTime.of(2023, 11, 21, 14, 30),         // last_modified
            LocalDateTime.of(2023, 11, 20, 9, 0),           // dtstamp
            2,                                              // sequence
            "Jean Dupont",                                  // organizer_name
            "jean.dupont@entreprise.com"                    // organizer_mail
        );
        
        List<Todo> todos = new ArrayList<>();
        todos.add(todo);
        
        // Act
        writer.write(todos, out);
        String result = out.toString();
        
        // Assert
        assertTrue(result.contains("<h2>Todo</h2>"));
        assertTrue(result.contains("Préparer présentation finale projet"));
        assertTrue(result.contains("NEEDS-ACTION"));
        assertTrue(result.contains("2023-12-15"));
        assertTrue(result.contains("25"));
    }
}