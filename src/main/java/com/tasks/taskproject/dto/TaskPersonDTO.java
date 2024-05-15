package com.tasks.taskproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaskPersonDTO {

    // Atributos de Task
    private String nombre_tarea;
    private String descripcion_tarea;
    // Atributos de Person
    private String nombre_persona;
    private String apellido_persona;
    private int edad_persona;

    public TaskPersonDTO() {

    }

    public TaskPersonDTO(String nombre_tarea, String descripcion_tarea, String nombre_persona, String apellido_persona, int edad_persona) {
        this.nombre_tarea = nombre_tarea;
        this.descripcion_tarea = descripcion_tarea;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.edad_persona = edad_persona;
    }
}
