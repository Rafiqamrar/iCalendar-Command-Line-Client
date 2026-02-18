# CliCal — iCalendar Command-Line Client

A lightweight Java CLI application for parsing and displaying **iCalendar (ICS)** files. Filter events and todos by date or status, and export to multiple formats.

## Features

- **ICS Parsing** — Parse `VEVENT` and `VTODO` components from iCalendar files
- **Flexible Filtering**
  - Events: `-today`, `-tomorrow`, `-week`, `-from DATE`, `-to DATE`
  - Todos: `-incomplete`, `-all`, `-completed`, `-inprocess`, `-needsaction`
- **Multiple Output Formats** — Text, ICS, HTML
- **File or Stdout** — Export to file with `-o FILE` or print to console

## Architecture

```
CLI → Parser → Calendar Model → Filters → OutputWriter
```

- **Polymorphism**: `OutputWriter` interface with `TextWriter`, `IcsWriter`, `HtmlWriter`
- **Inheritance**: `CalElement` ← `Event`, `Todo`
- **Delegation**: Modular components (parser, filters, writers)

## Build

```bash
./gradlew build
```

## Usage

```bash
# View today's events
./clical path/to/calendar.ics events -today

# View all incomplete todos
./clical path/to/todos.ics todos -incomplete

# Export events to HTML
./clical path/to/calendar.ics events -week -html -o schedule.html

# Export date range to ICS
./clical path/to/calendar.ics events -from 20251111 -to 20251130 -ics -o output.ics
```

## Run with Gradle

```bash
./gradlew run --args="path/to/file.ics events -today"
```

## Testing

```bash
# Run tests
./gradlew test

# Generate coverage report
./gradlew jacocoTestReport
# Report: build/reports/jacoco/test/html/index.html
```

## Project Structure

```
src/
├── main/java/          # Application source code
│   ├── cli/            # Command-line parsing
│   ├── parser/         # ICS file parsing
│   ├── model/          # Calendar, Event, Todo
│   ├── filters/        # Event and Todo filters
│   └── output/         # Writers (Text, ICS, HTML)
└── test/java/          # Unit tests
```

## Technologies

- Java 17+
- Gradle
- JUnit 5
- JaCoCo

