package uniandes.dpoo.hamburguesas.mundo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.excepciones.*;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testIniciarPedidoExitoso() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Juan", "Calle 123");
        assertNotNull(restaurante.getPedidoEnCurso());
    }

    @Test
    public void testIniciarPedidoCuandoYaHayUno() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Ana", "Calle 456");

        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Carlos", "Calle 789");
        });
    }

    @Test
    public void testCerrarYGuardarPedidoExitoso() throws Exception {
        restaurante.iniciarPedido("Juan", "Calle 123");
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 12000);
        restaurante.getPedidoEnCurso().agregarProducto(producto);

        File archivo = new File("./facturas/factura_0.txt");
        restaurante.cerrarYGuardarPedido();

        assertTrue(archivo.exists());
        archivo.delete();  // Limpieza del archivo tras la prueba
    }

    @Test
    public void testCerrarPedidoSinPedidoEnCurso() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        });
    }

    @Test
    public void testGetPedidosInicialmenteVacio() {
        assertTrue(restaurante.getPedidos().isEmpty());
    }

    @Test
    public void testGetIngredientesInicialmenteVacio() {
        assertTrue(restaurante.getIngredientes().isEmpty());
    }

    @Test
    public void testGetMenuBaseInicialmenteVacio() {
        assertTrue(restaurante.getMenuBase().isEmpty());
    }

    @Test
    public void testGetMenuCombosInicialmenteVacio() {
        assertTrue(restaurante.getMenuCombos().isEmpty());
    }

    @Test
    public void testCargarIngredientes() throws Exception {
        File ingredientesFile = crearArchivoTemporal("queso;1000\ntomate;500\n");
        restaurante.cargarInformacionRestaurante(ingredientesFile, null, null);

        assertEquals(2, restaurante.getIngredientes().size());
    }

    @Test
    public void testCargarMenu() throws Exception {
        File menuFile = crearArchivoTemporal("Hamburguesa;12000\nPapas;5000\n");
        restaurante.cargarInformacionRestaurante(null, menuFile, null);

        assertEquals(2, restaurante.getMenuBase().size());
    }

    @Test
    public void testCargarCombos() throws Exception {
        File menuFile = crearArchivoTemporal("Hamburguesa;12000\nPapas;5000\n");
        File combosFile = crearArchivoTemporal("Combo 1;10%;Hamburguesa;Papas\n");

        restaurante.cargarInformacionRestaurante(null, menuFile, combosFile);
        assertEquals(1, restaurante.getMenuCombos().size());
    }

    @Test
    public void testIngredienteRepetidoException() throws Exception {
        File ingredientesFile = crearArchivoTemporal("queso;1000\nqueso;1000\n");
        assertThrows(IngredienteRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(ingredientesFile, null, null);
        });
    }

    @Test
    public void testProductoRepetidoException() throws Exception {
        File menuFile = crearArchivoTemporal("Hamburguesa;12000\nHamburguesa;12000\n");
        assertThrows(ProductoRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(null, menuFile, null);
        });
    }

    // Método auxiliar para crear archivos temporales
    private File crearArchivoTemporal(String contenido) throws IOException {
        File archivo = File.createTempFile("temp", ".txt");
        FileWriter writer = new FileWriter(archivo);
        writer.write(contenido);
        writer.close();
        return archivo;
    }
}