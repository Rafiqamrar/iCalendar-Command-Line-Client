package eirb.pg203.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class EventTest {

  @Test
  public void testEventCreation() {
    LocalDateTime now = LocalDateTime.now();
    Event event = new Event("test-uid-123", "Test Event", "ENSEIRB", now, now, now,
        now.plusHours(2), now, "Description test", 1);

    assertEquals("test-uid-123", event.getUid());
    assertEquals("Test Event", event.getSummary());
    assertEquals("ENSEIRB", event.getLocation());
  }

  @Test
  public void testEventWithNullUidThrowsException() {
    LocalDateTime now = LocalDateTime.now();

    assertThrows(IllegalArgumentException.class,
        () -> { new Event(null, "Summary", null, now, now, now, now, now, null, 1); });
  }

  @Test
  public void testViewType() {
    LocalDateTime now = LocalDateTime.now();
    Event event = new Event("uid", "summary", null, now, now, now, now, now, null, 1);

    assertEquals(ViewType.EVENTS, event.viewType());
  }
}
