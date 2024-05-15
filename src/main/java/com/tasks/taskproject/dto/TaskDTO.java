package com.tasks.taskproject.dto;
import com.tasks.taskproject.model.Person;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    @NotBlank(message = "El nombre de la tarea es obligatorio.")
    private String nombre;
    @NotBlank(message = "La descripcion de la tarea es obligatorio.")
    private String descripcion;
    private Long assignedPersonId;

    public TaskDTO() {

    }

    public TaskDTO(String nombre, String descripcion, Long assignedPersonId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.assignedPersonId = assignedPersonId;
    }
}
