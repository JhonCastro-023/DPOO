package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ComboTest {

    @Test
    void testGetPrecioCombo() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(new ProductoMenu("Hamburguesa", 12000));
        items.add(new ProductoMenu("Papas", 5000));

        Combo combo = new Combo("Combo Especial", 0.9, items);
        assertEquals(15300, combo.getPrecio());
    }

    @Test
    void testGenerarTextoFactura() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(new ProductoMenu("Gaseosa", 3000));

        Combo combo = new Combo("Combo Bebida", 0.8, items);
        String factura = combo.generarTextoFactura();

        assertTrue(factura.contains("Combo Bebida"));
        assertTrue(factura.contains("Descuento: 0.8"));
    }
}

