package Supermercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/supermercado";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Amemai34934$";

    private static Connection connection;

    public static void main(String[] args) {
        try {  
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Conexion establecida con exito.\n");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Que accion desea realizar?");
                System.out.println("1. Dar de alta cliente");
                System.out.println("2. Dar de baja cliente");
                System.out.println("3. Dar de alta producto");
                System.out.println("4. Dar de baja producto");
                System.out.println("5. Realizar compra");
                System.out.println("6. Salir");

                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        darDeAltaCliente(scanner);
                        break;
                    case 2:
                        darDeBajaCliente(scanner);
                        break;
                    case 3:
                        darDeAltaProducto(scanner);
                        break;
                    case 4:
                        darDeBajaProducto(scanner);
                        break;
                    case 5:
                        realizarCompra(scanner);
                        break;
                    case 6:
                        System.out.println("Hasta luego!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                        break;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    private static void darDeAltaCliente(Scanner scanner) {
    	System.out.println("Ingrese el DNI del cliente:");
        int DNI = scanner.nextInt();
    	
    	System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.next();

        System.out.println("Ingrese el apellido del cliente:");
        String apellido = scanner.next();

        System.out.println("Ingrese el domicilio del cliente:");
        String domicilio = scanner.next();

        System.out.println("Ingrese el telefono del cliente:");
        int telefono = scanner.nextInt();

        try {
            String query = "INSERT INTO cliente (clienteDNI, clienteNombre, clienteApellido, clienteDomicilio, clienteTelefono) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, DNI);
            statement.setString(2, nombre);
            statement.setString(3, apellido);
            statement.setString(4, domicilio);
            statement.setInt(5, telefono);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("\nCliente dado de alta con exito.");
            } else {
                System.out.println("\nNo se pudo dar de alta al cliente.");
            }
        } catch (SQLException e) {
            System.err.println("\nError al dar de alta al cliente: " + e.getMessage());
        }
    }

    private static void darDeBajaCliente(Scanner scanner) {
        System.out.println("Ingrese el DNI del cliente a dar de baja:");
        int clienteDNI = scanner.nextInt();

        try {
            String query = "DELETE FROM cliente WHERE clienteDNI = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, clienteDNI);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente dado de baja con exito.");
            } else {
            	System.out.println("No se pudo dar de baja al cliente");
            }
         } catch (SQLException e) {
            System.err.println("Error al dar de baja al cliente: " + e.getMessage());
         }
     }
    
    private static void darDeAltaProducto(Scanner scanner) {
        System.out.println("Ingrese el nombre del producto:");
        String nombre = scanner.next();
        
        System.out.println("Ingrese la descripcion del producto:");
        String descripcion = scanner.next();
        
        System.out.println("Ingrese la Fecha de Vencimiento del producto:");
        String fechaVenc = scanner.next();

        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();

        try {
            String query = "INSERT INTO producto (productoNombre, productoDescripcion, productoFechaVenc, productoPrecio) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setString(3, fechaVenc);
            statement.setDouble(4, precio);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Producto dado de alta con exito.");
            } else {
                System.out.println("No se pudo dar de alta al producto.");
            }
        } catch (SQLException e) {
            System.err.println("Error al dar de alta al producto: " + e.getMessage());
        }
    }

    private static void darDeBajaProducto(Scanner scanner) {
        System.out.println("Ingrese el ID del producto a dar de baja:");
        int productoID = scanner.nextInt();

        try {
            String query = "DELETE FROM producto WHERE productoID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productoID);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Producto dado de baja con exito.");
            } else {
                System.out.println("No se pudo dar de baja al producto.");
            }
        } catch (SQLException e) {
            System.err.println("Error al dar de baja al producto: " + e.getMessage());
        }
    }
    
    private static void realizarCompra(Scanner scanner) {
        System.out.println("Ingrese el DNI del cliente que realiza la compra:");
        int clienteDNI = scanner.nextInt();
        
        
        
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM producto";
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Listado de productos:");
            while (resultSet.next()) {
                int id = resultSet.getInt("productoID");
                String nombre = resultSet.getString("productoNombre");
                double precio = resultSet.getDouble("productoPrecio");
                System.out.printf("%d - %s - $%.2f\n", id, nombre, precio);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el listado de productos: " + e.getMessage());
            return;
        }
        
        System.out.println("Ingrese el ID del producto a comprar (-1 para finalizar):");
        int productoID = scanner.nextInt();
        double total = 0;

        while (productoID != -1) {
            
            int cantidad = 0;
            try {
                String query = "SELECT * FROM stock WHERE productoID = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, productoID);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    cantidad = resultSet.getInt("cantidad");
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener la cantidad de productos disponibles: " + e.getMessage());
                return;
            }

            if (cantidad == 0) {
                System.out.println("No hay stock disponible para el producto seleccionado.");
            } else {
                
                System.out.printf("Ingrese la cantidad de %s que desea comprar (hay %d disponibles): ", obtenerNombreProducto(productoID), cantidad);
                int cantidadAComprar = scanner.nextInt();
                if (cantidadAComprar > cantidad) {
                    System.out.println("No hay suficiente stock disponible para la cantidad solicitada.");
                } else {
                    
                    try {
                        String query = "UPDATE stock SET cantidad = ? WHERE productoID = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setInt(1, cantidad - cantidadAComprar);
                        statement.setInt(2, productoID);
                        int rowsUpdated = statement.executeUpdate();
                        if (rowsUpdated > 0) {
                            total += obtenerPrecioProducto(productoID) * cantidadAComprar;
                        } else {
                            System.out.println("No se pudo actualizar el stock del producto.");
                        }
                    } catch (SQLException e) {
                        System.err.println("Error al actualizar el stock del producto: " + e.getMessage());
                        return;
                    }
                }
            }

            
            System.out.println("Ingrese el ID del producto a comprar (-1 para finalizar):");
            productoID = scanner.nextInt();
        }

        
        try {
            String query = "INSERT INTO venta (clienteDNI, fecha, total) VALUES (?, CURDATE(), ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, clienteDNI);
            statement.setDouble(2, total);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Compra registrada con exito. Total: $" + total);
            } else {
                System.out.println("No se pudo registrar la compra.");
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar la compra: " + e.getMessage());
            return;
        }
    }
    private static String obtenerNombreProducto(int productoID) {
        try {
            String query = "SELECT productoNombre FROM producto WHERE productoID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productoID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("productoNombre");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el nombre del producto: " + e.getMessage());
        }
        return "";
    }

    private static double obtenerPrecioProducto(int productoID) {
        try {
            String query = "SELECT productoPrecio FROM producto WHERE productoID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productoID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("productoPrecio");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el precio del producto: " + e.getMessage());
        }
        return 0.0;
    }

}