package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class PedidoTest {

    @Test
    void testAgregarProducto() {
        Pedido pedido = new Pedido("Juan", "Calle 123");
        ProductoMenu producto = new ProductoMenu("Papas", 5000);
        pedido.agregarProducto(producto);

        assertEquals(1, pedido.getProductos().size());
    }

    @Test
    void testGenerarTextoFactura() {
        Pedido pedido = new Pedido("Ana", "Calle 456");
        pedido.agregarProducto(new ProductoMenu("Hamburguesa", 12000));

        String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Ana"));
        assertTrue(factura.contains("12000"));
    }

    @Test
    void testGuardarFactura() {
        Pedido pedido = new Pedido("Carlos", "Calle 789");
        pedido.agregarProducto(new ProductoMenu("Gaseosa", 3000));

        File archivo = new File("factura.txt");
        assertDoesNotThrow(() -> pedido.guardarFactura(archivo));
        assertTrue(archivo.exists());

        archivo.delete();
    }
}
