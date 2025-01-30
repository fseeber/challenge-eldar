package demo.src.main.java.com.example.demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import demo.src.main.java.com.example.demo.model.MarcaTarjeta;
import demo.src.main.java.com.example.demo.model.Tarjeta;

@Service
public class TasaService {

    public BigDecimal calcularTasa(Tarjeta tarjeta) {
        BigDecimal tasa = BigDecimal.ZERO;

        if (tarjeta.getMarca() == MarcaTarjeta.VISA) {
            tasa = calcularTasaVISA(tarjeta.getFechaVencimiento());
        } else if (tarjeta.getMarca() == MarcaTarjeta.NARA) {
            tasa = calcularTasaNARA(tarjeta.getFechaVencimiento());
        } else if (tarjeta.getMarca() == MarcaTarjeta.AMEX) {
            tasa = calcularTasaAMEX(tarjeta.getFechaVencimiento());
        }

        return tasa;
    }

    private BigDecimal calcularTasaVISA(LocalDate fecha) {
        int año = fecha.getYear();
        int mes = fecha.getMonthValue();
        return BigDecimal.valueOf(año).divide(BigDecimal.valueOf(mes), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calcularTasaNARA(LocalDate fecha) {
        int dia = fecha.getDayOfMonth();
        return BigDecimal.valueOf(dia).multiply(BigDecimal.valueOf(0.5));
    }

    private BigDecimal calcularTasaAMEX(LocalDate fecha) {
        int mes = fecha.getMonthValue();
        return BigDecimal.valueOf(mes).multiply(BigDecimal.valueOf(0.1));
    }
}
