package demo.src.main.java.com.example.demo.repository;

import demo.src.main.java.com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByDni(String dni);
}