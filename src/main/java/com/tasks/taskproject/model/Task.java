package com.tasks.taskproject.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id",  nullable = false)
    private Person assignedPerson;

    public Task() {

    }

    public Task(Long id, String nombre, String descripcion, Person assignedPerson) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.assignedPerson = assignedPerson;
    }
}
