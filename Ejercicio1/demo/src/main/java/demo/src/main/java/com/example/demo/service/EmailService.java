package demo.src.main.java.com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarCorreoCompraExitosa(String email, String detalleCompra, double monto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("smtp@mailtrap.io"); // Tu correo de origen
        message.setTo(email); // El correo del usuario
        message.setSubject("Confirmación de Compra Exitosa");
        message.setText("Hola, \n\nTu compra ha sido realizada con éxito.\nDetalle de la compra:\n" +
                        "Monto: $" + monto + "\n" +
                        "Detalle: " + detalleCompra + "\n\nGracias por tu compra!");

        emailSender.send(message);
    }
}