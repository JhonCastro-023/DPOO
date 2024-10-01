package uniandes.dpoo.aerolinea.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas  {
    protected final int COSTO_POR_KM_NATURAL = 600;
    protected final int COSTO_POR_KM_CORPORATIVO = 800;
    protected final double DESCUENTO_PEQ = 0.02;
    protected final double DESCUENTO_MEDIANAS = 0.1;
    protected final double DESCUENTO_GRANDES = 0.2;

    public int calcularCostoBase(Vuelo vuelo, Cliente cliente ){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double calcularPorcentajeDescuento(Vuelo vuelo, Cliente cliente){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
