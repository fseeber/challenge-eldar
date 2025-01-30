package demo.src.main.java.com.example.demo.controller;

import demo.src.main.java.com.example.demo.model.Usuario;
import demo.src.main.java.com.example.demo.repository.OperacionRepository;
import demo.src.main.java.com.example.demo.repository.TarjetaRepository;
import demo.src.main.java.com.example.demo.repository.UsuarioRepository;
import demo.src.main.java.com.example.demo.request.OperacionRequest;
import demo.src.main.java.com.example.demo.model.Tarjeta;
import demo.src.main.java.com.example.demo.model.Compra;
import demo.src.main.java.com.example.demo.model.Operacion;
import demo.src.main.java.com.example.demo.service.UsuarioService;
import demo.src.main.java.com.example.demo.service.TarjetaService;
import demo.src.main.java.com.example.demo.service.TasaService;
import demo.src.main.java.com.example.demo.service.CompraService;
import demo.src.main.java.com.example.demo.service.EmailService;
import demo.src.main.java.com.example.demo.service.OperacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private OperacionService operacionService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TarjetaRepository tarjetaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TasaService tasaServicio;

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> registrarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/tarjetas")
    public ResponseEntity<Tarjeta> registrarTarjeta(@Valid @RequestBody Tarjeta tarjeta) {
        try {
            Usuario usuario = usuarioRepository.findById(tarjeta.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            tarjeta.setUsuario(usuario);
            Tarjeta nuevaTarjeta = tarjetaService.registrarTarjeta(tarjeta);
            return ResponseEntity.ok(nuevaTarjeta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/compras")
    public ResponseEntity<String> realizarCompra(@RequestBody OperacionRequest operacionRequest) {
    
    
    if (!operacionRequest.getCvv().equals(operacionRequest.getCvv())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("CVV incorrecto");
    }
    /* 
    if (!operacionRequest.getFechaVencimiento().isAfter(LocalDate.now())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La tarjeta está vencida");
    }
    */
    if (operacionRequest.getMonto().compareTo(BigDecimal.valueOf(10000)) > 0) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Monto superior al límite permitido");
    }

    Operacion operacion = new Operacion();
    operacion.setTarjeta(new Tarjeta());
    operacion.setMonto(operacionRequest.getMonto());
    operacion.setDetalle(operacionRequest.getDetalle());
    operacion.setFechaOperacion(LocalDate.now());

    BigDecimal tasa = tasaServicio.calcularTasa(new Tarjeta());
    BigDecimal totalConTasa = operacionRequest.getMonto().add(operacionRequest.getMonto().multiply(tasa));
    
    operacionService.registrarOperacion(operacion);  

    emailService.enviarCorreoCompraExitosa("seeberfederico@gmail.com", operacionRequest.getDetalle(), totalConTasa);

    return ResponseEntity.ok("Compra realizada con éxito");
    }

    @GetMapping("/tasas")
    public ResponseEntity<String> consultarTasas(@RequestParam(required = false) String fecha) {
        try {
            String tasa = tarjetaService.consultarTasas(fecha);
            return ResponseEntity.ok(tasa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
