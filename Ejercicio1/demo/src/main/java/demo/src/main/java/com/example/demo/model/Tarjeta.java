package demo.src.main.java.com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull
    private String marca;

    //@NotNull
    private String numero;

    //@NotNull
    private String fechaVencimiento;

    //@NotNull
    private String titular;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Relación con el usuario

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}