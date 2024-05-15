package com.tasks.taskproject.controller;
import com.tasks.taskproject.dto.PersonDTO;
import com.tasks.taskproject.service.IPersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PersonController {

    // Inyeccion de dependencia de mi interfaz IPersonService
    @Autowired
    private IPersonService personServ;

    // Endpoint de GetAll persons para mostrar todas las personas
    @GetMapping("/persons")
    public List<PersonDTO> getPersons() {
        return personServ.getPersons();
    }

    // Endpoint de getById person para mostrar una persona
    @GetMapping("/person/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {
        try {
            PersonDTO personDTO = personServ.findPersona(id);
            return ResponseEntity.ok(personDTO); // Si se encuentra la persona, devuelve los datos
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Si no se encuentra, devuelve el mensaje de error
        }
    }

    // Endpoint savePerson para guardar una persona
    @PostMapping("/person")
    public ResponseEntity<?> savePerson(@Valid @RequestBody PersonDTO personDTO) {
        personServ.savePersona(personDTO); // Llama al método del servicio para guardar la persona
        return ResponseEntity.ok("Persona guardada exitosamente.");
    }

    // Endpoint editPersona para actualizar una persona
    @PutMapping("/person/{id}")
    public ResponseEntity<String> editPerson(@PathVariable Long id, @Valid @RequestBody PersonDTO personDTO) {
        try {
            personServ.editPersona(id, personDTO);
            return ResponseEntity.ok("Persona actualizada exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint deletePersona para eliminar una persona
    @DeleteMapping("/person/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        try {
            personServ.deletePersona(id);
            return ResponseEntity.ok("Persona eliminada exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}