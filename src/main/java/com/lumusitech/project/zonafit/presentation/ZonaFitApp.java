package com.lumusitech.project.zonafit.presentation;

import com.lumusitech.project.zonafit.data.CustomerDAO;
import com.lumusitech.project.zonafit.data.ICustomerDAO;
import com.lumusitech.project.zonafit.domain.Customer;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        var exit = false;
        var scanner = new Scanner(System.in);
        var customerDAO = new CustomerDAO();

        while (!exit) {
            try {
                var option = showMenu(scanner);
                exit = handleOptions(scanner, option, customerDAO);
            } catch (Exception e) {
                System.out.println("Error al mostrar las opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int showMenu(Scanner scanner) {
        System.out.print("""
                *** ZONA FIT GYM
                1. Listar clientes
                2. Buscar Cliente
                3. Agregar cliente
                4. Actualiar cliente
                5. Eliminar cliente
                6. Salir
                Elige una opción:\s""");
        return Integer.parseInt(scanner.nextLine());
    }

    private static boolean handleOptions(Scanner scanner, int option, ICustomerDAO customerDAO) {
        var exit = false;

        switch (option) {
            case 1 -> {
                System.out.println("--- Listado de clientes ---");
                var customers = customerDAO.findAll();
                customers.forEach(System.out::println);
                break;
            }
            case 2 -> {
                int id;
                try {
                    System.out.println("Introduce el id del cliente");
                    id = Integer.parseInt(scanner.nextLine());
                    var customer = new Customer(id);
                    var exists = customerDAO.existsById(customer);
                    if (exists) System.out.println("Cliente encontrado: " + customer);
                    else System.out.println("Cliente NO encontrado: " + customer);
                } catch (Exception e) {
                    System.out.println("id inválido, solo se admiten valores numéricos.");
                }
                break;
            }
            case 3 -> {
                System.out.println("--- Agregar Cliente ---");
                System.out.print("Nombre: ");
                String name = scanner.nextLine();
                System.out.print("Apellido: ");
                String lastname = scanner.nextLine();
                System.out.print("Membresía: ");
                int membership;
                try {
                    membership = Integer.parseInt(scanner.nextLine());
                    var customer = new Customer(name, lastname, membership);
                    var saved = customerDAO.save(customer);
                    if (saved) System.out.println("Cliente guardado: " + customer);
                    else System.out.println("Cliente NO guardado: " + customer);
                } catch (Exception e) {
                    System.out.println("¡Error! Debes introducir un número entero para la membresía.");
                }
                break;
            }
            case 4 -> {
                System.out.println("--- Actualizar Cliente ---");
                int id = 0;
                try {
                    System.out.print("ID: ");
                    id = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("El ID de cliente debe ser un valor numérico");
                }

                System.out.print("Nombre: ");
                String name = scanner.nextLine();
                System.out.print("Apellido: ");
                String lastname = scanner.nextLine();

                int membership = 0;
                try {
                    System.out.print("Membresía: ");
                    membership = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("La membresía debe ser un valor numérico");
                }

                var customer = new Customer(id, name, lastname, membership);
                var updated = customerDAO.update(customer);
                if (updated) System.out.println("Cliente actualizado: " + customer);
                else System.out.println("Cliente No actualizado: " + customer);
                break;
            }
            case 5 -> {
                System.out.println("--- Eliminar Cliente ---");
                int id = 0;
                try {
                    System.out.print("ID: ");
                    id = Integer.parseInt(scanner.nextLine());
                    var customer = new Customer(id);
                    var deleted = customerDAO.delete(customer);
                    if (deleted) System.out.println("Cliente eliminado: " + customer);
                    else System.out.println("Cliente NO eliminado: " + customer);
                } catch (Exception e) {
                    System.out.println("Error: El id del cliente debe ser un valor numérico.");
                }
                break;
            }
            case 6 -> {
                exit = true;
                System.out.println("Gracias por usar nuestra app ZONA FIT");
                break;
            }
            default -> System.out.println("Opción inválida!");
        }
        return exit;
    }
}
