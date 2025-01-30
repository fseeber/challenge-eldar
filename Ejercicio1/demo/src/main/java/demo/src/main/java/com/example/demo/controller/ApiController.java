package demo.src.main.java.com.example.demo.controller;

import demo.src.main.java.com.example.demo.model.Usuario;
import demo.src.main.java.com.example.demo.model.Tarjeta;
import demo.src.main.java.com.example.demo.model.Compra;
import demo.src.main.java.com.example.demo.service.UsuarioService;
import demo.src.main.java.com.example.demo.service.TarjetaService;
import demo.src.main.java.com.example.demo.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> registrarUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/tarjetas")
    public ResponseEntity<Tarjeta> registrarTarjeta(@Valid @RequestBody Tarjeta tarjeta) {
        Tarjeta nuevaTarjeta = tarjetaService.registrarTarjeta(tarjeta);
        return ResponseEntity.ok(nuevaTarjeta);
    }

    @PostMapping("/compras")
    public ResponseEntity<Compra> realizarCompra(@Valid @RequestBody Compra compra) {
        Compra compraRealizada = compraService.realizarCompra(compra);
        return ResponseEntity.ok(compraRealizada);
    }

    @GetMapping("/tasas")
    public ResponseEntity<String> consultarTasas(@RequestParam(required = false) String fecha) {
        String tasa = tarjetaService.consultarTasas(fecha);
        return ResponseEntity.ok(tasa);
    }
}
