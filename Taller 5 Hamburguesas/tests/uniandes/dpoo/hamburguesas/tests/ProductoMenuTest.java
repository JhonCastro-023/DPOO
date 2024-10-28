package uniandes.dpoo.hamburguesas.tests;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductoMenuTest {
    @Test
	void testGetNombre() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 12000);
        assertEquals("Hamburguesa", producto.getNombre());
    }
    @Test
	void testGetPrecio() {
        ProductoMenu producto = new ProductoMenu("Papas", 5000);
        assertEquals(5000, producto.getPrecio());
    }
    @Test
	 void testGenerarTextoFactura() {
	        ProductoMenu producto = new ProductoMenu("Gaseosa", 3000);
	        String factura = producto.generarTextoFactura();
	        assertTrue(factura.contains("Gaseosa"));
	        assertTrue(factura.contains("3000"));
	    }
}
