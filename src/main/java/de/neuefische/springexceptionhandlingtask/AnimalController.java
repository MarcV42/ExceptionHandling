package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @GetMapping("{species}")
    String getAnimalSpecies(@PathVariable String species) {
        if (!species.equals("dog")) {
            throw new IllegalArgumentException("Only 'dog' is allowed");
        }
        return species;
    }

    @GetMapping
    String getAllAnimals() {
        throw new NoSuchElementException("No Animals found");
    }


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleExceptions(Exception exception){
        System.out.println("ACHTUNG, ATTENTIONE PREGO, UNERWARTETER FEHLER");
        return new ErrorMessage("Ups, hier stimmt was nicht...." + exception.getMessage());
    }

}
