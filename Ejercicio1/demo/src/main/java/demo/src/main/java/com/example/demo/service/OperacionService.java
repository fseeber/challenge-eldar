package demo.src.main.java.com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.src.main.java.com.example.demo.model.Operacion;
import demo.src.main.java.com.example.demo.repository.OperacionRepository;

@Service
public class OperacionService {
    
    @Autowired
    private OperacionRepository operacionRepository;

    public Operacion registrarOperacion(Operacion operacion) {

        return operacionRepository.save(operacion);
    }
}
