package demo.src.main.java.com.example.demo.repository;

import demo.src.main.java.com.example.demo.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
}