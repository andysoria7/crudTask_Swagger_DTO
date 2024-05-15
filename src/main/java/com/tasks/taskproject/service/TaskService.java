package com.tasks.taskproject.service;
import com.tasks.taskproject.dto.TaskDTO;
import com.tasks.taskproject.dto.TaskPersonDTO;
import com.tasks.taskproject.model.Person;
import com.tasks.taskproject.model.Task;
import com.tasks.taskproject.repository.IPersonRepository;
import com.tasks.taskproject.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private ITaskRepository taskRepo;

    @Autowired
    private IPersonRepository personRepo;

    @Override
    public List<TaskPersonDTO> getTasksWithPersons() {
        List<Task> tasks = taskRepo.findAll(); // Obtener todas las tareas de la base de datos

        List<TaskPersonDTO> listTasksWithPersons = new ArrayList<>();

        // Mapear cada tarea a un TaskPersonDTO
        for (Task task : tasks) {
            TaskPersonDTO taskPersonDTO = new TaskPersonDTO();
            taskPersonDTO.setNombre_tarea(task.getNombre());
            taskPersonDTO.setDescripcion_tarea(task.getDescripcion());

            // Obtener la persona asignada a esta tarea
            Person assignedPerson = task.getAssignedPerson();
            if (assignedPerson != null) {
                // Si la persona asignada existe, mapear sus datos al DTO
                taskPersonDTO.setNombre_persona(assignedPerson.getNombre());
                taskPersonDTO.setApellido_persona(assignedPerson.getApellido());
                taskPersonDTO.setEdad_persona(assignedPerson.getEdad());
            }

            listTasksWithPersons.add(taskPersonDTO);
        }

        return listTasksWithPersons;
    }

    @Override
    public TaskPersonDTO findTaskWithPerson(Long id) {
        // Buscar la tarea en la base de datos utilizando su ID
        Optional<Task> optionalTask = taskRepo.findById(id);

        // Verificar si la tarea existe
        if (optionalTask.isPresent()) {
            // Si la tarea existe, obtener sus datos y crear un DTO
            Task task = optionalTask.get();
            TaskPersonDTO taskPersonDTO = new TaskPersonDTO();
            taskPersonDTO.setNombre_tarea(task.getNombre());
            taskPersonDTO.setDescripcion_tarea(task.getDescripcion());

            // Obtener la persona asignada a esta tarea
            Person assignedPerson = task.getAssignedPerson();
            if (assignedPerson != null) {
                // Si la persona asignada existe, mapear sus datos al DTO
                taskPersonDTO.setNombre_persona(assignedPerson.getNombre());
                taskPersonDTO.setApellido_persona(assignedPerson.getApellido());
                taskPersonDTO.setEdad_persona(assignedPerson.getEdad());
            }

            return taskPersonDTO;
        } else {
            // Si la tarea no existe, manejar la situación (por ejemplo, lanzar una excepción)
            throw new RuntimeException("La tarea con el ID proporcionado no existe.");
        }
    }

    @Override
    public void saveTask(TaskDTO taskDTO) {
        // Verificar si la persona asignada existe en la base de datos utilizando su ID
        Optional<Person> optionalPerson = personRepo.findById(taskDTO.getAssignedPersonId());
        if (optionalPerson.isPresent()) {
            // Si la persona existe, crear la tarea y asignar la persona a la tarea
            Task task = new Task();
            task.setNombre(taskDTO.getNombre());
            task.setDescripcion(taskDTO.getDescripcion());
            task.setAssignedPerson(optionalPerson.get());

            // Guardar la tarea en la base de datos
            taskRepo.save(task);
        } else {
            // Si la persona no existe, lanzar una excepción
            throw new RuntimeException("La persona asignada con el ID proporcionado no existe.");
        }
    }

    @Override
    public void editTask(Long id, TaskDTO taskDTO) {
        // Obtener la tarea existente de la base de datos utilizando el ID proporcionado
        Optional<Task> optionalTask = taskRepo.findById(id);

        // Verificar si la tarea existe
        if (optionalTask.isPresent()) {
            // Obtener la tarea de la base de datos
            Task existingTask = optionalTask.get();

            // Verificar si la persona asignada existe en la base de datos utilizando su ID
            Optional<Person> optionalPerson = personRepo.findById(taskDTO.getAssignedPersonId());
            if (optionalPerson.isPresent()) {
                // Si la persona existe, actualizar los campos de la tarea con los datos del DTO
                existingTask.setNombre(taskDTO.getNombre());
                existingTask.setDescripcion(taskDTO.getDescripcion());
                existingTask.setAssignedPerson(optionalPerson.get());

                // Guardar la tarea actualizada en la base de datos
                taskRepo.save(existingTask);
            } else {
                // Si la persona no existe, lanzar una excepción
                throw new RuntimeException("La persona asignada con el ID proporcionado no existe.");
            }
        } else {
            // Si la tarea no existe, lanzar una excepción o manejar la situación de otra manera
            throw new RuntimeException("La tarea con el ID proporcionado no existe.");
        }
    }

    @Override
    public void deleteTask(Long id) {
        // Verificar si la tarea existe en la base de datos
        if(taskRepo.existsById(id)){
            // Si la tarea existe, eliminarla de la base de datos
            taskRepo.deleteById(id);
        } else {
            // Manejar la situación si la tarea no existe (por ejemplo, lanzar una excepción)
            throw new RuntimeException("La tarea con el ID proporcionado no existe.");
        }

    }
}
