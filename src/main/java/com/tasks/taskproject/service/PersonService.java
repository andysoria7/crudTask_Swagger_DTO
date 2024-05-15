package com.tasks.taskproject.service;

import com.tasks.taskproject.dto.PersonDTO;
import com.tasks.taskproject.model.Person;
import com.tasks.taskproject.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository personRepo;

    @Override
    public List<PersonDTO> getPersons() {
        List<Person> persons = personRepo.findAll();
        List<PersonDTO> listaPersonsDTO = new ArrayList<>();

        for (Person person : persons) {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setNombre(person.getNombre());
            personDTO.setApellido(person.getApellido());
            personDTO.setEdad(person.getEdad());
            listaPersonsDTO.add(personDTO);
        }

        return listaPersonsDTO;

    }

    @Override
    public PersonDTO findPersona(Long id) {
        // Buscar la persona en la base de datos utilizando su ID
        Optional<Person> optionalPerson = personRepo.findById(id);

        // Verificar si la persona existe
        if (optionalPerson.isPresent()) {
            // Si la persona existe, obtener sus datos y crear un DTO
            Person person = optionalPerson.get();
            PersonDTO personDTO = new PersonDTO(person.getNombre(), person.getApellido(), person.getEdad());
            return personDTO;
        } else {
            // Si la persona no existe, manejar la situación (por ejemplo, lanzar una excepción)
            throw new RuntimeException("La persona con el ID proporcionado no existe.");
        }
    }

    @Override
    public void savePersona(PersonDTO personDTO) {
        // Crear una nueva entidad Person y asignar los valores del DTO
        Person person = new Person();
        person.setNombre(personDTO.getNombre());
        person.setApellido(personDTO.getApellido());
        person.setEdad(personDTO.getEdad());

        // Guardar la entidad Person en la base de datos usando el repositorio
        personRepo.save(person);
    }

    @Override
    public void editPersona(Long id, PersonDTO personDTO) {
        // Obtener la persona existente de la base de datos utilizando el ID proporcionado
        Optional<Person> optionalPerson = personRepo.findById(id);

        // Verificar si la persona existe
        if (optionalPerson.isPresent()) {
            // Obtener la persona de la base de datos
            Person existingPerson = optionalPerson.get();

            // Actualizar los datos de la persona con los valores del DTO
            existingPerson.setNombre(personDTO.getNombre());
            existingPerson.setApellido(personDTO.getApellido());
            existingPerson.setEdad(personDTO.getEdad());

            // Guardar la persona actualizada en la base de datos
            personRepo.save(existingPerson);
        } else {
            // Manejar la situación si la persona no existe (por ejemplo, lanzar una excepción o registrar un mensaje de error)
            // Por ejemplo, lanzar una excepción:
            throw new RuntimeException("La persona con el ID proporcionado no existe.");
        }
    }

    @Override
    public void deletePersona(Long id) {
        // Verificar si la persona existe en la base de datos
        if (personRepo.existsById(id)) {
            // Si la persona existe, eliminarla de la base de datos
            personRepo.deleteById(id);
        } else {
            // Manejar la situación si la persona no existe (por ejemplo, lanzar una excepción)
            throw new RuntimeException("La persona con el ID proporcionado no existe.");
        }
    }

}

