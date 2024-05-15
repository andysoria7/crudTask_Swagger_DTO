package com.tasks.taskproject.controller;
import com.tasks.taskproject.dto.TaskDTO;
import com.tasks.taskproject.dto.TaskPersonDTO;
import com.tasks.taskproject.service.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TaskController {

    // Inyeccion de dependencia de mi interfaz ITaskService
    @Autowired
    private ITaskService taskServ;

    // Endpoint de GetAllTasksWithPersons para mostrar todas las tareas con personas que tienen asignada
    @GetMapping("/tasksWithPersons")
    public List<TaskPersonDTO> getTasksWithPersons() {
        return taskServ.getTasksWithPersons();
    }

    // Endpoint de getByIdTaskWithPerson para mostrar una tarea con persona asociada
    @GetMapping("/taskWithPerson/{id}")
    public ResponseEntity<?> getTaskWithPersonById(@PathVariable Long id) {
        try {
            TaskPersonDTO taskPersonDTO = taskServ.findTaskWithPerson(id);
            return ResponseEntity.ok(taskPersonDTO); // Si se encuentra la tarea, devuelve los datos
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Si no se encuentra, devuelve el mensaje de error
        }
    }

    // Endpoint saveTask para guardar una tarea con persona asignada
    @PostMapping("/task")
    public ResponseEntity<?> saveTask(@Valid @RequestBody TaskDTO taskDTO) {
        try {
            taskServ.saveTask(taskDTO); // Llama al método del servicio para guardar la tarea
            return ResponseEntity.ok("Tarea guardada exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Si no se encuentra la persona, devuelve el mensaje de error
        }
    }

    // Endpoint editTask para actualizar una tarea con persona asignada
    @PutMapping("/task/{id}")
    public ResponseEntity<String> editTask(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO) {
        try {
            taskServ.editTask(id, taskDTO);
            return ResponseEntity.ok("Tarea actualizada exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    // Endpoint deleteTask para eliminar una tarea
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        try {
            taskServ.deleteTask(id);
            return ResponseEntity.ok("Tarea eliminada exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
