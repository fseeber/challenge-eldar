package demo.src.main.java.com.example.demo.repository;

import demo.src.main.java.com.example.demo.model.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacionRepository extends JpaRepository<Operacion, Long> {

}
