package demo.src.main.java.com.example.demo.service;

import demo.src.main.java.com.example.demo.model.Tarjeta;
import demo.src.main.java.com.example.demo.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    public Tarjeta registrarTarjeta(Tarjeta tarjeta) {

        return tarjetaRepository.save(tarjeta);
    }

    public String consultarTasas(String fecha) {
        if (fecha == null || fecha.isEmpty()) {
            fecha = "fecha actual"; 
        }

        return "Tasas de las marcas: [Visa, MasterCard, etc.]";
    }
}