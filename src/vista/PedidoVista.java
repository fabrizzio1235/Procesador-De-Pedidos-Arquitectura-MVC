package vista;

import modelo.Pedido;
import modelo.Producto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    VISTA.
    Presenta información, captura acciones y muestra mensajes.
    No calcula descuentos ni impuestos, no almacena pedidos ni aplica reglas de negocio.
    Válida el formato de que mete el usario.
 */

public class PedidoVista {
    private static final Scanner scanner = new Scanner(System.in);

    // Devuelve el pedido capturado, o null si el usuario regresa.
    // el null lo maneja el Controlador
    public Pedido capturarPedido() {
        System.out.println();
        System.out.println("---Registrar Pedido---");
        System.out.println("1. Capturar pedido nuevo");
        System.out.println("2. Usar pedido prueba");
        System.out.println("3. Salir");
        System.out.println("Seleccione una opción: ");
        String opcion = scanner.nextLine().trim();

        switch (opcion) {
            case "1": return capturarPedidoNuevo();
            case "2": return capturarPedidoDePrueba();
            case "3": return null;
            default: mostrarError("Opción no válida");
                return null;
        }
    }

    // Reutilización de capas
    private Pedido capturarPedidoNuevo() {
        System.out.print("Nombre del cliente: ");
        String cliente = scanner.nextLine();

        List<Producto> productos = new ArrayList<>();
        String opcion;

        do {
            System.out.println("1. Agregar producto");
            System.out.println("2. Confirmar pedido");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine().trim();

            if (opcion.equals("1")) {
                productos.add(capturarProducto());
            } else if (!opcion.equals("2")) {
                mostrarError("Opción no válida");
            }
        } while (!opcion.equals("2"));

        // Si el usuario confirma sin agregar productos, o deja el nombre vacío,
        // no se valida aquí. Se manda al Modelo, que es quien rechaza.
        return new Pedido(cliente, productos);
    }

    private Producto capturarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        BigDecimal precio = leerPrecio("Precio: ");
        int cantidad = leerEntero("Cantidad solicitada: ");
        int existencia = leerEntero("Existencia: ");

        return new Producto(nombre, precio, cantidad, existencia);
    }

    // Pedidos ya armados para cubrir las evidencias. Reutilización
    private Pedido capturarPedidoDePrueba() {
        System.out.println("Caso de prueba");
        System.out.println("1. Pedido válido con descuento");
        System.out.println("2. Pedido válido sin descuento");
        System.out.println("3. Pedido en límite del descuento");
        System.out.println("4. Pedido con cantidad mayor a la existencia");
        System.out.println("5. Pedido sin productos (inválido)");
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine().trim();

        switch (opcion) {
            case "1":
                return new Pedido("Francois", new ArrayList<>(List.of(
                        new Producto("Monitor", new BigDecimal("1250"), 3, 8))));
            case "2":
                return new Pedido("Diego", new ArrayList<>(List.of(
                        new Producto("Cuchara", new BigDecimal("22"), 1, 22),
                        new Producto("Servilletas", new BigDecimal("38"), 2, 51))));
            case "3":
                return new Pedido("Fabrizzio", new ArrayList<>(List.of(
                        new Producto("Teclado", new BigDecimal("500"), 1, 5),
                        new Producto("Mouse", new BigDecimal("250"), 2, 10))));
            case "4":
                return new Pedido("Pedro", new ArrayList<>(List.of(
                        new Producto("Teclado", new BigDecimal("500"), 5, 3))));
            case "5":
                return new Pedido("Edwin", new ArrayList<>());
            default:
                mostrarError("Opción no válida");
                return null;
        }
    }

    // Reutilización de capas que usaba Integer.parseInt.
    // Se repite la pregunta hasta que el usuario teclee un entero.
    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                mostrarError("Ingrese un número entero");
            }
        }
    }

    // Lo mismo que leerEntero pero para precios
    private BigDecimal leerPrecio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                mostrarError("Ingrese un precio válido");
            }
        }
    }

    // Se usa cuando el Modelo registró el pedido correctamente
    public void mostrarResultado(Pedido pedido) {
        System.out.println();
        System.out.println("Pedido registrado exitosamente");
        mostrarPedido(pedido);
    }

    public void mostrarPedido(Pedido pedido) {
        System.out.println("ID del pedido: " + pedido.getId());
        System.out.println("Cliente: " + pedido.getCliente());
        System.out.println("Productos:");
        for (Producto producto : pedido.getProductos()) {
            System.out.println("  - " + producto.getNombre() + " | " + dinero(producto.getPrecio()) + " x " + producto.getCantidad());
        }
        System.out.println("Subtotal: " + dinero(pedido.getSubtotal()));
        System.out.println("Descuento: " + dinero(pedido.getDescuento()));
        System.out.println("Impuestos: " + dinero(pedido.getImpuestos()));
        System.out.println("Total: " + dinero(pedido.getTotal()));
        System.out.println("Estado: " + pedido.getEstado());
    }

    // Se usa cuando el Modelo rechaza un pedido (llega desde el controlador)
    public void mostrarError(String mensaje) {
        System.out.println("Error:" + mensaje);
    }

    //Da formato a la salida de dinero
    protected String dinero(BigDecimal valor) {
        return "$" + valor.setScale(2, RoundingMode.HALF_UP);
    }
}
