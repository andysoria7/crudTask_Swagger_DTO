package com.tasks.taskproject.service;

import com.tasks.taskproject.dto.PersonDTO;

import java.util.List;

public interface IPersonService {

    public List<PersonDTO> getPersons();

    public PersonDTO findPersona(Long id);

    public void savePersona(PersonDTO personDTO);

    public void editPersona(Long id, PersonDTO personDTO);

    public void deletePersona(Long id);

}
