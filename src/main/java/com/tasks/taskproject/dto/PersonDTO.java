package com.tasks.taskproject.dto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonDTO {

    @NotBlank(message = "El nombre de la persona es obligatorio.")
    private String nombre;
    @NotBlank(message = "El apellido de la persona es obligatorio.")
    private String apellido;
    @Positive(message = "La edad de la persona debe ser un número positivo.")
    @Min(value = 1, message = "La edad de la persona debe ser mayor o igual a 1.")
    private int edad;

    public PersonDTO() {

    }

    public PersonDTO(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

}
