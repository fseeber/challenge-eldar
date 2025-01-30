package demo.src.main.java.com.example.demo.controller;

import demo.src.main.java.com.example.demo.model.Usuario;
import demo.src.main.java.com.example.demo.repository.TarjetaRepository;
import demo.src.main.java.com.example.demo.repository.UsuarioRepository;
import demo.src.main.java.com.example.demo.model.Tarjeta;
import demo.src.main.java.com.example.demo.model.Compra;
import demo.src.main.java.com.example.demo.service.UsuarioService;
import demo.src.main.java.com.example.demo.service.TarjetaService;
import demo.src.main.java.com.example.demo.service.CompraService;
import demo.src.main.java.com.example.demo.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private CompraService compraService;

    @Autowired
    private TarjetaRepository tarjetaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

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
    public ResponseEntity<Compra> realizarCompra(@Valid @RequestBody Compra compra) {
        try {
            Tarjeta tarjeta = tarjetaRepository.findById(compra.getTarjeta().getId())
                                              .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
            compra.setTarjeta(tarjeta);
            Compra compraRealizada = compraService.realizarCompra(compra);
            emailService.enviarCorreoCompraExitosa(compraRealizada.getTarjeta().getUsuario().getEmail(),
            compraRealizada.getDetalle(),
            compraRealizada.getMonto());
            return ResponseEntity.ok(compraRealizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
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
