package demo.src.main.java.com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String marca;

    @NotNull
    private String numero;

    @NotNull
    private String fechaVencimiento;

    @NotNull
    private String titular;

    // Getters y setters
}