# RestControllerAdvice

## Lernziele

- [ ] Verstehen, was ein RestControllerAdvice ist und wie es in Spring eingesetzt wird.
- [ ] Unterschied zwischen globaler und Controller-spezifischer Verwendung von RestControllerAdvice begreifen.
- [ ] Anwenden der Annotation @RestControllerAdvice zur globalen Fehlerbehandlung.
- [ ] Erstellen einer ErrorMessage-Entity (Record) mit einem Message-Feld.
- [ ] Integration von RestControllerAdvice in bestehende Spring-Projekte.
- [ ] Vorbereitung auf die Entwicklung von REST-basierten Backends mit Controllern, Services und Repositories.

## Einführung

Das **RestControllerAdvice** ist ein leistungsstolzes Werkzeug im Spring-Framework, das uns hilft, die Fehlerbehandlung
und -kommunikation in unserer RESTful-Anwendung zu verbessern. Es ermöglicht uns, auf eine elegante und zentralisierte
Weise auf Fehler zu reagieren und Rückmeldungen an die Clientanwendung zu liefern. Ein RestControllerAdvice kann sowohl
global auf alle Controller als auch spezifisch für einen bestimmten Controller angewendet werden.

## ErrorMessage-Entity (Record)

Um die Kommunikation von Fehlermeldungen zu vereinfachen, erstellen wir eine einfache ErrorMessage-Entity (Record) mit
einem Message-Feld.

```java
public record ErrorMessage(String message) {
}
```

Diese Entity enthält nur ein Feld `message`, das den Text der Fehlermeldung enthält. Dadurch können wir Fehlermeldungen
in einer strukturierten Form zurückgeben.

> 💡 Um die ErrorMessage-Entity noch leistungsfähiger zu gestalten, könnten wir weitere Felder wie Fehlercode,
> Zeitstempel usw. hinzufügen.

## Http-Statuscode setzen für Fehlerantworten

Um die Fehlerkommunikation zu verbessern, ist es wichtig, die richtigen HTTP-Statuscodes für die Antworten zu setzen.
Dies hilft dem Client, die Art des Fehlers zu verstehen und entsprechend zu reagieren.
Dafür gibt es in Spring die Annotation **@ResponseStatus**, die wir über unsere ExceptionHandler Methoden anwenden
können.

```java
@ResponseStatus(HttpStatus.BAD_REQUEST)
```

> 💡 Die Annotation @ResponseStatus kann auch über die Methoden unserer Controller angewendet werden, um den Statuscode
> der Antwort zu setzen.

## Globale Fehlerbehandlung mit @RestControllerAdvice

Die Annotation **@RestControllerAdvice** erlaubt es uns, eine Klasse zu erstellen, die global auf alle Controller
innerhalb unserer Anwendung Einfluss nimmt. Dies ist besonders nützlich, um gemeinsame Fehlerbehandlungslogik an einer
zentralen Stelle zu definieren.

```java

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorMessage handleGlobalException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage("Ein Fehler ist aufgetreten: " + ex.getMessage());
        return errorMessage;
    }
}
```

In diesem Beispiel verwenden wir die Annotation **@ExceptionHandler**, um die Methode `handleGlobalException` zu
definieren. Diese Methode wird aufgerufen, wenn eine Exception innerhalb eines Controllers nicht behandelt wurde. Sie
erstellt eine Instanz unserer ErrorMessage-Entity (Record) und sendet eine HTTP-Antwort mit dem entsprechenden
Statuscode zurück.

## Integration in Spring-Projekte

Um das RestControllerAdvice in Ihr bestehendes Spring-Projekt zu integrieren, folgen Sie diesen Schritten:

1. Erstellt eine neue Klasse (z. B. GlobalExceptionHandler) mit der Annotation @RestControllerAdvice.
2. Definiert eine Methode, die mit @ExceptionHandler annotiert sind, um verschiedene Arten von Fehlern zu behandeln.
3. Nutzt die erstellte ErrorMessage-Klasse, um strukturierte Fehlermeldungen zurückzugeben.

## Ressourcen

- [Spring Framework Dokumentation: Exception Handling](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-exceptionhandler)
- [Baeldung Tutorial: Spring RestControllerAdvice](https://www.baeldung.com/exception-handling-for-rest-with-spring)
