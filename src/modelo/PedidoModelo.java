package modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    MODELO.
    Reutilización de la versión en capas.
    Diferencia con capas, en MVC el Modelo agrupa dos cosas:
    - Mantiene el estado (los pedidos registrados)
    - Ejecuta las reglas del negocio
    No imprime nada ni pide datos al usuario, cuando algo no es válido
    lanza una excepción, el Controlador la atrapa y se la pasa a la Vista.
 */

public class PedidoModelo {

    private static final BigDecimal INICIO_DESCUENTO = new BigDecimal("1000");
    private static final BigDecimal PORCENTAJE_DESCUENTO = new BigDecimal("0.10");
    private static final BigDecimal IVA = new BigDecimal("0.16");
    private final List<PedidoObservador> observers = new ArrayList<>();
    private Map<Integer, Pedido> pedidos = new HashMap<>();
    private int siguienteId = 1;

    //Operaciones para añadir, eliminar y notificar a observadores (En este caso, la vista)
    public void agregarObserver (PedidoObservador observer){
        observers.add(observer);
    }
    public void eliminarObserver (PedidoObservador observer){
        observers.remove(observer);
    }

    public void notificarPedidoRegistrado (Pedido pedido){
        for (PedidoObservador o:observers){
            o.pedidoRegistrado(pedido);
        }
    }

    //Valida, calcula, guarda, y devuelve el pedido
    public Pedido registrarPedido(Pedido pedido) {
        validar(pedido);

        BigDecimal subtotal = calcularSubtotal(pedido);
        pedido.setSubtotal(subtotal);

        BigDecimal descuento = calcularDescuento(subtotal);
        pedido.setDescuento(descuento);

        BigDecimal impuestos = calcularImpuestos(subtotal, descuento);
        pedido.setImpuestos(impuestos);

        BigDecimal total = (subtotal.subtract(descuento)).add(impuestos);
        pedido.setTotal(total);

        pedido.setEstado("PROCESADO");

        pedido.setId(siguienteId);
        pedidos.put(siguienteId, pedido);
        siguienteId++;
        //Una vez guardado el pedido, se notifica a los observadores
        notificarPedidoRegistrado(pedido);

        return pedido;
    }

    public Pedido consultarPedido(int id) {
        return pedidos.get(id);
    }

    // Se devuelve una copia de la lista
    public List<Pedido> listarPedidos() {
        return new  ArrayList<>(pedidos.values());
    }


    private void validar(Pedido pedido){
        if(pedido.getCliente() == null || pedido.getCliente().trim().isEmpty()){
            throw new IllegalArgumentException("El cliente no puede estar vacío");
        }

        if(pedido.getProductos() == null || pedido.getProductos().isEmpty()){
            throw new IllegalArgumentException("El pedido debe tener al menos un producto");
        }

        for (Producto producto :  pedido.getProductos()) {
            if(producto.getCantidad() <= 0){
                throw new IllegalArgumentException("La cantidad solicitada debe ser mayor que 0");
            }

            if(producto.getCantidad() > producto.getExistencia()){
                throw new IllegalArgumentException("No puede solicitarse una cantidad superior a la existencia disponible.");
            }
        }
    }

    // subtotal = suma de (precio * cantidad) de cada producto del pedido
    private BigDecimal calcularSubtotal(Pedido pedido){
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Producto producto :  pedido.getProductos()) {
            BigDecimal cantidad = new BigDecimal(producto.getCantidad());
            subtotal = subtotal.add(producto.getPrecio().multiply(cantidad));

        }
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    // 10% de descuento si el subtotal alcanza el inicio del descuento
    private BigDecimal calcularDescuento(BigDecimal subtotal){
        //BigDecimal.compareTo devuelve un número positivo si el de la
        //izquierda es mayor, 0 si son iguales y negativo si es menor
        if (subtotal.compareTo(INICIO_DESCUENTO) >= 0){
            return subtotal.multiply(PORCENTAJE_DESCUENTO).setScale(2,RoundingMode.HALF_UP);
        } else {
            return BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
        }
    }

    // impuestos = 16% sobre el subtotal - descuento
    private BigDecimal calcularImpuestos(BigDecimal subtotal, BigDecimal descuento){
        BigDecimal base = (subtotal.subtract(descuento));
        return base.multiply(IVA).setScale(2,RoundingMode.HALF_UP);
    }
}