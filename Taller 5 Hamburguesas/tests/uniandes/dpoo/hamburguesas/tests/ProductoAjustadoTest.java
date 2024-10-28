package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoAjustadoTest {

    @Test
    void testGetPrecioConAgregados() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 12000);
        ProductoAjustado ajustado = new ProductoAjustado(base);

        Ingrediente queso = new Ingrediente("Queso", 2000);
        ajustado.getAgregados().add(queso);
        assertEquals(14000, ajustado.getPrecio());
    }

    @Test
    void testGenerarTextoFactura() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 12000);
        ProductoAjustado ajustado = new ProductoAjustado(base);

        ajustado.getAgregados().add(new Ingrediente("Queso", 2000));
        String factura = ajustado.generarTextoFactura();

        assertTrue(factura.contains("Queso"));
        assertTrue(factura.contains("2000"));
    }
}

