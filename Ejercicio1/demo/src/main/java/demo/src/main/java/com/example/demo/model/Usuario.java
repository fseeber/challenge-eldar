package demo.src.main.java.com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private String dni;

    @NotNull
    private String fechaNacimiento;

    @Email
    @NotNull
    private String email;

    // Getters y setters

}
