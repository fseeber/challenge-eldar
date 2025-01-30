package demo.src.main.java.com.example.demo.service;

import demo.src.main.java.com.example.demo.model.Compra;
import demo.src.main.java.com.example.demo.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public Compra realizarCompra(Compra compra) {
        // Lógica para verificar la tarjeta, monto, CVV, etc.
        // Enviar notificación por email al usuario

        return compraRepository.save(compra);
    }

}
