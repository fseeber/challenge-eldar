package demo.src.main.java.com.example.demo.repository;

import demo.src.main.java.com.example.demo.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}