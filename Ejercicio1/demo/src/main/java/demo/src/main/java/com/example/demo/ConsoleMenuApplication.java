package demo.src.main.java.com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ConsoleMenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleMenuApplication.class, args);
    }

    @Bean
    CommandLineRunner runApplication() {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            List<Person> persons = new ArrayList<>();
            List<Card> cards = new ArrayList<>();
            
            while (true) {
                System.out.println("Menu Interactivo:");
                System.out.println("1. Registrar Persona");
                System.out.println("2. Registrar Tarjeta");
                System.out.println("3. Consultar Tarjetas por DNI");
                System.out.println("4. Consultar Tasas por Fecha");
                System.out.println("5. Salir");
                System.out.print("Selecciona una opción: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Nombre: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Apellido: ");
                        String lastName = scanner.nextLine();
                        System.out.print("DNI: ");
                        String dni = scanner.nextLine();
                        System.out.print("Fecha de Nacimiento (dd-MM-yyyy): ");
                        String birthDate = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        persons.add(new Person(firstName, lastName, dni, birthDate, email));
                        System.out.println("Persona registrada exitosamente.");
                        break;

                    case 2:
                        System.out.print("Marca de Tarjeta: ");
                        String brand = scanner.nextLine();
                        System.out.print("Número de Tarjeta: ");
                        String cardNumber = scanner.nextLine();
                        System.out.print("Fecha de Vencimiento (MM-yyyy): ");
                        String expirationDate = scanner.nextLine();
                        System.out.print("Nombre Completo del Titular: ");
                        String cardHolder = scanner.nextLine();
                        cards.add(new Card(brand, cardNumber, expirationDate, cardHolder));
                        System.out.println("Tarjeta registrada exitosamente.");
                        break;

                    case 3:
                        System.out.print("DNI del Usuario: ");
                        String queryDni = scanner.nextLine();
                        cards.stream()
                                .filter(card -> card.getCardHolder().equalsIgnoreCase(queryDni))
                                .forEach(System.out::println);
                        break;

                    case 4:
                        System.out.print("Fecha (dd-MM-yyyy) [Enter para fecha actual]: ");
                        String date = scanner.nextLine();
                        if (date.isEmpty()) {
                            date = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        }
                        System.out.println("Consultando tasas para la fecha: " + date);
                        break;

                    case 5:
                        System.out.println("Saliendo del sistema. Hasta luego!");
                        return;

                    default:
                        System.out.println("Opción inválida, intenta nuevamente.");
                }
            }
        };
    }

    static class Person {
        private String firstName;
        private String lastName;
        private String dni;
        private String birthDate;
        private String email;

        public Person(String firstName, String lastName, String dni, String birthDate, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.dni = dni;
            this.birthDate = birthDate;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Nombre: " + firstName + " " + lastName + ", DNI: " + dni + ", Email: " + email;
        }
    }

    static class Card {
        private String brand;
        private String cardNumber;
        private String expirationDate;
        private String cardHolder;

        public Card(String brand, String cardNumber, String expirationDate, String cardHolder) {
            this.brand = brand;
            this.cardNumber = cardNumber;
            this.expirationDate = expirationDate;
            this.cardHolder = cardHolder;
        }

        public String getCardHolder() {
            return cardHolder;
        }

        @Override
        public String toString() {
            return "Marca: " + brand + ", Número: " + cardNumber + ", Vence: " + expirationDate + ", Titular: " + cardHolder;
        }
    }
}

