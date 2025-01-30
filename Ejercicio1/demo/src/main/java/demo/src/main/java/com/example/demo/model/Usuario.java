package demo.src.main.java.com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull
    private String nombre;

    //@NotNull
    private String apellido;

    //@NotNull
    private String dni;

    //@NotNull
    private LocalDate fechaNacimiento;

    @Email
    //@NotNull
    private String email;

    @OneToMany(mappedBy = "usuario")
    private List<Tarjeta> tarjetas; // Relación con las tarjetas
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
}
